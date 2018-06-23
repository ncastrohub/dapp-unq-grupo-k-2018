package services;

import javassist.NotFoundException;
import model.User;
import model.Vehicle;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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

    @Transactional
    public User findByEmail(String email) throws NotFoundException {
        User user = new User();
        user.email = email;
        List<User> userList = this.getRepository().findByExample(user);
        if (userList.isEmpty())
              { throw new NotFoundException(""); }
         else { return userList.get(0); }
    }

}