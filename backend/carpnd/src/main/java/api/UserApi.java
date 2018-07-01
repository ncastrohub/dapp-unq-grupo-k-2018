package api;

import api.forms.MoneyAndAmountForm;
import api.forms.UserForm;
import api.forms.UserUpdateForm;
import model.User;
import model.exceptions.FormValidationError;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import scripting.AuthRequired;
import scripting.SecuredRequest;
import services.PublishService;
import utils.GetUserFromHeaders;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserApi {


    public PublishService getPublishService() {
        return publishService;
    }

    private PublishService publishService;

    public void setPublishService(final PublishService service) {
        this.publishService = service;
    }
//
//    private User getCurrentUser(SecurityContext securityContext){
//        Long userId = new Long(securityContext.getUserPrincipal().getName());
//        return this.publishService.getUserService().findById(userId);
//    }

//    public User getCurrentUserFromHeaders(HttpHeaders headers){
//        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet("https://tpi-desapp2.auth0.com/userinfo");
//        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//        request.setHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
//        HttpResponse response = null;
//        try {
//            response = client.execute(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        HttpEntity entity = response.getEntity();
//        try {
//            InputStream instream = entity.getContent();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String json = null;
//        try {
//            json = EntityUtils.toString(entity);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JSONObject jsonObj = new JSONObject(json);
//
//        String email = jsonObj.get("email").toString();
//        return this.publishService.getUserService().getByEmail(email);
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
    public Response deleteUser(@Context HttpHeaders headers) {
        User user = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        publishService.deleteUser(user.getId());
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
    public Response currentUser(@Context HttpHeaders headers) {
        User user = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        return Response.ok(user).build();
    }


    @POST
    @SecuredRequest
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    @Path(value ="/addMoney/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addMoneyToUser(@Context HttpHeaders headers, MoneyAndAmountForm amount) {
        User user = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        try {
            this.publishService.addMoneyToUser(user, amount);
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
        return Response.ok().build();
    }

}
