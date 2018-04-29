package services;

import model.User;
import model.Vehicle;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public class UserService extends GenericService<User> {

    private static final long serialVersionUID = 2131359482422367092L;

    @Transactional
    public User createVehicleForUser(Serializable userId, Vehicle newVehicle) {
        User user = this.getRepository().findById(userId);
        user.addVehicle(newVehicle);
        newVehicle.owner = user;
        this.getRepository().save(user);
        return user;
    }
}