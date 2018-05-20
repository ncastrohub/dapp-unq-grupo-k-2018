package model.exceptions;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CustomValidationError extends Exception {

    private final Set<ConstraintViolation> errorList;

    public CustomValidationError(Set<ConstraintViolation> errorList) {
        this.errorList = errorList;
    }
}
