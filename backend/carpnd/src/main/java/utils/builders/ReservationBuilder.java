package utils.builders;

import model.AdressLocation;
import model.Reservation;
import model.User;

import java.time.LocalDateTime;

public class ReservationBuilder {
    private User owner;
    private User customer;
    private LocalDateTime acquireTime;
    private AdressLocation acquireLocation;
    private LocalDateTime returnTime;
    private AdressLocation returnLocation;

    public static ReservationBuilder start() {
        return new ReservationBuilder();
    }

    public ReservationBuilder withOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Reservation build() {
        return new Reservation(owner, customer, acquireTime, acquireLocation, returnTime, returnLocation);
    }

    public ReservationBuilder withCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public ReservationBuilder withAcquireTime(LocalDateTime acquireTime) {
        this.acquireTime = acquireTime;
        return this;
    }

    public ReservationBuilder withAcquireLocation(AdressLocation acquireLocation) {
        this.acquireLocation = acquireLocation;
        return this;
    }

    public ReservationBuilder withReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
        return this;
    }

    public ReservationBuilder withReturnLocation(AdressLocation returnLocation) {
        this.returnLocation = returnLocation;
        return this;
    }
}
