package model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.LocalDate;

import java.util.List;

public class Reservation extends IdModel {

    @JsonIgnore
    private ReservedPublication publicationSnapshot;

    @JsonIgnore
    public Publication publication;

    public Reservation() {}

//    public ReservationState state;

    @JsonIgnore
    public User customer;

    public Reservation(Publication publication, List<LocalDate> reservationDays,
                       User customer, AdressLocation returnLocation) {
        this.publication = publication;
        this.customer = customer;
//        this.state = new ReservationState();
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
        Integer hours2 = this.getPublicationSnapshot().getHoursBetween();
        return costPerHour.plusBy(new Double(hours2));
    }

    public User getOwner() {
        return publication.getOwner();
    }

    public User getCustomer() {
        return customer;
    }
}
