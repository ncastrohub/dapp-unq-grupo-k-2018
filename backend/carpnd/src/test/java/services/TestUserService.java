package services;

import api.forms.UserForm;
import api.forms.UserUpdateForm;
import model.User;
import model.exceptions.FormValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.builders.UserBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-test.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestUserService {

    @Autowired
    private PublishService publicationService;

    @Test
    public void testCreateUser() throws FormValidationError {

        UserForm user = UserBuilder.start().withName("Nazareno").withLastName("Castro")
                .withEmail("nazarenomartincastro@gmail.com")
                .withCUIL("1212312337")
                .buildForm();
        User createdUser = this.publicationService.createUser(user);
        User userOnDb = this.publicationService.retrieveUser(createdUser.getId());

        assertThat(createdUser.getId()).isEqualTo(userOnDb.getId());
        assertThat(createdUser.name).isEqualTo(userOnDb.name);
        assertThat(createdUser.lastName).isEqualTo(userOnDb.lastName);
        assertThat(createdUser.cuil).isEqualTo(userOnDb.cuil);
        assertThat(createdUser.email).isEqualTo(userOnDb.email);

    }

    @Test
    public void testDeleteUser() throws FormValidationError {

        UserForm user = UserBuilder.start().withName("Nazareno").withLastName("Castro")
                .withEmail("nazarenomartincastro@gmail.com")
                .withCUIL("1212312337")
                .buildForm();
        User createdUser = this.publicationService.createUser(user);
        this.publicationService.deleteUser(createdUser.getId());

        List<User> userListAfterRemove = this.publicationService.getUsers();
        assertThat(userListAfterRemove.size()).isEqualTo(0);

    }

    @Test
    public void testUpdateUser() throws FormValidationError {

        UserForm user = UserBuilder.start().withName("Nazareno").withLastName("Castro")
                .withEmail("nazarenomartincastro@gmail.com")
                .withCUIL("1212312337")
                .buildForm();
        User createdUser = this.publicationService.createUser(user);

        UserUpdateForm updateForm = new UserUpdateForm();
        updateForm.name = "Super Nachito";
        updateForm.lastName = "El mejor";
        updateForm.cuil = "0000000000";
        updateForm.email = "user@gmail.com";
        updateForm.id = createdUser.getId();

        this.publicationService.updateUser(updateForm);

        User userUpdated = this.publicationService.retrieveUser(createdUser.getId());

        assertThat(userUpdated.name).isEqualTo("Super Nachito");
        assertThat(userUpdated.lastName).isEqualTo("El mejor");
        assertThat(userUpdated.cuil).isEqualTo("0000000000");
        assertThat(userUpdated.email).isEqualTo("user@gmail.com");

    }
}
