package model;
import org.junit.Test;
import utils.builders.ReservationBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestReservation {


//    Que la reserva se crea con un usuario dueño
//    Que la reserva se crea con un usuario comprador
//    Que la reserva se crea en estado Wayting for owner
//    Que se crea con el tiempo de acquireTime
//    Que el totalTime y el return time estan en blanco
//    Que se crea con el acquire location
//    Que se crea con el return location

    @Test
    public void reservationCreatedWithUserOwner() {
        User owner = mock(User.class);
        Reservation reservation = ReservationBuilder.start()
            .withOwner(owner)
            .build();
        assertThat(reservation.owner).isEqualTo(owner);
    }

    @Test
    public void reservationCreatedWithUserCustomer() {
        User customer = mock(User.class);
        Reservation reservation = ReservationBuilder.start()
                .withCustomer(customer)
                .build();
        assertThat(reservation.customer).isEqualTo(customer);
    }

}
