package model;

import model.Exceptions.DayAlreadyReservedException;
import model.Exceptions.DayDisabledException;
import org.junit.Test;
import utils.builders.PublicationBuilder;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

//import static org.junit.jupiter.api.Assertions.*;


public class TestPublication {


    /*Que tengo que poder hacer con una publicacion
    *
    * yo como comprador quiero poder reservar para ciertos dias
    * la publicacion tiene que tener registro de los dias que se van ocupando
    * la publicacion tiene que poder liberar dias
    * yo como vendedor tengo que poder decidir que dias no habilito la publicacion
    * yo como vendedor tengo que poder modificar el precio
    * yo como vendedor solo puedo alquilar un auto por cinco dias
    * yo como vendedor quiero poder cerrar una publicacion*/


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
    public void testWhenReserveVehicleForSomeDaysAPublicationReservedIsCreated() throws DayAlreadyReservedException, DayDisabledException {

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
    public void testWhenPublicationIsReservedDaysReservedAreNotAvailable() throws DayAlreadyReservedException, DayDisabledException {
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

        assertThat(publication.canReserve(dayOne)).isFalse();
        assertThat(publication.canReserve(dayTwo)).isFalse();
        assertThat(publication.canReserve(dayThree)).isFalse();


    }

    @Test
    public void testAVehicleCannotBeReservedFormMoreThanFiveDaysAndLessThanOne(){

    }

}
