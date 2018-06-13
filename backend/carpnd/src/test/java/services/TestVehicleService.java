package services;

import api.forms.VehicleForm;
import api.forms.VehicleUpdateForm;
import model.exceptions.FormValidationError;
import model.User;
import model.Vehicle;
import model.VehicleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.builders.UserBuilder;
import utils.builders.VehicleBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-test.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestVehicleService {

    @Autowired
    private UserService userService;

    @Autowired
    private PublishService publicationService;

    @Test
    public void testGetVehiclesForUser() throws FormValidationError {

        User user = UserBuilder.someUser();
        this.userService.save(user);

        for (VehicleForm each : createTwoVehicles())
        publicationService.createVehicleForUser(user.getId(), each);

        List<Vehicle> vehicleList = this.publicationService.getVehiclesForUser(user.getId());
        assertThat(vehicleList.size()).isEqualTo(2);

        assertThat(vehicleList.get(0).type).isEqualTo(VehicleType.SEDAN);
        assertThat(vehicleList.get(1).type).isEqualTo(VehicleType.COUPE);
    }


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


    @Test
    public void testEditVehicleForUser() throws FormValidationError {

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

        Vehicle savedVehicle = vehicleList.get(0);

        VehicleUpdateForm updateForm = new VehicleUpdateForm();
        updateForm.capacity = 4;
        updateForm.description = "Un auto lindo y amplio";
        updateForm.photo = "https://autito_nuevo.jpg";
        updateForm.type = VehicleType.COUPE;
        updateForm.id = savedVehicle.getId();

        this.publicationService.updateVehicle(user.getId(), updateForm);

        Vehicle vehicleUpdated = this.publicationService.getVehiclesForUser(user.getId()).get(0);

        assertThat(vehicleUpdated.capacity).isEqualTo(4);
        assertThat(vehicleUpdated.type).isEqualTo(VehicleType.COUPE);
        assertThat(vehicleUpdated.description).isEqualTo("Un auto lindo y amplio");
        assertThat(vehicleUpdated.photo).isEqualTo("https://autito_nuevo.jpg");
        assertThat(vehicleUpdated.owner.getId()).isEqualTo(user.getId());
    }


    @Test
    public void testCreateVehicleForUserWithInvalidData() {

        User user = UserBuilder.someUser();
        this.userService.save(user);

        VehicleForm vehicle = VehicleBuilder.start()
                .withCapacity(3)
                .withDescription("Un l")
                .withPhoto("httito.jpg")
                .withType(VehicleType.SEDAN)
                .buildForm();
        try {
            publicationService.createVehicleForUser(user.getId(), vehicle);

            List vehicleList = this.publicationService.getVehiclesForUser(user.getId());

        } catch (FormValidationError validationError) {

            Map<String, String> errors = validationError.errors;
            assertThat(errors.get("photo")).isEqualTo("must be valid HTTP URL");
            assertThat(errors.get("description")).isEqualTo("size must be between 10 and 30");
        }

    }

    @Test
    public void testDeleteVehicle() throws FormValidationError {

        User user = UserBuilder.someUser();
        this.userService.save(user);

        for (VehicleForm each : createTwoVehicles()) publicationService.createVehicleForUser(user.getId(), each);

        List<Vehicle> vehicleList = this.publicationService.getVehiclesForUser(user.getId());

        this.publicationService.deleteVehicle(user.getId(), vehicleList.get(0).getId());

        List<Vehicle> vehicleListAfterRemove = this.publicationService.getVehiclesForUser(user.getId());

        assertThat(vehicleListAfterRemove.size()).isEqualTo(1);
        assertThat(vehicleList.get(1)).isEqualTo(vehicleListAfterRemove.get(0));

    }

    private List<VehicleForm> createTwoVehicles() {
        VehicleForm firstVehicleForm = VehicleBuilder.start()
                .withCapacity(3)
                .withDescription("Un lindo auto sedan")
                .withPhoto("https://autito.jpg")
                .withType(VehicleType.SEDAN)
                .buildForm();

        VehicleForm secondVehicleForm = VehicleBuilder.start()
                .withCapacity(6)
                .withDescription("Un lindo auto copupe")
                .withPhoto("https://autito.jpg")
                .withType(VehicleType.COUPE)
                .buildForm();
        LinkedList<VehicleForm> resultList = new LinkedList<>();
        resultList.add(firstVehicleForm);
        resultList.add(secondVehicleForm);
        return resultList;
    }


}
