


package api;

import model.Vehicle;
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

    private VehicleService vehicleService;

    public void setVehicleService(final VehicleService service) {
        this.vehicleService = service;
    }

    @GET
    @Path("/list")
    @Produces("application/json")
    public Response vehicleList() {
        Vehicle vehicle = new Vehicle();
        vehicle.setDescription("Nachito");

        this.vehicleService.save(vehicle);

        List<Vehicle> vehicles = vehicleService.retriveAll();

        return Response.ok(vehicles).build();
    }
}