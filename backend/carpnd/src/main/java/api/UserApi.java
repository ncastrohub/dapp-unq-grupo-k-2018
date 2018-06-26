package api;

import api.forms.UserForm;
import javafx.util.Pair;
import javassist.NotFoundException;
import model.User;
import model.exceptions.FormValidationError;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.security.SecurityContext;
import org.springframework.security.access.annotation.Secured;
import scripting.AuthRequired;
import services.PublishService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserApi {

    @Context
    private HttpHeaders headers;

    @Context
    SecurityContext securityContext;

    public PublishService getPublishService() {
        return publishService;
    }

    private PublishService publishService;

    public void setPublishService(final PublishService service) {
        this.publishService = service;
    }



    @GET
    @AuthRequired()
    @Path("/list")
    @Produces("application/json")
    public Response getUserList(@Context SecurityContext securityContext) {
        return Response.ok(publishService.getUsers()).build();
    }

    @POST
    @AuthRequired()
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
    @AuthRequired
    @Path(value = "/get-by-email/")
    @Consumes("application/json")
    @Produces("application/json")
    @CrossOriginResourceSharing(
            allowAllOrigins = true,
            allowCredentials = true
    )
    public Response getUserByEmail(UserForm userF, @Context SecurityContext securityContext) {
        try {
            User theUser = publishService.getByEmail(securityContext.getUserPrincipal().getName());
            return Response.ok(theUser).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Pair<>("error","Cannot found user with that email")).build();
        }
    }

}
