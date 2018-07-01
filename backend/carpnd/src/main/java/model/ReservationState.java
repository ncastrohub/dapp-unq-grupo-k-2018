package model;

public class ReservationState extends IdModel {

    public StateTypes getCurrentState() {
        return currentState;
    }

    public void setCurrentState(StateTypes currentState) {
        this.currentState = currentState;
    }

    private StateTypes currentState;

    public ReservationState(StateTypes waitConfirmOwner) {
        this.currentState = waitConfirmOwner;
    }

    public void setWaitForOwnerToConfirm(){
        this.currentState = StateTypes.WAIT_CONFIRM_OWNER;
    }

    public ReservationState(){}

}
