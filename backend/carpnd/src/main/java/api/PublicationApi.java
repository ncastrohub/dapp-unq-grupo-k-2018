package api;


import api.DETEOS.UserForm;
import api.forms.VehicleForm;
import api.forms.VehicleUpdateForm;
import model.Vehicle;
import model.User;
import model.exceptions.FormValidationError;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import services.PublicationConcernService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author nachelissssss
 */

@Path("/publication")
public class PublicationApi {

    @Context
    private HttpHeaders headers;

    private PublicationConcernService publicationService;

    public void setPublicationService(final PublicationConcernService service) {
        this.publicationService = service;
    }

    @GET
    @Path("/{userId}/vehicle/list")
    @Produces("application/json")
    @CrossOriginResourceSharing(
            allowAllOrigins = true,
            allowCredentials = true
    )
    public Response getVehiclesForUser(@PathParam("userId") String userId) {

        List vehicleList = publicationService.getVehiclesForUser(new Long(userId));
        return Response.ok(vehicleList).build();
    }

    @GET
    @Path("/user/list")
    @Produces("application/json")
    public Response getUserList() {
        return Response.ok(publicationService.getUsers()).build();
    }


    @POST
    @Path(value = "/{userId}/vehicle/new")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createVehicleForUser(@PathParam("userId") Long userId, VehicleForm vehicle) {
        try {
            Vehicle newVehicle = publicationService.createVehicleForUser(userId, vehicle);
            return Response.ok(newVehicle).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

    @POST
    @Path(value = "/{userId}/vehicle/delete")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteVehicleForUser(@PathParam("userId") Long userId, VehicleUpdateForm vehicle) {
        publicationService.deleteVehicle(userId, vehicle.id);
        return Response.ok(vehicle).build();
    }

    @POST
    @Path(value = "/{userId}/vehicle/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateVehicleForUser(
            @PathParam("userId") Long userId,
            VehicleUpdateForm vehicle) {
        try {
            publicationService.updateVehicle(userId, vehicle);
            return Response.ok(vehicle).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

    @POST
    @Path(value = "/userF/createUser")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(UserForm userF) {
        try {
            User newUser = publicationService.createUser (userF);
            return Response.ok(newUser).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

/*    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteUser(UserUpdateForm userU) throws FormValidationError {
        publicationService.deleteUser(userU);
        return Response.ok(userU).build();
        }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUserForUser(
            UserUpdateForm userUF) {
        try {
            publicationService.updateUser(userUF);
            return Response.ok(userUF).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }*/

}