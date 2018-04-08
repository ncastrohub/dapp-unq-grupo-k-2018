package model;

import java.time.LocalDate;
import java.util.List;

public class ReservedPublication {
    private MoneyAndAmount costPerHour;
    public Publication publication;
    public List<LocalDate> reservedDays;
    private User customer;

    public ReservedPublication(Publication publication, List<LocalDate> reservedDays, User customer) {
        this.publication = publication;
        this.reservedDays = reservedDays;
        this.costPerHour = publication.getCostPerHour();
        this.customer = customer;
    }

    public MoneyAndAmount getCostPerHour() {
        return costPerHour;
    }

    public User getCustomer() {
        return this.customer;
    }

    public List<LocalDate> getReservedDays() {
        return reservedDays;
    }
}
