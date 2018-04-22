package services;

import model.*;
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
@ContextConfiguration({
        "/META-INF/spring-database-context.xml",
        "/META-INF/spring-services-context.xml"
})
public class TestVehicleService {

    @Autowired
    private PublicationServices publicationService;

    @Test
    public void testSaveVehicleOnDB() {
        Vehicle vehicle = VehicleBuilder.someVehicle();
        VehicleService vehicleService = this.publicationService.getVehicleService();
        vehicleService.save(vehicle);
        Vehicle savedVehicle = vehicleService.getById(vehicle.getId());
        assertThat(vehicle.description).isEqualTo(savedVehicle.description);
        assertThat(vehicle.type).isEqualTo(savedVehicle.type);
        assertThat(vehicle.capacity).isEqualTo(savedVehicle.capacity);
        assertThat(vehicle.photo).isEqualTo(savedVehicle.photo);
        assertThat(vehicle.photo).isEqualTo(savedVehicle.photo);
    }

    @Test
    public void testSaveVehicleToUser() {
        User user = UserBuilder.someUser();
        this.publicationService.getUserService().save(user);
        Vehicle vehicle = VehicleBuilder.start().
                withCapacity(3).
                withDescripion("Un Auto Lindo").
                withPhoto("https://autophoto.jpg").
                withType(VehicleType.COUPE).
                withUser(user).
                build();
        this.publicationService.getVehicleService().save(vehicle);

        List<Vehicle> vehicleList = this.publicationService.getUserService().getVehiclesForUser(user.getId());

        Vehicle firstVehicle = vehicleList.get(0);
        assertThat(vehicle.description).isEqualTo(firstVehicle.description);
        assertThat(vehicle.type).isEqualTo(firstVehicle.type);
        assertThat(vehicle.capacity).isEqualTo(firstVehicle.capacity);
        assertThat(vehicle.photo).isEqualTo(firstVehicle.photo);
        assertThat(vehicle.user.getId()).isEqualTo(firstVehicle.user.getId());
    }
}
//
//}
//
//    @Test
//    public void testSaveVehicleWithInvalidDataReturnValidationErrorAndNotSave() {
//
//    }
//
//
//    @Test void testDeleteVehicleForUser(){
//
//    }
//
//    @Test void testMofifyVehicle(){
//
//    }
