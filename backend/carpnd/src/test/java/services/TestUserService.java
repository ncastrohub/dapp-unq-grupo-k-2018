package services;
import api.DETEOS.UserForm;
import api.DETEOS.UserUpdateForm;
import api.forms.VehicleForm;
import model.User;
import model.exceptions.FormValidationError;
import org.junit.Test;
import utils.builders.UserBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)


public class TestUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationConcernService publicationService;

    @Test
    public void testCreateUser() throws FormValidationError {

        User user = UserBuilder.someUser();
        this.userService.save(user);

        User userBB = UserBuilder.start()
                .withName("Nazareno")
                .withLastName("Castro")
                .withCUIL("23232323")
                .withEmail("ncastro@gmail.com")
                .build();

        UserUpdateForm userForm = new UserUpdateForm();
        userForm.name="Nazareno";
        userForm.lastName="Castro";
        userForm.cuil= "ncastro@gmail.com";

        this.publicationService.updateUser(userForm);

        assertThat(userForm.name).isEqualTo("Nazareno");
        assertThat(userForm.lastName).isEqualTo("Castro");
        assertThat(userForm.cuil).isEqualTo("23232323");
        assertThat(userForm.email).isEqualTo("ncastro@gmail.com");
    }

    @Test
    public void testDeleteUser() throws FormValidationError {

        User user = UserBuilder.someUser();
        this.userService.save(user);

        UserUpdateForm userForm = new UserUpdateForm();
        userForm.name="Nazareno";
        userForm.lastName="Castro";
        userForm.cuil= "ncastro@gmail.com";

        this.publicationService.updateUser(userForm);

        List<User> userCant = this.publicationService.getUsers();

        this.publicationService.deleteUser(user.getId());

        List<User> UserAfterRemove = this.publicationService.getUsers();

        assertThat(UserAfterRemove.size()).isEqualTo(1);

        assertThat(userCant.get(1)).isEqualTo(UserAfterRemove.get(0));

    }

    @Test
    public void testEditUser() throws FormValidationError {

        User user = UserBuilder.someUser();
        this.userService.save(user);

        UserUpdateForm userForm = new UserUpdateForm();
        userForm.name="Nazareno";
        userForm.lastName="Castro";
        userForm.cuil= "ncastro@gmail.com";

        this.publicationService.updateUser(userForm);

        UserUpdateForm updateForm = new UserUpdateForm();
        updateForm.name = "Julia";
        updateForm.lastName = "Troilo";
        updateForm.cuil = "9922992299";
        updateForm.email = "julia.troilo@unq.edu.ar";

        assertThat(userForm.name).isEqualTo("Julia");
        assertThat(userForm.lastName).isEqualTo("Troilo");
        assertThat(userForm.cuil).isEqualTo("9922992299");
        assertThat(userForm.email).isEqualTo("julia.troilo@unq.edu.ar");
    }
}
