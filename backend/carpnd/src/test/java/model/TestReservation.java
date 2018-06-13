package model;

import org.junit.Test;
import utils.builders.ReservationBuilder;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TestReservation {

    @Test
    public void reservationCreatedCorrectly() {
        User owner = mock(User.class);
        User customer = mock(User.class);

        LocalDateTime acquireTime = LocalDateTime.now().minusDays(3);
        LocalDateTime returnTime = LocalDateTime.now().plusDays(7);

        AdressLocation acquireLocation = mock(AdressLocation.class);
        AdressLocation returnLocation = mock(AdressLocation.class);
        Publication publication = mock(Publication.class);

        List<LocalDate> reservedDays = new LinkedList<>();
        reservedDays.add(LocalDate.now().plusDays(3));
        reservedDays.add(LocalDate.now().plusDays(4));
        reservedDays.add(LocalDate.now().plusDays(5));
        reservedDays.add(LocalDate.now().plusDays(6));

        Reservation reservation = ReservationBuilder.start()
            .withCustomer(customer)
            .withReturnLocation(returnLocation)
            .withPublication(publication)
            .withReservedDays(reservedDays)
            .build();

        assertThat(reservation.customer).isEqualTo(customer);
        assertThat(reservation.customer).isNotEqualTo(owner);
        assertThat(reservation.state).isInstanceOf(WaitingForOwnerState.class);
        assertThat(reservation.publication).isEqualTo(publication);
    }

    @Test
    public void reservationFinalPriceReturnThePriceCalculatedForTheTimeTranscurred(){

        User owner = mock(User.class);
        User customer = mock(User.class);
        LocalDateTime acquireTime = LocalDateTime.now().minusDays(3);
        LocalDateTime returnTime = acquireTime.plusDays(7);
        AdressLocation acquireLocation = mock(AdressLocation.class);
        AdressLocation returnLocation = mock(AdressLocation.class);

        List<LocalDate> reservedDays = new LinkedList<>();
        reservedDays.add(LocalDate.now().plusDays(3));
        reservedDays.add(LocalDate.now().plusDays(4));
        reservedDays.add(LocalDate.now().plusDays(5));
        reservedDays.add(LocalDate.now().plusDays(6));


        Publication publication = mock(Publication.class);

        when(publication.getCostPerHour()).thenReturn( new MoneyAndAmount(10.00, CustomCurrencies.ARS));
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
        assertThat(publicationSnapshot.getHoursBetween()).isEqualTo(7 * 24);

        when(publicationSnapshot.getAcquireTime()).thenReturn(acquireTime);
        when(publicationSnapshot.getReturnTime()).thenReturn(acquireTime);
        when(publicationSnapshot.getCostPerHour()).thenReturn(new MoneyAndAmount(10.00, CustomCurrencies.ARS));

        when(reservation.getPublicationSnapshot()).thenReturn(publicationSnapshot);
//        Hours.hoursBetween(this.publicationSnapshot.getAcquireTime(), this.publicationSnapshot.returnTime).getHours();

        MoneyAndAmount priceExpected = new MoneyAndAmount((7 * 24) * 10.00, CustomCurrencies.ARS);

        assertThat(reservation.finalPrice().amount).isEqualTo(priceExpected.amount);
    }

}
