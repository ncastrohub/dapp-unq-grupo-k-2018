package model;

public class Vehicle {
    private int capacity;
    private String type;
    private String description;
    private String photo; // Agregar el link a las fotos!

    public Vehicle(int capacity, String type, String description, String link){
        this.capacity=capacity;
        this.type=type;
        this.description=description;
        this.photo=link;
    }

}
