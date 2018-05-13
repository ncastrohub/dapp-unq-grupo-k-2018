package model;

public class ReservationState {

    private StateNode myState;

    public ReservationState() {
        setState(new WaitingForOwner());
    }

    public void setState(final StateNode newState) {
        myState = newState;
    }

    public void ownerAccepts(Reservation aReserve) {

        myState.ownerAccepts(this);
    }

    public void ownerRejects(Reservation aReserve) {

        myState.ownerRejects(this);
    }

    public void userAccepts(Reservation aReserve) {

        myState.ownerRejects(this);
    }

    public void userRejects(Reservation aReserve) {

        myState.ownerRejects(this);
    }

    public StateNode getState() {

        return myState;
    }
}
