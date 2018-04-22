


package api;

import model.Vehicle;
import services.PublicationServices;
import services.VehicleService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author nachelissssss
 */
@Path("/vehicle")
public class VehicleApi {

    PublicationServices publicationService;

    public PublicationServices getPublicationService() {
        return publicationService;
    }

    public void setPublicationService(PublicationServices publicationService) {
        this.publicationService = publicationService;
    }

    public void setPublicationServices(final PublicationServices service) {
        this.publicationService = service;
    }

    @GET
    @Path("/list")
    @Produces("application/json")
    public Response vehicleList() {
        Vehicle vehicle = new Vehicle();
        vehicle.setDescription("Nachito");

        this.publicationService.getVehicleService().save(vehicle);

        List<Vehicle> vehicles = publicationService.getVehicleService().retriveAll();

        return Response.ok(vehicles).build();
    }





}