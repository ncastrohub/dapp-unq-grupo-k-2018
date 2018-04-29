package services.Validators;

import me.geso.tinyvalidator.ConstraintViolation;
import me.geso.tinyvalidator.Validator;
import model.Exceptions.FormValidationError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenericValidator {

    public static <T> void validate(T toValidate) throws FormValidationError {

        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(toValidate);

        if(violations.size() > 0){

            Map<String, String> errorList =
                    violations.stream().collect(Collectors.toMap(ConstraintViolation::getName, ConstraintViolation::getMessage));

            throw new FormValidationError(errorList);
        }

    }

}