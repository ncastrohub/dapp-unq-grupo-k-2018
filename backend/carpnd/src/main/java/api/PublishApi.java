package api;


import api.forms.PublicationForm;
import api.forms.UserUpdateForm;
import api.forms.VehicleForm;
import api.forms.VehicleUpdateForm;
import model.Publication;
import model.User;
import model.Vehicle;
import model.VehicleType;
import model.exceptions.FormValidationError;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import scripting.SecuredRequest;
import services.PublishService;
import utils.GetUserFromHeaders;
import utils.OwnPaginationPage;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author nachelissssss
 */

@Path("/publication")
public class PublishApi {

    @Context
    private HttpHeaders headers;
    private PublishService publishService;


    public PublishService getPublishService() {
        return publishService;
    }

    public void setPublishService(final PublishService service) {
        this.publishService = service;
    }

    @GET
    @SecuredRequest
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    @Path("/vehicle/list")
    @Produces("application/json")
    public Response getVehiclesForUser(@Context HttpHeaders headers) {
        User user = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        List vehicleList = publishService.getVehiclesForUser(user.getId());
        return Response.ok(vehicleList).build();
    }

    @POST
    @SecuredRequest
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    @Path(value = "/vehicle/new")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createVehicleForUser(@Context HttpHeaders headers, VehicleForm vehicle) {
        User user = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        try {
            Vehicle newVehicle = publishService.createVehicleForUser(user.getId(), vehicle);
            return Response.ok(newVehicle).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

    @POST
    @SecuredRequest
    @Path(value = "/vehicle/delete/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteVehicleForUser(@Context HttpHeaders headers, VehicleUpdateForm vehicle) {
        User user = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        publishService.deleteVehicle(vehicle.id, user.getId());
        return Response.ok(vehicle).build();
    }

    @POST
    @SecuredRequest
    @Path(value = "/vehicle/update/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateVehicleForUser(@Context HttpHeaders headers, VehicleUpdateForm vehicle) {
        User user = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        try {
            publishService.updateVehicle(user.getId(), vehicle);
            return Response.ok(vehicle).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }


    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/{publicationId}/")
    public Response publicationDetail(@PathParam("publicationId") Long publicationId) {
        return Response.ok(this.publishService.getPublicationService().findById(publicationId)).build();
    }


    @POST
    @SecuredRequest
    @Produces("application/json")
    @Path(value = "/create/")
    public Response publicationCreate(@Context HttpHeaders headers, PublicationForm publicationForm) {
        User owner = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        try {
            Publication newPublication = this.publishService.createPublicationForUser(owner.getId(), publicationForm);
            return Response.ok(newPublication).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        }
    }

//    @GET
////    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
//    @Path(value ="/list/{vehicleType}")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response publicationListByVehicleSedan(@PathParam(value = "vehicleType") VehicleType vehicleType) {
//        return Response.ok(this.publishService.getPublicationService().getPaginationPageByVehicleType(vehicleType)).build();
//    }


    @GET
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    @Path(value ="/listtype/{vehicleType}/{pageNumber}/")
    public Response publicationPageAndVehicleTypeWithPageNumber(
            @PathParam("vehicleType") VehicleType vehicleType,
            @PathParam("pageNumber") Integer pageNumber) {
        return Response.ok(this.publishService.getPublicationService().getPaginationPageByVehicleType(vehicleType, pageNumber)).type("application/json").build();
    }

    @GET
//    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    @Path("/listtype/{vehiculoNOmbre}/")
    public Response AndVehicleType(@PathParam("vehiculoNOmbre") VehicleType vehiculoNOmbre) {
        return Response.ok(this.publishService.getPublicationService().getPaginationPageByVehicleType(vehiculoNOmbre)).type("application/json").build();
    }

    @GET
//    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    @Path(value ="/list/{pageNumber}/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response publicationPage(@PathParam("pageNumber") Integer pageNumber) {
        return Response.ok(this.publishService.getPublicationService().getPaginationPage(pageNumber)).build();
    }

    @GET
//    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    @Path(value ="/list/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response publicationPage() {
        return Response.ok(this.publishService.getPublicationService().getPaginationPage()).build();
    }

}