package model;

import org.codehaus.jackson.annotate.JsonIgnore;


public class Vehicle extends IdModel {

    public int capacity;

    public VehicleType type;

    public String description;

    public String photo; // Agregar el link a las fotos!

    @JsonIgnore
    public User owner;

    public Vehicle(int capacity, VehicleType type, String description, String photo) {
        this.capacity = capacity;
        this.type = type;
        this.description = description;
        this.photo = photo;
    }

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

    public int getCapacity() {
        return capacity;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @JsonIgnore
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Vehicle && this.getId() != null && this.getId().equals(((Vehicle) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
