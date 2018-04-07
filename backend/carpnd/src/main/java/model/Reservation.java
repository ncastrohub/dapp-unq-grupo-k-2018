package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Reservation {

    public ReservedPublication publication;
    public AdressLocation acquireLocation;
    public LocalDateTime acquireTime;
    public LocalDateTime returnTime;
    public ReservationState state;
    public AdressLocation returnLocation;

    public Reservation(
            User owner,
            User customer,
            LocalDateTime acquireTime, AdressLocation acquireLocation,
            LocalDateTime returnTime, AdressLocation returnLocation,
            ReservedPublication publication) {
        this.owner = owner;
        this.customer = customer;
        this.state = new WaitingForOwnerState();
        this.acquireTime = acquireTime;
        this.acquireLocation = acquireLocation;
        this.returnTime = returnTime;
        this.returnLocation = returnLocation;
        this.publication = publication;
    }

    public User owner;
    public User customer;

    public MoneyAndAmount finalPrice() {
        MoneyAndAmount costPerHour = this.publication.getCostPerHour();
        Long hours = acquireTime.until( returnTime, ChronoUnit.HOURS);
        Double roundedValue = hours.doubleValue();
        return costPerHour.plusBy(roundedValue);
    }
}
