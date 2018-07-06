package model;

import org.junit.Test;
import utils.builders.ReservationBuilder;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestReservation {

    @Test
    public void reservationCreatedCorrectly() {
        User owner = mock(User.class);
        User customer = mock(User.class);
        AdressLocation acquireLocation = mock(AdressLocation.class);
        AdressLocation returnLocation = mock(AdressLocation.class);

        List<LocalDate> reservedDays = new LinkedList<>();
        reservedDays.add(LocalDate.now().plusDays(3));
        reservedDays.add(LocalDate.now().plusDays(4));
        reservedDays.add(LocalDate.now().plusDays(5));
        reservedDays.add(LocalDate.now().plusDays(6));

        Publication publication = mock(Publication.class);
        MoneyAndAmount cost = new MoneyAndAmount(10.00, CustomCurrencies.ARS);
//        when(publication.getCostPerHour()).thenReturn(cost);
        when(publication.getAcquireLocation()).thenReturn(acquireLocation);
        when(acquireLocation.createNew()).thenReturn(acquireLocation);


        Reservation reservation = ReservationBuilder.start()
            .withCustomer(customer)
            .withReturnLocation(returnLocation)
            .withPublication(publication)
            .withReservedDays(reservedDays)
            .build();

        assertThat(reservation.customer).isEqualTo(customer);
        assertThat(reservation.customer).isNotEqualTo(owner);
//        TODO: contemplar el estado en la reserva
//        assertThat(reservation.).isInstanceOf(WaitingForOwnerState.class);
        assertThat(reservation.publication).isEqualTo(publication);
    }

    @Test
    public void reservationFinalPriceReturnThePriceCalculatedForTheTimeTranscurred(){

        User customer = mock(User.class);
        AdressLocation acquireLocation = mock(AdressLocation.class);
        AdressLocation returnLocation = mock(AdressLocation.class);

        List<LocalDate> reservedDays = new LinkedList<>();
        reservedDays.add(LocalDate.now().plusDays(3));
        reservedDays.add(LocalDate.now().plusDays(4));
        reservedDays.add(LocalDate.now().plusDays(5));
        reservedDays.add(LocalDate.now().plusDays(6));

        Publication publication = mock(Publication.class);
        MoneyAndAmount cost = new MoneyAndAmount(10.00, CustomCurrencies.ARS);
//        when(publication.getCostPerHour()).thenReturn(cost);
        when(publication.getAcquireLocation()).thenReturn(acquireLocation);
        when(acquireLocation.createNew()).thenReturn(acquireLocation);

        Reservation reservation = spy(ReservationBuilder.start()
                .withCustomer(customer)
                .withReturnLocation(returnLocation)
                .withPublication(publication)
                .withReservedDays(reservedDays)
                .build());

        ReservedPublication publicationSnapshot = mock(ReservedPublication.class);
        when(publicationSnapshot.getHoursBetween()).thenReturn(7 * 24);
        when(publicationSnapshot.getCostPerHour()).thenReturn(cost);
        when(reservation.getPublicationSnapshot()).thenReturn(publicationSnapshot);
        assertThat(reservation.finalPrice().amount).isEqualTo( 7 * 24 * 10);
    }

}
