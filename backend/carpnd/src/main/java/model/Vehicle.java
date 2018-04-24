package model;

public class Vehicle extends IdModel {

    public int capacity;
    public VehicleType type;
    public String description;
    public String photo; // Agregar el link a las fotos!
    public User owner;

    public Vehicle() {
    }

    public Vehicle(int capacity, VehicleType type, String description, String link, User owner) {
        this.capacity = capacity;
        this.type = type;
        this.description = description;
        this.photo = link;
        this.owner = owner;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
