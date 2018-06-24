package api;

import api.forms.UserForm;
import org.testng.internal.collections.Pair;
import javassist.NotFoundException;
import model.User;
import model.exceptions.FormValidationError;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import services.PublishService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserApi {

    @Context
    private HttpHeaders headers;

    public PublishService getPublishService() {
        return publishService;
    }

    private PublishService publishService;

    public void setPublishService(final PublishService service) {
        this.publishService = service;
    }


// //////////////////////////////////////////////////7
// USERS
// //////////////////////////////////////////////////7

    @GET
    @Path("/list")
    @Produces("application/json")
    public Response getUserList() {
        return Response.ok(publishService.getUsers()).build();
    }

    @POST
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


    @POST
    @Path(value = "/get-by-email/")
    @Consumes("application/json")
    @Produces("application/json")
    @CrossOriginResourceSharing(
            allowAllOrigins = true,
            allowCredentials = true
    )
    public Response getUserByEmail(UserForm userF) {
        try {
            User theUser = publishService.getByEmail(userF.email);
            return Response.ok(theUser).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Pair<>("error","Cannot found user with that email")).build();
        }
    }

}
