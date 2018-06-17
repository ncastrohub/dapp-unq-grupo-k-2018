package model;

public class WaitingForOwnerState extends ReservationState {

    @Override
    public Boolean getWaitingForOwner() {
        return true;
    }

}
