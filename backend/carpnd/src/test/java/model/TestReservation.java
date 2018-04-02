package model;
import org.junit.Test;
import utils.builders.ReservationBuilder;

import java.sql.Time;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestReservation {

    @Test
    public void reservationCreatedCorrectly() {
        User owner = mock(User.class);
        User customer = mock(User.class);
        LocalDateTime acquireTime = LocalDateTime.now().minusDays(3);
        LocalDateTime returnTime = LocalDateTime.now().plusDays(7);
        AdressLocation acquireLocation = mock(AdressLocation.class);
        AdressLocation returnLocation = mock(AdressLocation.class);
        Reservation reservation = ReservationBuilder.start()
            .withOwner(owner)
            .withCustomer(customer)
            .withAcquireTime(acquireTime)
            .withAcquireLocation(acquireLocation)
            .withReturnTime(returnTime)
            .withReturnLocation(returnLocation)
            .build();

        assertThat(reservation.owner).isEqualTo(owner);
        assertThat(reservation.customer).isEqualTo(customer);
        assertThat(reservation.owner).isNotEqualTo(customer);
        assertThat(reservation.customer).isNotEqualTo(owner);
        assertThat(reservation.state).isInstanceOf(WaitingForOwnerState.class);

        assertThat(reservation.acquireTime).isEqualTo(acquireTime);
        assertThat(reservation.returnTime).isEqualTo(returnTime);
        assertThat(reservation.acquireLocation).isEqualTo(acquireLocation);
        assertThat(reservation.returnLocation).isEqualTo(returnLocation);

    }


}
