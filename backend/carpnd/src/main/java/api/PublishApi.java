package api;


import api.forms.*;
import model.Publication;
import model.User;
import model.Vehicle;
import model.exceptions.FormValidationError;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import services.PublishService;
import utils.OwnPaginationPage;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author nachelissssss
 */

@Path("/publication")
public class PublishApi {

    @Context
    private HttpHeaders headers;

    public PublishService getPublishService() {
        return publishService;
    }

    private PublishService publishService;

    public void setPublishService(final PublishService service) {
        this.publishService = service;
    }

    @GET
    @Path("/{userId}/vehicle/list")
    @Produces("application/json")
    @CrossOriginResourceSharing(
            allowAllOrigins = true,
            allowCredentials = true
    )
    public Response getVehiclesForUser(@PathParam("userId") String userId) {

        List vehicleList = publishService.getVehiclesForUser(new Long(userId));
        return Response.ok(vehicleList).build();
    }

    @GET
    @Path("/user/list")
    @Produces("application/json")
    public Response getUserList() {
        return Response.ok(publishService.getUsers()).build();
    }


    @POST
    @Path(value = "/{userId}/vehicle/new")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createVehicleForUser(@PathParam("userId") Long userId, VehicleForm vehicle) {
        try {
            Vehicle newVehicle = publishService.createVehicleForUser(userId, vehicle);
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
        publishService.deleteVehicle(userId, vehicle.id);
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
            publishService.updateVehicle(userId, vehicle);
            return Response.ok(vehicle).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

    @POST
        @Path(value = "/user/new")
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

// AGREGADO PARA PROCESAR USUARIOS
    @POST
    @Path(value = "/user/getByEmail/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getUserByEmail(UserForm userF) {
        try {

            User theUser = publishService.getByEmail(userF);
            return Response.ok(theUser).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }
// FIN AGREGADO

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/user/delete/")
    public Response deleteUser(UserUpdateForm userForm) {
        publishService.deleteUser(userForm.id);
        return Response.ok().build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/user/edit/")
    public Response updateUser(UserUpdateForm userForm) {
        try {
            this.publishService.updateUser(userForm);
            return Response.ok(userForm).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/publication/list/")
    public Response publicationList() {
        OwnPaginationPage<Publication> page = this.publishService.getPublicationService().getPaginationPage();
        return Response.ok(page).build();
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/publication/{publicationId}/")
    public Response publicationDetail(@PathParam("publicationId") Long publicationId) {
        return Response.ok(this.publishService.getPublicationService().findById(publicationId)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/{userId}/publication/create/")
    public Response publicationCreate(@PathParam("userId") Long userId, PublicationForm publicationForm) {
        try {
            Publication newPublication = this.publishService.createPublicationForUser(userId, publicationForm);
            return Response.ok(newPublication).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

    

}