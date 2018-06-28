package api;

import api.forms.UserForm;
import api.forms.UserUpdateForm;
import model.User;
import model.exceptions.FormValidationError;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.security.SecurityContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import scripting.AuthRequired;
import scripting.SecuredRequest;
import services.PublishService;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@Path("/user")
public class UserApi {

//    @Resource
    public SecurityContext getSecurityContext() {
        return securityContext;
    }

//    @Resource
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    //    @Context
//    private HttpHeaders headers;
//
    @Context
    SecurityContext securityContext;



    public PublishService getPublishService() {
        return publishService;
    }

    private PublishService publishService;

    public void setPublishService(final PublishService service) {
        this.publishService = service;
    }



    private User getCurrentUser(SecurityContext securityContext){
        Long userId = new Long(securityContext.getUserPrincipal().getName());
        return this.publishService.getUserService().findById(userId);
    }

    public User getCurrentUserFromHeaders(HttpHeaders headers){
        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://tpi-desapp2.auth0.com/userinfo");
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        request.setHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            InputStream instream = entity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = null;
        try {
            json = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObj = new JSONObject(json);

        String email = jsonObj.get("email").toString();
        return this.publishService.getUserService().getByEmail(email);
    }



//    @GET
//    @AuthRequired()
//    @Path("/list")
//    @Produces("application/json")
//    public Response getUserList(@Context SecurityContext securityContext) {
//        return Response.ok(publishService.getUsers()).build();
//    }

    @POST
    @AuthRequired
    @Path(value = "/new")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(UserForm userF) {
        try {
            User newUser = publishService.createUser(userF);
            return Response.ok(newUser).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }


    @GET
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/delete/")
    public Response deleteUser(@Context SecurityContext securityContext) {
        publishService.deleteUser(this.getCurrentUser(securityContext).getId());
        return Response.ok().build();
    }


    @POST
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/edit/")
    public Response updateUser(UserUpdateForm userForm) {
        try {
            this.publishService.updateUser(userForm);
            return Response.ok(userForm).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }


    @POST
    @SecuredRequest
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    @Path(value ="/currentUser/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response currentUser(@Context HttpHeaders headers, @Context SecurityContext securityContext) {

//        Principal principal = securityContext.getUserPrincipal();
//        String email = principal.getName();
//        User user = this.publishService.getUserService().getByEmail(email);
        return Response.ok(getCurrentUserFromHeaders(headers)).build();
    }

}
