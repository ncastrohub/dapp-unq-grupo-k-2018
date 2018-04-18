package model;

public class ReservationState {

    public StateNode myState;

    public ReservationState() {
        setState(new WaitingForOwner());
    }

    void setState(final StateNode newState) {
        myState = newState;
    }

    void ownerAccepts(Reservation aReserve) {
        myState.ownerAccepts(this);
    }

    void ownerRejects(Reservation aReserve) {
        myState.ownerRejects(this);
    }

    void userAccepts(Reservation aReserve) {
        myState.ownerRejects(this);
    }

    void userRejects(Reservation aReserve) {
        myState.ownerRejects(this);
    }

    public StateNode getState() {
        return myState;
    }
}
