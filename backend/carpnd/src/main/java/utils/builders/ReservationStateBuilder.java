package utils.builders;

import model.ReservationState;
import model.StateTypes;

public class ReservationStateBuilder {

    public static ReservationState some(){
        ReservationState state = new ReservationState();
        state.setCurrentState(StateTypes.IN_PROCESS);
        return state;
    }
}
