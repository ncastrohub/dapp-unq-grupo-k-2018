package services;

import javafx.util.Pair;
import model.Exceptions.CustomValidationError;
import model.User;
import model.Vehicle;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;

public class PublicationConcernService {

    private UserService userService;
    private VehicleService vehicleService;

    @Transactional
    public void createVehicleForUser(Serializable userId, Vehicle vehicle) throws CustomValidationError {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Vehicle>> constraintViolations =
                validator.validate( vehicle );

        if (constraintViolations.size() == 0){
            User user = userService.findById(userId);
            user.addVehicle(vehicle);
            userService.save(user);
        }else {
            throw new CustomValidationError(constraintViolations.);
        }

    }





    public List<Pair(String, String)> asdasd(Set<ConstraintViolation<E>> asdq) {

    }

}
