package services;

import model.User;
import model.exceptions.FormValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.builders.UserBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)


public class TestUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationConcernService publicationService;

    @Test
    public void testGetUserForUser() throws FormValidationError {

        User user = UserBuilder.someUser();
        this.userService.save(user);

    }
}
