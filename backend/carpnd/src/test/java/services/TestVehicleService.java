package services;

import model.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml"})
public class TestVehicleService {

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void testSaveVehicleOnDB() {

        Vehicle vehicle = new Vehicle();
        vehicle.setDescription("Nachito");

        this.vehicleService.save(vehicle);
    }
}
