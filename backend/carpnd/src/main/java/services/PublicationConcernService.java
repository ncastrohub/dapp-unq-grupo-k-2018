package services;

import api.DETEOS.VehicleForm;
import model.Exceptions.FormValidationError;
import model.User;
import model.Vehicle;
import services.Validators.GenericValidator;

import java.io.Serializable;
import java.util.List;

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

    public void createVehicleForUser(Serializable userId, VehicleForm vehicle) throws FormValidationError {
        GenericValidator.validate(vehicle);
        Vehicle newVehicle = new Vehicle(
                vehicle.capacity, vehicle.type, vehicle.description, vehicle.photo);
        User user = userService.createVehicleForUser(userId, newVehicle);
    }

    public List getVehiclesForUser(Long id) {
        return this.userService.findById(id).getVehicles();
    }
}
