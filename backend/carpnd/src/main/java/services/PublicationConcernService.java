package services;

import api.DETEOS.UserForm;
import api.DETEOS.UserUpdateForm;
import api.forms.VehicleForm;
import api.forms.VehicleUpdateForm;
import model.exceptions.FormValidationError;
import model.User;
import model.Vehicle;
import org.springframework.transaction.annotation.Transactional;
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

    public Vehicle createVehicleForUser(Serializable userId, VehicleForm vehicle) throws FormValidationError {
        GenericValidator.validate(vehicle);
        Vehicle newVehicle = new Vehicle(
                vehicle.capacity, vehicle.type, vehicle.description, vehicle.photo);
        userService.createVehicleForUser(userId, newVehicle);
        return newVehicle;
    }

    public List<Vehicle> getVehiclesForUser(Long id) {
        return this.userService.findById(id).getVehicles();
    }

    @Transactional
    public void deleteVehicle(Serializable userId, Serializable vehicleId) {
        Vehicle vehicle = this.vehicleService.getRepository().findById(vehicleId);
        this.userService.getRepository().findById(userId).getVehicles().remove(vehicle);
    }

    public void updateVehicle(Long userId, VehicleUpdateForm vehicle) throws FormValidationError {
        GenericValidator.validate(vehicle);
        Vehicle vehicleInDb = this.vehicleService.findById(vehicle.id);
        vehicleInDb.description = vehicle.description;
        vehicleInDb.type = vehicle.type;
        vehicleInDb.capacity = vehicle.capacity;
        vehicleInDb.photo = vehicle.photo;
        this.vehicleService.update(vehicleInDb);
    }

    public List<User> getUsers() {
        return this.userService.retriveAll();
    }

    public User createUser(Serializable userId, UserForm userF) throws FormValidationError {
        GenericValidator.validate(userF);
        User newUser = new User(
                userF.name, userF.lastName, userF.cuil, userF.email);
        userService.createUser(userId);
        // PARA QUE ESTO NO CHILLE DICE QUE NECESITO UN METODO createUser en
        // UnserService o
        //PublicConscernService,
        // eso es lo que no entiendo. Yo lo hice en UserService... CHEQUEAR ESTOOOOO!!!!!!

        return newUser;
    }

    public void deleteUser(Long userId) { this.userService.deleteById(userId);
    }

    public void updateUser(UserUpdateForm userUP) throws FormValidationError {
        GenericValidator.validate(userUP);
        User userInDb = this.userService.findById(userUP.id);
        userInDb.name = userUP.name;
        userInDb.lastName = userUP.lastName;
        userInDb.cuil = userUP.cuil;
        userInDb.email = userUP.email;
        this.userService.update(userInDb);
    }

}



