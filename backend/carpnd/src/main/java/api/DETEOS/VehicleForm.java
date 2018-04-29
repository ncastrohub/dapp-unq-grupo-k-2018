package api.DETEOS;

import model.Vehicle;

public class VehicleForm {

    VehicleForm() {}

    private Vehicle vehicle;
    private Long user;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}

