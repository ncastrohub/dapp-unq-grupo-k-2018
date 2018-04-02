package utils.builders;

import model.Reservation;
import model.User;

public class ReservationBuilder {
    private User owner;
    private User customer;

    public static ReservationBuilder start() {
        return new ReservationBuilder();
    }

    public ReservationBuilder withOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Reservation build() {
        return new Reservation(owner, customer);
    }

    public ReservationBuilder withCustomer(User customer) {
        this.customer = customer;
        return this;
    }
}
