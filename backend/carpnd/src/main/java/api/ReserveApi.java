package api;

import api.forms.ReserveForm;
import model.Reservation;
import model.exceptions.*;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import services.ReserveService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/reservation")
public class ReserveApi {

    private ReserveService reserveService;


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/reserve/")
    @CrossOriginResourceSharing(
            allowAllOrigins = true,
            allowCredentials = true
    )
    public Response makeReservation(ReserveForm reserveForm) {
        try {
            Reservation newReservation = this.reserveService.reservePublication(reserveForm);
            return Response.ok(newReservation).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        } catch (NoReturnLocationInPublicationException | InvalidAmountOfDaysToReserveException | DayDisabledException | NotEnoughCreditException | DayAlreadyReservedException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.error).build();
        }
    }

    public ReserveService getReserveService() {
        return reserveService;
    }

    public void setReserveService(ReserveService reserveService) {
        this.reserveService = reserveService;
    }


    @GET
    @Path("/reservation/{reservationId}")
    @Produces("application/json")
    @CrossOriginResourceSharing(
            allowAllOrigins = true,
            allowCredentials = true
    )
    public Response getVehiclesForUser(@PathParam("reservationId") Long reservationId) {
        return Response.ok(this.reserveService.getReservationService().findById(reservationId)).build();
    }
}
