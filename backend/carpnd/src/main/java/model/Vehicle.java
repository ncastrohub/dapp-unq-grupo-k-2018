package model;

public class Vehicle {
    int capacity;
    String type;
    String description;
    String photo; // Agregar el lik a las fotos!

    public Vehicle(int capacity, String type, String description, String link){
        this.capacity=capacity;
        this.type=type;
        this.description=description;
        this.photo=link;
    }

}
