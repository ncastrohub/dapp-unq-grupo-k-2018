package model;

import org.joda.time.LocalDate;

import java.util.List;

public class Reservation extends IdModel {

    private ReservedPublication publicationSnapshot;

    public Publication publication;
    public ReservationState state;
    public User customer;

    public Reservation(Publication publication, List<LocalDate> reservationDays,
                       User customer, AdressLocation returnLocation) {
        this.publication = publication;
        this.customer = customer;
        this.state = new WaitingForOwnerState();
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
        MoneyAndAmount costPerHour = this.getPublicationSnapshot().getCostPerHour();
//        Long hours = Duration.between(this.publicationSnapshot.getAcquireTime(), this.publicationSnapshot.returnTime).toHours();
        Integer hours2 = this.getPublicationSnapshot().getHoursBetween();

//        Double roundedValue = hours.doubleValue();
        return costPerHour.plusBy(new Double(hours2));
    }

    public User getOwner() {
        return publication.getOwner();
    }

    public User getCustomer() {
        return customer;
    }
}
