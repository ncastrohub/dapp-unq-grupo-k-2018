package api;

import api.forms.MoneyAndAmountForm;
import api.forms.UserForm;
import api.forms.UserUpdateForm;
import javassist.NotFoundException;
import model.User;
import model.exceptions.FormValidationError;
import org.apache.commons.mail.EmailException;
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



//
//    @GET
//    @Path(value ="/unsecurenew/{email}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response unsecureCurrentUser(@PathParam("email") String email) {
//        User newUser;
//        try {
//            newUser = publishService.getByEmail(email);
//        } catch (NotFoundException e) {
//            try {
//                UserForm userF = new UserForm();
//                userF.email = email;
//                userF.name = "Julia";
//                userF.lastName = "Troilo";
//                userF.cuil = "20313097681";
//                newUser = publishService.createUser(userF);
//                try {
//                    publishService.sendTestEmail(newUser, "Grosa prueba exitosa!");
//                } catch (EmailException e1) {
//                    newUser.lastName += " (" + e1.getMessage() + ")";
//                }
//            } catch (FormValidationError formValidationError) {
//                return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
//            }
//        }
//        return Response.ok(newUser).build();
//    }
//
//    @GET
//    @Path(value = "/prueba/")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response unsecureNew() {
//            return Response.ok("Prueba vive!").build();
//    }
//
//    @GET
//    @Path(value = "/list/")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response unsecureList() {
//        return Response.ok(publishService.getUsers()).build();
//    }

}
