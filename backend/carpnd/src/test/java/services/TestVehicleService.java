package services;

import api.DETEOS.VehicleForm;
import model.Exceptions.FormValidationError;
import model.User;
import model.Vehicle;
import model.VehicleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.builders.UserBuilder;
import utils.builders.VehicleBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml"})
public class TestVehicleService {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserService userService;


    @Autowired
    private PublicationConcernService publicationService;

    @Test
    public void testCreateVehicleForUser() throws FormValidationError {

        User user = UserBuilder.someUser();
        this.userService.save(user);

        VehicleForm vehicle = VehicleBuilder.start()
                .withCapacity(3)
                .withDescription("Un lindo auto")
                .withPhoto("https://autito.jpg")
                .withType(VehicleType.SEDAN)
                .buildForm();

        publicationService.createVehicleForUser(user.getId(), vehicle);

        List<Vehicle> vehicleList = this.publicationService.getVehiclesForUser(user.getId());
        Vehicle retrievedVehicle = vehicleList.get(0);
        assertThat(retrievedVehicle.capacity).isEqualTo(3);
        assertThat(retrievedVehicle.type).isEqualTo(VehicleType.SEDAN);
        assertThat(retrievedVehicle.description).isEqualTo("Un lindo auto");
        assertThat(retrievedVehicle.photo).isEqualTo("https://autito.jpg");
        assertThat(retrievedVehicle.owner.getId()).isEqualTo(user.getId());
    }


//    @Test
//    public void testCreateVehicleForUserWithInvalidData() {
//
//        User user = UserBuilder.someUser();
//        this.userService.save(user);
//
//        VehicleForm vehicle = VehicleBuilder.start()
//                .withCapacity(3)
//                .withDescription("Un l")
//                .withPhoto("httito.jpg")
//                .withType(VehicleType.SEDAN)
//                .buildForm();
//
//
//        try {
//            publicationService.createVehicleForUser(user.getId(), vehicle);
//
//            List<Vehicle> vehicleList = this.publicationService.getVehiclesForUser(user.getId());
//
//        } catch (FormValidationError validationError) {
//
//
//        }
//
//
//    }


//    @Test
//    public void testGetVehiclesForUser() {
//
//        User user = UserBuilder.someUser();
//        this.userService.save(user);
//        Vehicle firstVehicle = VehicleBuilder.start()
//                .withCapacity(3)
//                .withDescription("Un lindo auto")
//                .withPhoto("https://autito.jpg")
//                .withType(VehicleType.SEDAN)
//                .withOwner(user)
//                .build();
//
//        Vehicle secondVehicle = VehicleBuilder.start()
//                .withCapacity(3)
//                .withDescription("Un lindo auto")
//                .withPhoto("https://autito.jpg")
//                .withType(VehicleType.SEDAN)
//                .withOwner(user)
//                .build();
//
//        this.vehicleService.save(vehicle);
//        assertThat(vehicle.capacity).isEqualTo(3);
//        assertThat(vehicle.type).isEqualTo(VehicleType.SEDAN);
//        assertThat(vehicle.description).isEqualTo("Un lindo auto");
//        assertThat(vehicle.photo).isEqualTo("https://autito.jpg");
//        assertThat(vehicle.owner).isEqualTo(user);
//    }
}
