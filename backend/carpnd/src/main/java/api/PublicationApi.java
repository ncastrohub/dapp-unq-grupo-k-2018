package api;

import api.DETEOS.VehicleForm;
import model.Exceptions.FormValidationError;
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
    public Response getVehiclesForUser() {


        return Response.ok(publicationService.getUsers()).build();
    }


    @POST
    @Path(value = "/{userid}/vehicle/new")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createVehicleForUser(@PathParam("userid") String userId, VehicleForm vehicle) {

        try {
            publicationService.createVehicleForUser(userId, vehicle);
            return Response.ok(vehicle).build();
        } catch (FormValidationError formValidationError){
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

}