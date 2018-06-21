package services;


import api.forms.*;
import model.Publication;
import model.User;
import model.Vehicle;
import model.exceptions.FormValidationError;
import org.springframework.transaction.annotation.Transactional;
import services.Validators.GenericValidator;
import utils.builders.PublicationsEnabledDaysFormBuilder;

import java.io.Serializable;
import java.util.List;

public class PublishService {

    private UserService userService;
    private VehicleService vehicleService;
    private PublicationService publicationService;
    private ReservationService reservationService;


    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }


    public PublicationService getPublicationService() {
        return publicationService;
    }

    public void setPublicationService(PublicationService publicationService) {
        this.publicationService = publicationService;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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
    public void deleteVehicle(Serializable userId, Long vehicleId) {
        Vehicle vehicle = this.vehicleService.getRepository().findById(vehicleId);
        User userToEdit = this.userService.getRepository().findById(userId);
        userToEdit.getVehicles().remove(vehicle);
        this.userService.getRepository().update(userToEdit);
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

    public User createUser(UserForm userF) throws FormValidationError {
        GenericValidator.validate(userF);
        User newUser = new User(
                userF.name, userF.lastName, userF.cuil, userF.email);
        this.userService.save(newUser);
        return newUser;
    }

    public void deleteUser(Long userId) {
        this.userService.deleteById(userId);
    }

    public void updateUser(UserUpdateForm userForm) throws FormValidationError {
        GenericValidator.validate(userForm);
        User userInDb = this.userService.findById(userForm.id);
        userInDb.name = userForm.name;
        userInDb.lastName = userForm.lastName;
        userInDb.cuil = userForm.cuil;
        userInDb.email = userForm.email;
        this.userService.update(userInDb);

    }

    public User retriveUser(Long id) {
        return this.userService.findById(id);
    }

    public Publication createPublicationForUser(Long userId, PublicationForm publication) throws FormValidationError {
        GenericValidator.validate(publication);

        User publicationUser = this.userService.findById(userId);
        Vehicle selectedVehicle = null;

        for (Vehicle vehicle :  publicationUser.getVehicles()) {
            if (vehicle.getId() == publication.vehicle.id) {
                selectedVehicle = vehicle;
            }
        }

        Publication newPublication = new Publication(
                        publicationUser,
                publication.getCostPerDayInstance(),
                selectedVehicle,
                publication.getAcquireLocationInstance(),
                publication.getReturnLocationsList(),
                PublicationsEnabledDaysFormBuilder.some().getModelInstance()
                );

        this.publicationService.save(newPublication);
        return newPublication;
    }

    public Publication retrievePublication(Long publicationId) {
        return this.publicationService.findById(publicationId);
    }



//
//    public OwnPaginationPage<Publication> getPublicationPage(int pageNumber) {
//        return null;
//    }


}



