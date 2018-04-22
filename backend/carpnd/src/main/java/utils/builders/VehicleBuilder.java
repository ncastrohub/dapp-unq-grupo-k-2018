package utils.builders;

import model.User;
import model.Vehicle;
import model.VehicleType;

public class VehicleBuilder {
    private int capacity;
    private VehicleType type;
    private String description;
    private String photo; // Agregar el link a las fotos!
    private User user;

    public VehicleBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public static VehicleBuilder start() {
        return new VehicleBuilder();
    }

    public VehicleBuilder withType(VehicleType type) {
        this.type = type;
        return this;
    }

    public VehicleBuilder withDescripion(String description) {
        this.description = description;
        return this;
    }

    public VehicleBuilder withPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Vehicle build() {
        return new Vehicle(this.capacity, this.type, this.description, this.photo, this.user);
    }

    public static Vehicle someVehicle() {

        return new Vehicle(5, VehicleType.COUPE, "fiveDoors", "link", UserBuilder.someUser());
    }

    public VehicleBuilder withUser(User user) {
        this.user = user;
        return this;
    }
}
