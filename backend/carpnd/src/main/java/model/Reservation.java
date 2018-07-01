package model;

import model.exceptions.NotReservationOwnerException;
import model.exceptions.ReservationStateError;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.LocalDate;

import java.util.List;

public class Reservation extends IdModel {

    @JsonIgnore
    private ReservedPublication publicationSnapshot;

    @JsonIgnore
    public Publication publication;

    public ReservationState state;

    public Reservation() {}

//    public ReservationState state;

    @JsonIgnore
    public User customer;

    public Reservation(Publication publication, List<LocalDate> reservationDays,
                       User customer, AdressLocation returnLocation) {
        this.publication = publication;
        this.customer = customer;
        this.state = new ReservationState(StateTypes.WAIT_CONFIRM_OWNER);
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

    public void confirmByOwner(User owner) throws NotReservationOwnerException, ReservationStateError {
        if (! this.getOwner().getId().equals(owner.getId())) {
            throw new NotReservationOwnerException("you dont own this reservation");
        }
        this.state.setInProcess();
    }

    public void returnVehicle(User owner) throws NotReservationOwnerException, ReservationStateError {
        if (! this.getOwner().getId().equals(owner.getId())) {
            throw new NotReservationOwnerException("you dont own this reservation");
        }
        this.state.setReturnVehicle();
    }

    public void reject(User owner) throws NotReservationOwnerException, ReservationStateError {
        if (! this.getOwner().getId().equals(owner.getId())) {
            throw new NotReservationOwnerException("you dont own this reservation");
        }
        this.state.reject();
    }
}
