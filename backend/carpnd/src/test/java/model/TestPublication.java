package model;

import model.exceptions.*;
import org.junit.Test;
import utils.builders.PublicationBuilder;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

//import static org.junit.jupiter.api.Assertions.*;


public class TestPublication {

    @Test
    public void testPublicationCreation(){

        User owner = mock(User.class);
        MoneyAndAmount pricePerHour = mock(MoneyAndAmount.class);
        Vehicle car = mock(Vehicle.class);
        AdressLocation acquirePlace = mock(AdressLocation.class);

        LinkedList<AdressLocation> returnLocations = new LinkedList<>();
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));

        PublicationsEnabledDays availableDays = mock(PublicationsEnabledDays.class);
        Telephone telephone = mock(Telephone.class);

        Publication publication = PublicationBuilder.start()
                .withOwner(owner)
                .withPricePerHour(pricePerHour)
                .withVehicle(car)
                .withAcquireLocation(acquirePlace)
                .withRestoreLocations(returnLocations)
                .withAvaibleDays(availableDays)
                .withContactPhone(telephone)
                .build();

        assertThat(publication.getOwner()).isEqualTo(owner);
        assertThat(publication.getCostPerHour()).isEqualTo(pricePerHour);
        assertThat(publication.getVehicle()).isEqualTo(car);
        assertThat(publication.getAcquireLocation()).isEqualTo(acquirePlace);
        assertThat(publication.getReturnLocations()).isEqualTo(returnLocations);
        assertThat(publication.getAvaiblesDays()).isEqualTo(availableDays);

    }


    @Test
    public void testWhenReserveVehicleForSomeDaysAPublicationReservedIsCreated() throws DayAlreadyReservedException, DayDisabledException, InvalidAmountOfDaysToReserveException {

        User owner = mock(User.class);
        User customer = mock(User.class);

        MoneyAndAmount pricePerHour = mock(MoneyAndAmount.class);
        Vehicle car = mock(Vehicle.class);
        AdressLocation acquirePlace = mock(AdressLocation.class);

        LinkedList<AdressLocation> returnLocations = new LinkedList<>();
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));

        PublicationsEnabledDays availableDays = mock(PublicationsEnabledDays.class);
        Telephone telephone = mock(Telephone.class);

        Publication publication = PublicationBuilder.start()
                .withOwner(owner)
                .withPricePerHour(pricePerHour)
                .withVehicle(car)
                .withAcquireLocation(acquirePlace)
                .withRestoreLocations(returnLocations)
                .withAvaibleDays(availableDays)
                .withContactPhone(telephone)
                .build();

        LinkedList<LocalDate> reservationDays = new LinkedList<>();
        LocalDate dayOne = LocalDate.now().plusDays(1);
        LocalDate dayTwo = LocalDate.now().plusDays(2);
        LocalDate dayThree = LocalDate.now().plusDays(3);
        reservationDays.add(dayOne);
        reservationDays.add(dayTwo);
        reservationDays.add(dayThree);

        publication.makeReservation(customer, reservationDays);
        ReservedPublication reservedPublication = publication.getReservedPublicationList().get(0);
        assertThat(reservedPublication.getCustomer()).isEqualTo(customer);
        assertThat(reservedPublication.getReservedDays()).isEqualTo(reservationDays);
    }

    @Test
    public void testWhenPublicationIsReservedDaysReservedAreNotAvailable() throws DayAlreadyReservedException, DayDisabledException, InvalidAmountOfDaysToReserveException {
        User owner = mock(User.class);
        User customer = mock(User.class);

        MoneyAndAmount pricePerHour = mock(MoneyAndAmount.class);
        Vehicle car = mock(Vehicle.class);
        AdressLocation acquirePlace = mock(AdressLocation.class);

        LinkedList<AdressLocation> returnLocations = new LinkedList<>();
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));

        PublicationsEnabledDays availableDays = new PublicationsEnabledDays();
        Telephone telephone = mock(Telephone.class);

        Publication publication = PublicationBuilder.start()
                .withOwner(owner)
                .withPricePerHour(pricePerHour)
                .withVehicle(car)
                .withAcquireLocation(acquirePlace)
                .withRestoreLocations(returnLocations)
                .withAvaibleDays(availableDays)
                .withContactPhone(telephone)
                .build();

        LinkedList<LocalDate> reservationDays = new LinkedList<>();
        LocalDate dayOne = LocalDate.now().plusDays(1);
        LocalDate dayTwo = LocalDate.now().plusDays(2);
        LocalDate dayThree = LocalDate.now().plusDays(3);
        reservationDays.add(dayOne);
        reservationDays.add(dayTwo);
        reservationDays.add(dayThree);

        publication.makeReservation(customer, reservationDays);

        assertThat(publication.canReserve(dayOne)).isFalse();
        assertThat(publication.canReserve(dayTwo)).isFalse();
        assertThat(publication.canReserve(dayThree)).isFalse();


    }


    @Test
    public void testWhenReservedDaysAreReleasedThatDaysCanBeReservedAgain() throws DayAlreadyReservedException, DayDisabledException, DayNotReservedException, InvalidAmountOfDaysToReserveException {
        User owner = mock(User.class);
        User customer = mock(User.class);

        MoneyAndAmount pricePerHour = mock(MoneyAndAmount.class);
        Vehicle car = mock(Vehicle.class);
        AdressLocation acquirePlace = mock(AdressLocation.class);

        LinkedList<AdressLocation> returnLocations = new LinkedList<>();
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));

        PublicationsEnabledDays availableDays = new PublicationsEnabledDays();
        Telephone telephone = mock(Telephone.class);

        Publication publication = PublicationBuilder.start()
                .withOwner(owner)
                .withPricePerHour(pricePerHour)
                .withVehicle(car)
                .withAcquireLocation(acquirePlace)
                .withRestoreLocations(returnLocations)
                .withAvaibleDays(availableDays)
                .withContactPhone(telephone)
                .build();

        LinkedList<LocalDate> reservationDays = new LinkedList<>();
        LocalDate dayOne = LocalDate.now().plusDays(1);
        LocalDate dayTwo = LocalDate.now().plusDays(2);
        LocalDate dayThree = LocalDate.now().plusDays(3);
        reservationDays.add(dayOne);
        reservationDays.add(dayTwo);
        reservationDays.add(dayThree);

        publication.makeReservation(customer, reservationDays);

        publication.releaseDays(reservationDays);

        assertThat(publication.canReserve(dayOne)).isTrue();
        assertThat(publication.canReserve(dayTwo)).isTrue();
        assertThat(publication.canReserve(dayThree)).isTrue();


    }

    @Test
    public void testAVehicleCannotBeReservedFormMoreThanFiveDaysAndLessThanOne() {
        User owner = mock(User.class);
        User customer = mock(User.class);

        MoneyAndAmount pricePerHour = mock(MoneyAndAmount.class);
        Vehicle car = mock(Vehicle.class);
        AdressLocation acquirePlace = mock(AdressLocation.class);

        LinkedList<AdressLocation> returnLocations = new LinkedList<>();
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));

        PublicationsEnabledDays availableDays = new PublicationsEnabledDays();
        Telephone telephone = mock(Telephone.class);

        Publication publication = PublicationBuilder.start()
                .withOwner(owner)
                .withPricePerHour(pricePerHour)
                .withVehicle(car)
                .withAcquireLocation(acquirePlace)
                .withRestoreLocations(returnLocations)
                .withAvaibleDays(availableDays)
                .withContactPhone(telephone)
                .build();

        LinkedList<LocalDate> reservationDays = new LinkedList<>();
        LocalDate dayOne = LocalDate.now().plusDays(1);
        LocalDate dayTwo = LocalDate.now().plusDays(2);
        LocalDate dayThree = LocalDate.now().plusDays(3);
        LocalDate dayFour = LocalDate.now().plusDays(4);
        LocalDate dayFive = LocalDate.now().plusDays(5);
        LocalDate daySix = LocalDate.now().plusDays(6);

        reservationDays.add(dayOne);
        reservationDays.add(dayTwo);
        reservationDays.add(dayThree);
        reservationDays.add(dayFour);
        reservationDays.add(dayFive);
        reservationDays.add(daySix);


        assertThrows(InvalidAmountOfDaysToReserveException.class, ()-> publication.makeReservation(customer, reservationDays));

    }

    @Test
    public void testAsOwnerIWantToDisabledSomeDaysToCannotBeReserved() throws DayAlreadyDisabledException {
        User owner = mock(User.class);
        User customer = mock(User.class);

        MoneyAndAmount pricePerHour = mock(MoneyAndAmount.class);
        Vehicle car = mock(Vehicle.class);
        AdressLocation acquirePlace = mock(AdressLocation.class);

        LinkedList<AdressLocation> returnLocations = new LinkedList<>();
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));
        returnLocations.add(mock(AdressLocation.class));

        PublicationsEnabledDays availableDays = new PublicationsEnabledDays();
        Telephone telephone = mock(Telephone.class);

        Publication publication = PublicationBuilder.start()
                .withOwner(owner)
                .withPricePerHour(pricePerHour)
                .withVehicle(car)
                .withAcquireLocation(acquirePlace)
                .withRestoreLocations(returnLocations)
                .withAvaibleDays(availableDays)
                .withContactPhone(telephone)
                .build();

        LinkedList<LocalDate> reservationDays = new LinkedList<>();

        LocalDate dayOne = LocalDate.now().plusDays(1);
        LocalDate dayTwo = LocalDate.now().plusDays(2);
        LocalDate dayThree = LocalDate.now().plusDays(3);
        reservationDays.add(dayOne);
        reservationDays.add(dayTwo);
        reservationDays.add(dayThree);


        publication.disabledDays(reservationDays);

        assertThrows(DayDisabledException.class, ()-> publication.makeReservation(customer, reservationDays));
    }

    /**/


}
