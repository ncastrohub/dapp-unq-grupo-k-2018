package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Reservation {

    private ReservedPublication publicationSnapshot;

    public Publication publication;
    public ReservationState state;
    public User customer;

    public Reservation(Publication publication, List<LocalDate> reservationDays,
                       User customer, AdressLocation returnLocation) {
        this.publication = publication;
        this.customer = customer;
        this.state.ownerAccepts(this);
        this.publicationSnapshot = new ReservedPublication(
                publication,
                reservationDays,
                customer,
                returnLocation
        );
    }

    public ReservedPublication getPublicationSnapshot() {
        return publicationSnapshot;
    }


    public MoneyAndAmount finalPrice() {
        MoneyAndAmount costPerHour = this.publicationSnapshot.getCostPerHour();
        Long hours = this.publicationSnapshot.getAcquireTime().until( this.publicationSnapshot.returnTime, ChronoUnit.HOURS);
        Double roundedValue = hours.doubleValue();
        return costPerHour.plusBy(roundedValue);
    }

    public User getOwner() {
        return publication.getOwner();
    }

    public User getCustomer() {
        return customer;
    }
}
