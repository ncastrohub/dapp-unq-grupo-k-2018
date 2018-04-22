package services;

import model.User;
import model.Vehicle;

import java.util.List;

public class UserService extends GenericService<User> {

    public List<Vehicle> getVehiclesForUser(Long id) {
        return this.getRepository().findById(id).vehicleList;
    }
}
