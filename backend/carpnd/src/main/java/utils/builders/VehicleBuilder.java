package utils.builders;

import api.forms.VehicleForm;
import model.User;
import model.Vehicle;
import model.VehicleType;

public class VehicleBuilder {
    private int capacity;
    private VehicleType type;
    private String description;
    private String photo; // Agregar el link a las fotos!
    private User owner;
    public VehicleBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public static VehicleBuilder start() {
        return new VehicleBuilder();
    }


    public VehicleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public VehicleBuilder withPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Vehicle build() {
        return new Vehicle(this.capacity, this.type,
                this.description, this.photo, this.owner
        );
    }

    public VehicleForm buildForm() {
        VehicleForm vehicleForm = new VehicleForm();
        vehicleForm.capacity = this.capacity;
        vehicleForm.description = this.description;
        vehicleForm.photo = this.photo;
        vehicleForm.type = this.type;
        return vehicleForm;
    }

    public VehicleBuilder withOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public static Vehicle someVehicle() {
        return new Vehicle(5, VehicleType.SEDAN,
                "fiveDoors", "link", UserBuilder.someUser()
        );
    }

    public static Vehicle onlyWithOwner(User owner) {
        return new Vehicle(5, VehicleType.SEDAN,
                "fiveDoors", "link", owner
        );
    }


    public VehicleBuilder withType(VehicleType vehicleType) {
        this.type = vehicleType;
        return this;
    }
}
