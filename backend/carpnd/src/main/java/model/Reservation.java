package model;

import java.time.LocalDateTime;

public class Reservation {

    public AdressLocation acquireLocation;
    public LocalDateTime acquireTime;
    public LocalDateTime returnTime;
    public ReservationState state;
    public AdressLocation returnLocation;

    public Reservation(User owner, User customer, LocalDateTime acquireTime, AdressLocation acquireLocation, LocalDateTime returnTime, AdressLocation returnLocation) {
        this.owner = owner;
        this.customer = customer;
        this.state = new WaitingForOwnerState();
        this.acquireTime = acquireTime;
        this.acquireLocation = acquireLocation;
        this.returnTime = returnTime;
        this.returnLocation = returnLocation;

    }

    public User owner;
    public User customer;

}
