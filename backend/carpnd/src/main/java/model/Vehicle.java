package model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

public class Vehicle extends IdModel {

    @Min(1)
    public int capacity;

    @NotNull
    public VehicleType type;

    @NotNull
    @Size(min = 10, max = 30)
    public String description;

    @Pattern(regexp = "[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)\n")
    @NotNull
    public String photo; // Agregar el link a las fotos!

    @NotNull
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

    public int getCapacity() {
        return capacity;
    }
}
