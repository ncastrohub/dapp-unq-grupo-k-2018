package model.Exceptions;

import model.Vehicle;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CustomValidationError<T> extends Exception {

    private final Set<ConstraintViolation> errorList;

    public CustomValidationError(Set<ConstraintViolation> errorList) {
        this.errorList = errorList;
    }
}
