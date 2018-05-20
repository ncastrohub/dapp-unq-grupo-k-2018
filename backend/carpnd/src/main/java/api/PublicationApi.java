package api;
import api.DETEOS.UserForm;
import api.DETEOS.UserUpdateForm;
import api.forms.VehicleForm;
import api.forms.VehicleUpdateForm;
import model.exceptions.FormValidationError;
import model.Vehicle;
import model.User;
import services.PublicationConcernService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author nachelissssss
 */
@Path("/publication")
public class PublicationApi {


    private PublicationConcernService publicationService;

    public void setPublicationService(final PublicationConcernService service) {
        this.publicationService = service;
    }

    @GET
    @Path("/{userId}/vehicle/list")
    @Produces("application/json")
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
    @Path(value = "/{userId}/userF/createUse/new")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(@PathParam("userId") Long userId, UserForm userF) {
        try {
            User newUser = publicationService.createUser (userId, userF);
            return Response.ok(newUser).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

    @POST
    @Path(value = "/{userId}/userU")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteUser(Long userId) {
        publicationService.deleteUser(userId);
        return Response.ok(userId).build();
        }


    @POST
    @Path(value = "/user")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUserForUser(
             UserUpdateForm user){
        try {
            publicationService.updateUser(user);
            return Response.ok(user).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
      }
    }
}