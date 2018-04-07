package model;

import java.time.LocalDate;
import java.util.List;

public class ReservedPublication {
    private MoneyAndAmount costPerHour;
    public Publication publication;
    public List<LocalDate> reservedDays;

    public ReservedPublication(Publication publication, List<LocalDate> reservedDays) {
        this.publication = publication;
        this.reservedDays = reservedDays;
        this.costPerHour = publication.getCostPerHour();
    }

    public MoneyAndAmount getCostPerHour() {
        return costPerHour;
    }
}
