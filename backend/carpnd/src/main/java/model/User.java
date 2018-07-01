package model;


import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.LinkedList;
import java.util.List;

public class User extends IdModel {

    public String name;
    public String lastName;
    public String cuil;
    public String email;
//
//    @JsonIgnore
//    private SystemRateManager rateManager;

    @JsonIgnore
    public MoneyAndAmount availableMoney;

    @JsonIgnore
    private List<Vehicle> vehicles = new LinkedList<>();

    public User() {
    }

    public User(String name, String lastName, String cuil, String email) {
        this.name = name;
        this.lastName = lastName;
        this.cuil = cuil;
        this.email = email;
//        this.rateManager = new SystemRateManager();
        this.vehicles = new LinkedList<>();
        this.availableMoney = new MoneyAndAmount(0.00, CustomCurrencies.ARS);
    }

    /*
    Return user's owner rate on the system
     */
//    public Long ownerRate() {
////        TODO: Implementar
//        return rateManager.ownerRate;
//    }

    /*
    Return user's customer rate on the system
     */
//    public Long customerRate() {
////        TODO: Implementar
//        return rateManager.customerRate;
//    }


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
//
//    @JsonIgnore
//    public SystemRateManager getRateManager() {
//        return rateManager;
//    }

    @JsonIgnore
    public List<Vehicle> getVehicles() {
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

//    public void setRateManager(SystemRateManager rateManager) {
//        this.rateManager = rateManager;
//    }

    public void setVehicles(List vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
    }

    public void reduceMoney(MoneyAndAmount moneyAndAmount) {
        this.availableMoney = this.availableMoney.reduceAndGet(moneyAndAmount);
    }
}

