package services;

import model.Exceptions.CustomValidationError;
import model.User;
import model.Vehicle;

import java.io.Serializable;

public class PublicationConcernService {

    private UserService userService;
    private VehicleService vehicleService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    public void createVehicleForUser(Serializable userId, Vehicle vehicle) throws CustomValidationError {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<Vehicle>> constraintViolations =
//                validator.validate( vehicle );

//        if (constraintViolations.size() == 0){

        User user = userService.findById(userId);
        vehicle.owner = user;
        vehicleService.save(vehicle);
//        }else {
//            throw new CustomValidationError(constraintViolations.);
//        }

    }

}
