package api;

import api.DETEOS.CreateVehicleContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Vehicle;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import services.PublicationConcernService;
import services.VehicleService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author nachelissssss
 */
@Path("/publication")
public class VehicleApi {


    private PublicationConcernService publicationService;


    public void setVehicleService(final VehicleService service) {
        this.vehicleService = service;
    }

    @GET
    @Path("/vehicle/list")
    @Produces("application/json")
    public Response getVehiclesForUser() {
        Vehicle vehicle = new Vehicle();
        vehicle.setDescription("Nachito");

        this.vehicleService.save(vehicle);

        List<Vehicle> vehicles = vehicleService.retriveAll();

        return Response.ok(vehicles).build();
    }

//    @GET
//    @Path("/vehicle/list")
//    @Produces("application/json")
//    public Response getVehiclesForUser() {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setDescription("Nachito");
//
//        this.vehicleService.save(vehicle);
//
//        List<Vehicle> vehicles = vehicleService.retriveAll();
//
//        return Response.ok(vehicles).build();
//    }


    @POST
    @Path(value = "/vehicle/new")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createVehicleForBusiness(CreateVehicleContainer vehicle) {


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();

        try {
            publicationService.createVehicleForUser(vehicle.getUser(), vehicle.getVehicle());
            return Response.ok(vehicle).build();
        } catch (ConstraintViolationException validationError){

            ArrayNode errorsAsJson = mapper.valueToTree(errorList);
            objectNode1.putArray("errors").add(errorsAsJson);
            return Response.status(Response.Status.BAD_REQUEST).entity(objectNode1.toString()).build();
        }
    }

}