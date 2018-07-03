package model.exceptions;

public class CannotChangeStateError extends ExceptionWithError {
    public CannotChangeStateError(String cannot_change_from_current_state) {
        super(cannot_change_from_current_state);
    }
}
