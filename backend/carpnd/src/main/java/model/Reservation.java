package model;

public class Reservation {

    public Reservation(User owner, User customer) {
        this.owner = owner;
        this.customer = customer;
    }

    public User owner;
    public User customer;

}
