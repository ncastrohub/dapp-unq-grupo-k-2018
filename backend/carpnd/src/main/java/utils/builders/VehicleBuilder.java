package utils.builders;

import model.Vehicle;

public class VehicleBuilder {
    private int capacity;
    private String type;
    private String description;
    private String photo; // Agregar el link a las fotos!

    public VehicleBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public static VehicleBuilder start() {
        return new VehicleBuilder();
    }

    public VehicleBuilder withType(String type) {
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
        return new Vehicle(5, "familiar", "fiveDoors", "link");  }

    public static Vehicle someVehicle() {
        return new Vehicle(5, "familiar", "fiveDoors", "link");
    }

}
