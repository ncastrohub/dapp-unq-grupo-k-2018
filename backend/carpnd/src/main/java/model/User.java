package model;

import java.util.LinkedList;
import java.util.List;

public class User extends IdModel {

    String name;
    String lastName;
    String cuil;
    String email;
    private SystemRateManager rateManager;
    private List vehicles;

    public User(){}

    public User(String name, String lastName, String cuil, String email) {
        this.name = name;
        this.lastName = lastName;
        this.cuil = cuil;
        this.email = email;
        this.rateManager = new SystemRateManager();
        this.vehicles = new LinkedList();
    }

    /*
    Return user's owner rate on the system
     */
    public Long ownerRate() {
//        TODO: Implementar
        return rateManager.ownerRate;
    }

    /*
    Return user's customer rate on the system
     */
    public Long customerRate() {
//        TODO: Implementar
        return rateManager.customerRate;
    }


    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCuil() {
        return cuil;
    }

    public String getEmail() {
        return email;
    }

    public SystemRateManager getRateManager() {
        return rateManager;
    }

    public List getVehicles() {
        return vehicles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRateManager(SystemRateManager rateManager) {
        this.rateManager = rateManager;
    }

    public void setVehicles(List vehicles) {
        this.vehicles = vehicles;
    }
}

