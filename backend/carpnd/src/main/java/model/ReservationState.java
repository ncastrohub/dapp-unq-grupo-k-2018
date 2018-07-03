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

    public void setInProcess()  {

        this.currentState = StateTypes.IN_PROCESS;

    }

    public void setReturnVehicle()  {
        this.currentState = StateTypes.RETURNED;
    }

    public void reject() {

            this.currentState = StateTypes.INTERRUPTED;

    }
}
