package api;

import api.forms.ReserveForm;
import model.Reservation;
import model.exceptions.*;
import services.ReserveService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/reservation")
public class ReserveApi {

    private ReserveService reserveService;


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/reserve/")
    public Response makeReservation(ReserveForm reserveForm) {
        try {
            Reservation newReservation = this.reserveService.reservePublication(reserveForm);
            return Response.ok(newReservation).build();
        } catch (FormValidationError formValidationError) {
            return Response.status(Response.Status.BAD_REQUEST).entity(formValidationError.errors).build();
        } catch (NoReturnLocationInPublicationException | InvalidAmountOfDaysToReserveException | DayDisabledException | DayAlreadyReservedException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.error).build();
        }
    }

    public ReserveService getReserveService() {
        return reserveService;
    }

    public void setReserveService(ReserveService reserveService) {
        this.reserveService = reserveService;
    }
}
