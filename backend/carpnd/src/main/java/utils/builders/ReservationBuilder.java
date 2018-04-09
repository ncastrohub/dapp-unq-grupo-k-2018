package utils.builders;

import model.*;

import java.time.LocalDateTime;

public class ReservationBuilder {
    private User owner;
    private User customer;
    private LocalDateTime acquireTime;
    private AdressLocation acquireLocation;
    private LocalDateTime returnTime;
    private AdressLocation returnLocation;
    private ReservedPublication publication;

    public static ReservationBuilder start() {
        return new ReservationBuilder();
    }

    public ReservationBuilder withOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Reservation build() {
        return new Reservation(owner, customer, acquireTime, acquireLocation, returnTime, returnLocation, publication);
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

    public ReservationBuilder withPublication(ReservedPublication publication) {
        this.publication = publication;
        return this;
    }
}
