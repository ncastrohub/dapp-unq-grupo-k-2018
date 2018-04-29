package api;

import api.DETEOS.VehicleForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Exceptions.CustomValidationError;
import org.hibernate.exception.ConstraintViolationException;
import services.PublicationConcernService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.LinkedList;

/**
 * @author nachelissssss
 */
@Path("/publication")
public class PublicationApi {


    private PublicationConcernService publicationService;


    public void setPublicationService(final PublicationConcernService service) {
        this.publicationService = service;
    }

//    @GET
//    @Path("/vehicle/list")
//    @Produces("application/json")
//    public Response getVehiclesForUser() {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setDescription("Nachito");
//
//        this.publicationService.saveVehicle(vehicle);
//
//        List<Vehicle> vehicles = vehicleService.retriveAll();
//
//        return Response.ok(vehicles).build();
//    }


    @POST
    @Path(value = "/vehicle/new")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createVehicleForUser(VehicleForm vehicle) {


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();

        try {
            publicationService.createVehicleForUser(vehicle.getUser(), vehicle.getVehicle());
            return Response.ok(vehicle).build();
        } catch (ConstraintViolationException validationError){

            ArrayNode errorsAsJson = mapper.valueToTree(new LinkedList<>());
            objectNode1.putArray("errors").add(errorsAsJson);
            return Response.status(Response.Status.BAD_REQUEST).entity(objectNode1.toString()).build();

        } catch (CustomValidationError customValidationError) {
            customValidationError.printStackTrace();
        }
        return Response.ok(vehicle).build();
    }

}