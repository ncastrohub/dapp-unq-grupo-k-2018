package services;

import api.forms.UserForm;
import model.User;
import model.exceptions.FormValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.builders.UserBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestUserService {

    @Autowired
    private PublicationConcernService publicationService;

    @Test
    public void testCreateUser() throws FormValidationError {

        UserForm user = UserBuilder.start().withName("Nazareno").withLastName("Castro")
                .withEmail("nazarenomartincastro@gmail.com")
                .withCUIL("1212312337")
                .buildForm();
        User createdUser = this.publicationService.createUser(user);
        User userOnDb = this.publicationService.retriveUser(createdUser.getId());

        assertThat(createdUser.getId()).isEqualTo(userOnDb.getId());
        assertThat(createdUser.name).isEqualTo(userOnDb.name);
        assertThat(createdUser.lastName).isEqualTo(userOnDb.lastName);
        assertThat(createdUser.cuil).isEqualTo(userOnDb.cuil);
        assertThat(createdUser.email).isEqualTo(userOnDb.email);

    }
}
