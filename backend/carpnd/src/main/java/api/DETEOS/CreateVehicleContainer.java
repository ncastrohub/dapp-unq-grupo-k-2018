package api.DETEOS;

import lombok.Getter;
import lombok.Setter;
import model.Vehicle;

public class CreateVehicleContainer {

    CreateVehicleContainer() {}

    Vehicle vehicle;
    String user;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

