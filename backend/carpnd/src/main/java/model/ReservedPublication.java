package model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class ReservedPublication extends IdModel {
    private MoneyAndAmount costPerHour;

    @JsonIgnore
    public Publication publication;

    public List<LocalDate> reservedDays;
    public AdressLocation acquireLocation;
    public AdressLocation returnLocation;
    public LocalDateTime returnTime;

    @JsonIgnore
    private User customer;

    public ReservedPublication(Publication publication, List<LocalDate> reservedDays, User customer, AdressLocation returnLocation) {
        this.reservedDays = reservedDays;
        this.acquireLocation = publication.getAcquireLocation().createNew();
        this.returnLocation = returnLocation;
        this.costPerHour = publication.getCostPerHour().createNew();
        this.customer = customer;
        this.publication = publication;
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

    public LocalDateTime getAcquireTime() {
        return this.reservedDays.stream().min(Comparator.comparing(LocalDate::getDayOfYear)).get().atStartOfDay();
    }
}
