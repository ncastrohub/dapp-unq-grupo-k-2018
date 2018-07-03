package api;

import api.forms.ReserveForm;
import model.Reservation;
import model.User;
import model.exceptions.*;
import org.apache.commons.mail.EmailException;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import scripting.SecuredRequest;
import services.PublishService;
import services.ReserveService;
import utils.GetUserFromHeaders;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/reservation")
public class ReserveApi {

    private ReserveService reserveService;

    private PublishService publishService;

    public ReserveService getReserveService() {
        return reserveService;
    }

    public void setReserveService(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    public PublishService getPublishService() {
        return publishService;
    }

    public void setPublishService(final PublishService service) {
        this.publishService = service;
    }

    @POST
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/reserve/")
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    public Response makeReservation(@Context HttpHeaders headers, ReserveForm reserveForm) {
        User customer = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        try {
            Reservation newReservation = this.reserveService.reservePublication(reserveForm, customer.getId());
            return Response.ok(newReservation).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        } catch (NoReturnLocationInPublicationException | InvalidAmountOfDaysToReserveException | DayDisabledException | NotEnoughCreditException | DayAlreadyReservedException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.error).build();
        }
    }


    @GET
    @Path("/{reservationId}")
    @Produces("application/json")
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    public Response getVehiclesForUser(@PathParam("reservationId") Long reservationId) {
        return Response.ok(this.reserveService.getReservationService().findById(reservationId)).build();
    }

    @POST
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/confirmByOwner/{reservationId}")
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    public Response confirmReservationByOwner(@Context HttpHeaders headers, @PathParam("reservationId") Long reservationId) {
        User owner = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        try {
            return Response.ok(this.reserveService.confirmByOwner(reservationId, owner)).build();
        } catch (ReservationStateError | NotReservationOwnerException reservationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(reservationError.error).build();
        }
    }


    @POST
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/returnVehicle/{reservationId}")
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    public Response returnVehicle(@Context HttpHeaders headers, @PathParam("reservationId") Long reservationId) {
        User owner = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        try {
            return Response.ok(this.reserveService.returnVehicle(reservationId, owner)).build();
        } catch (ReservationStateError | NotReservationOwnerException reservationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(reservationError.error).build();
        }
    }


    @POST
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/reject/{reservationId}")
    @CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true)
    public Response reject(@Context HttpHeaders headers, @PathParam("reservationId") Long reservationId) {
        User owner = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        try {
            return Response.ok(this.reserveService.reject(reservationId, owner)).build();
        } catch (ReservationStateError | NotReservationOwnerException reservationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(reservationError.error).build();
        }
    }

    @GET
    @Path(value ="/list/owner/{pageNumber}/")
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    public Response reservationOwnerPage(@Context HttpHeaders headers, @PathParam(value="pageNumber") Integer pageNumber) {
        User owner = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        return Response.ok(this.reserveService.getReservationService().getPaginationPageOwner(owner.getId(), pageNumber)).build();
    }

    @GET
    @Path(value ="/list/owner/")
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    public Response reservationOwnerPage(@Context HttpHeaders headers) {
        User owner = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        return Response.ok(this.reserveService.getReservationService().getPaginationPageOwner(owner.getId())).build();
    }

    @GET
    @Path(value ="/list/customer/{pageNumber}/")
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    public Response reservationCustomerPage(@Context HttpHeaders headers, @PathParam(value="pageNumber") Integer pageNumber) {
        User owner = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        return Response.ok(this.reserveService.getReservationService().getPaginationPageCustomer(owner.getId(), pageNumber)).build();
    }

    @GET
    @Path(value ="/list/customer/")
    @SecuredRequest
    @Consumes("application/json")
    @Produces("application/json")
    public Response reservationCustomerPage(@Context HttpHeaders headers) {
        User owner = GetUserFromHeaders.getCurrentUserFromHeaders(headers, this.publishService);
        return Response.ok(this.reserveService.getReservationService().getPaginationPageCustomer(owner.getId())).build();
    }

}
