package services;


import api.forms.PublicationForm;
import model.Publication;
import model.Reservation;
import model.User;
import model.VehicleType;
import model.exceptions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.OwnPaginationPage;
import utils.builders.PublicationFormBuilder;
import utils.builders.UserBuilder;

import org.joda.time.LocalDate;
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
    public void testMakeReservationOfAPublication() throws FormValidationError, DayDisabledException, DayAlreadyReservedException, InvalidAmountOfDaysToReserveException, NoReturnLocationInPublicationException {

        User owner = UserBuilder.someUser();
        User customer = UserBuilder.someUser();

        List<LocalDate> daysToReserve = new LinkedList<>();
        daysToReserve.add(LocalDate.now().plusDays(3));
        daysToReserve.add(LocalDate.now().plusDays(4));
        daysToReserve.add(LocalDate.now().plusDays(5));

        this.userService.save(owner);
        this.userService.save(customer);
        PublicationForm publicationForm = PublicationFormBuilder.some();
        Publication createdPublication = this.publicationService.createPublicationForUser(owner.getId(), publicationForm);

        for (LocalDate eachDay: daysToReserve){
            assertThat(createdPublication.canReserve(eachDay)).isTrue();
        }

        assertThat(createdPublication.getId()).isNotNull();

        Reservation cratedReservation = this.publicationService.makeReservation(
                customer.getId(),
                daysToReserve,
                createdPublication.getId(),
                createdPublication.getReturnLocations().get(0).getId()
        );

        Publication publicationOnDb = this.publicationService.retrievePublication(createdPublication.getId());

        for (LocalDate eachDay: daysToReserve){
            assertThat(publicationOnDb.canReserve(eachDay)).isFalse();
        }

        assertThat(publicationOnDb.getReservedDays()).containsAll(daysToReserve);
        assertThat(cratedReservation.publication.getId()).isEqualTo(createdPublication.getId());
        assertThat(cratedReservation.getOwner().getId()).isEqualTo(owner.getId());
        assertThat(cratedReservation.getCustomer().getId()).isEqualTo(customer.getId());
        assertThat(publicationOnDb.getReturnLocationsById(cratedReservation.getPublicationSnapshot().returnLocation.getId())).isNotNull();
        assertThat(publicationOnDb.getAcquireLocation().getId()).isNotEqualTo(cratedReservation.getPublicationSnapshot().acquireLocation.getId());
    }

    @Test
    public void testSearchPublicationOnPagination() throws FormValidationError {

        User owner = UserBuilder.someUser();
        this.userService.save(owner);
        this.publicationService.createPublicationForUser(owner.getId(), PublicationFormBuilder.some());
        this.publicationService.createPublicationForUser(owner.getId(), PublicationFormBuilder.some());
        this.publicationService.createPublicationForUser(owner.getId(), PublicationFormBuilder.some());
        this.publicationService.createPublicationForUser(owner.getId(), PublicationFormBuilder.some());

        PublicationForm secondPublicationForm = PublicationFormBuilder.some();
        secondPublicationForm.vehicle.type = VehicleType.SEDAN;
        this.publicationService.createPublicationForUser(owner.getId(), secondPublicationForm);

        PublicationForm thirdPublicationForm = PublicationFormBuilder.some();
        thirdPublicationForm.vehicle.type = VehicleType.VAN;
        this.publicationService.createPublicationForUser(owner.getId(), thirdPublicationForm);


        OwnPaginationPage<Publication> page = this.publicationService.getPublicationService().getPaginationPage();

        assertThat(page.beforeUrl).isNull();
        assertThat(page.elementList).size().isEqualTo(4);
        assertThat(page.nextUrl).isEqualTo("/publication/list/1");

    }



}
