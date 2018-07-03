package model;

import model.exceptions.CannotChangeStateError;
import model.exceptions.NotReservationOwnerException;
import model.exceptions.ReservationStateError;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.LocalDate;

import java.util.List;

public class Reservation extends IdModel {

    public void setPublicationSnapshot(ReservedPublication publicationSnapshot) {
        this.publicationSnapshot = publicationSnapshot;
    }

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

    @JsonIgnore
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

    public void setState(User user, StateTypes reservationState) throws CannotChangeStateError {
        if (this.customer.getId().equals(user.getId())){
            if (reservationState == StateTypes.INTERRUPTED && (this.state.getCurrentState() == StateTypes.IN_PROCESS || this.state.getCurrentState() == StateTypes.WAIT_CONFIRM_OWNER )) {
                this.state.reject();
                return;
            }
            if (reservationState == StateTypes.WAIT_OWNER_TO_END && this.state.getCurrentState() == StateTypes.IN_PROCESS ){
                this.state.setCurrentState(reservationState);
                return;
            }
            throw new CannotChangeStateError("Cannot change from current state");
        }
        if (this.publication.getOwner().getId().equals(user.getId())){
            if (reservationState == StateTypes.IN_PROCESS && (this.state.getCurrentState() == StateTypes.WAIT_CONFIRM_OWNER )) {
                this.state.setCurrentState(reservationState);
                return;
            }
            if (reservationState == StateTypes.RETURNED && (this.state.getCurrentState() == StateTypes.WAIT_OWNER_TO_END )) {
                this.state.setCurrentState(reservationState);
                return;
            }
            throw new CannotChangeStateError("Cannot change from current state");
        }
        throw new CannotChangeStateError("Cannot change from current state");
    }
}
