package api;

import api.forms.UserForm;
import api.forms.UserUpdateForm;
import model.User;
import model.exceptions.FormValidationError;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.security.SecurityContext;
import scripting.AuthRequired;
import scripting.SecuredRequest;
import services.PublishService;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
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
    public Response currentUser(@Context SecurityContext securityContext) {
        Principal principal = securityContext.getUserPrincipal();
        String email = principal.getName();
        User user = this.publishService.getUserService().getByEmail(email);
        return Response.ok(user).build();
    }

}
