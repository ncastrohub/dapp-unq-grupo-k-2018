package services;

import model.CustomCurrencies;
import model.MoneyAndAmount;
import model.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.builders.VehicleBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml"})
public class TestVehicleService {

    @Autowired
    private VehicleService vehicleService;


    @Test
    public void testSaveVehicleOnDB() {
        Vehicle vehicle = VehicleBuilder.someVehicle();
        this.vehicleService.save(vehicle);
        Vehicle savedVehicle = this.vehicleService.getById(vehicle.getId());
        assertThat(vehicle.description).isEqualTo(savedVehicle.description);
        assertThat(vehicle.type).isEqualTo(savedVehicle.type);
        assertThat(vehicle.capacity).isEqualTo(savedVehicle.capacity);
        assertThat(vehicle.photo).isEqualTo(savedVehicle.photo);
        assertThat(vehicle.photo).isEqualTo(savedVehicle.photo);
    }
}
//
//}@Test
//    public void testSaveVehicleToUser() {
//
//    }
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
