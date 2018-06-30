package model;

public class ReservationState extends IdModel {

    private StateTypes currentState;

    public ReservationState(StateTypes waitConfirmOwner) {
        this.currentState = waitConfirmOwner;
    }

    public void setWaitForOwnerToConfirm(){
        this.currentState = StateTypes.WAIT_CONFIRM_OWNER;
    }


}
