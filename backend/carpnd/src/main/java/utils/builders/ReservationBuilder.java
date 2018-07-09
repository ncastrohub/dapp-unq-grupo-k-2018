package utils.builders;

import model.*;

import org.joda.time.LocalDate;
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

    public static Reservation some() {
        Reservation reserv = new Reservation();
        reserv.publication = PublicationBuilder.some();

        return reserv;
    }

    public Reservation getOne() {
        Reservation reserv = new Reservation();
        PublicationBuilder pBuilder = new PublicationBuilder();
        reserv.customer = new User();
        reserv.state = new ReservationState();
        reserv.publication = pBuilder.getOne();
        return reserv;
    }
}
