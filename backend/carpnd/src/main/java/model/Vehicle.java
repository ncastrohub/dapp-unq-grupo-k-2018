package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE")
public class Vehicle extends IdModel {


    public User user;

    @Column(name="CAPACITY")
    public Integer capacity;


    public VehicleType type;

    @Column(name="DESCRIPTION", length=50)
    public String description;

    @Column(name="PHOTO")
    public String photo; // Agregar el link a las fotos!

    public Vehicle(){}

    public Vehicle(int capacity, VehicleType type, String description, String link, User user){
        this.capacity=capacity;
        this.type=type;
        this.description=description;
        this.photo=link;
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
