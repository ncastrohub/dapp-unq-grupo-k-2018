package utils.builders;

import model.*;

import java.time.LocalDate;
import java.util.List;

public class ReservationBuilder {
    private User customer;
    private AdressLocation returnLocation;
    private Publication publication;
    private List<LocalDate> reservedDays;

    public static ReservationBuilder start() {
        return new ReservationBuilder();
    }

    public Reservation build() {
        return new Reservation(publication, reservedDays, customer, returnLocation);
    }

    public ReservationBuilder withCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public ReservationBuilder withReturnLocation(AdressLocation returnLocation) {
        this.returnLocation = returnLocation;
        return this;
    }

    public ReservationBuilder withReservedDays(List<LocalDate> reservedDays) {
        this.reservedDays = reservedDays;
        return this;
    }

    public ReservationBuilder withPublication(Publication publication) {
        this.publication = publication;
        return this;
    }
}
