package model;

import model.exceptions.ReservationStateError;

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

    public void setInProcess() throws ReservationStateError {
        if ( ! this.currentState.equals(StateTypes.WAIT_CONFIRM_OWNER)){
            throw new ReservationStateError("cannot pass to in process the current reservation");
        }else {
            this.currentState = StateTypes.IN_PROCESS;
        }
    }

    public void setReturnVehicle() throws ReservationStateError {
        if ( ! this.currentState.equals(StateTypes.IN_PROCESS)){
            throw new ReservationStateError("cannot return this vehicle");
        }else {
            this.currentState = StateTypes.RETURNED;
        }
    }

    public void reject() throws ReservationStateError {
        if ( this.currentState.equals(StateTypes.INTERRUPTED)){
            throw new ReservationStateError("cannot reject this vehicle");
        }else {
            this.currentState = StateTypes.INTERRUPTED;
        }
    }
}
