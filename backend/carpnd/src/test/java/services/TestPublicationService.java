package services;


import api.forms.PublicationForm;
import model.Publication;
import model.User;
import model.exceptions.FormValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.builders.PublicationFormBuilder;
import utils.builders.UserBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-test.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestPublicationService {


    @Autowired
    private PublishService publicationService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreatePublication() throws FormValidationError {

        User user = UserBuilder.someUser();

        this.userService.save(user);

        PublicationForm publicationForm = PublicationFormBuilder.some();

        Publication createdPublication = this.publicationService.createPublicationForUser(user.getId(), publicationForm);

        Publication PublicationOnDb = this.publicationService.retrievePublication(createdPublication.getId());

        assertThat(createdPublication.getVehicle()).isEqualTo(PublicationOnDb.getVehicle());
        assertThat(createdPublication.getReturnLocations().size()).isEqualTo(PublicationOnDb.getReturnLocations().size());
        assertThat(createdPublication.getAcquireLocation().getId()).
                isEqualTo(PublicationOnDb.getAcquireLocation().getId());
        assertThat(createdPublication.getDisabledDays()).isEqualTo(PublicationOnDb.getDisabledDays());
        assertThat(createdPublication.getReservedDays()).isEqualTo(PublicationOnDb.getReservedDays());

    }


}
