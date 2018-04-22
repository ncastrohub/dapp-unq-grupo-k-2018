package model;

public class Vehicle extends IdModel {

    public int capacity;
    public String type;
    public String description;
    public String photo; // Agregar el link a las fotos!

    public Vehicle() {}

    public Vehicle(int capacity, String type, String description, String link){
        this.capacity=capacity;
        this.type=type;
        this.description=description;
        this.photo=link;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
