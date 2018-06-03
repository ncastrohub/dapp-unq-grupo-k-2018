package services;


import api.forms.PublicationForm;
import model.Publication;
import model.Reservation;
import model.User;
import model.exceptions.DayAlreadyReservedException;
import model.exceptions.DayDisabledException;
import model.exceptions.FormValidationError;
import model.exceptions.InvalidAmountOfDaysToReserveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.builders.PublicationFormBuilder;
import utils.builders.UserBuilder;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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
        assertThat(createdPublication.getReservedDays()).isEqualTo(PublicationOnDb.getReservedDays());
        assertThat(createdPublication.getCostPerHour().amount).isEqualTo(PublicationOnDb.getCostPerHour().amount);
        assertThat(createdPublication.getCostPerHour().currency).isEqualTo(PublicationOnDb.getCostPerHour().currency);

    }


    @Test
    public void testMakeReservationOfAPublication() throws FormValidationError, DayDisabledException, DayAlreadyReservedException, InvalidAmountOfDaysToReserveException {

        User owner = UserBuilder.someUser();
        User customer = UserBuilder.someUser();

        List<LocalDate> daysToReserve = new LinkedList<>();
        daysToReserve.add(LocalDate.now().plusDays(6));
        daysToReserve.add(LocalDate.now().plusDays(7));
        daysToReserve.add(LocalDate.now().plusDays(8));

        this.userService.save(owner);
        PublicationForm publicationForm = PublicationFormBuilder.some();
        Publication createdPublication = this.publicationService.createPublicationForUser(owner.getId(), publicationForm);

        for (LocalDate eachDay: daysToReserve){
            assertThat(createdPublication.canReserve(eachDay)).isTrue();
        }

        Reservation cratedReservation = this.publicationService.makeReservation(
                createdPublication.getId(),
                daysToReserve,
                customer.getId(),
                createdPublication.getReturnLocations().get(0).getId()
        );

        for (LocalDate eachDay: daysToReserve){
            assertThat(createdPublication.canReserve(eachDay)).isFalse();
        }

        assertThat(createdPublication.getReservedDays()).containsAll(daysToReserve);
        assertThat(cratedReservation.publication.publication.getId()).isEqualTo(createdPublication.getId());
        assertThat(cratedReservation.getOwner().getId()).isEqualTo(owner.getId());
        assertThat(cratedReservation.getCustomer().getId()).isEqualTo(customer.getId());
        assertThat(createdPublication.getReturnLocations()).contains(cratedReservation.returnLocation);
        assertThat(createdPublication.getAcquireLocation().getId()).isEqualTo(cratedReservation.acquireLocation.getId());


        /*
        * public ReservedPublication publication;
    public AdressLocation acquireLocation;
    public LocalDateTime acquireTime;
    public LocalDateTime returnTime;
    public ReservationState state;
    public AdressLocation returnLocation;
    public User owner;
    public User customer;
        * */

    }


}
