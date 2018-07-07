package model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.Comparator;
import java.util.List;

public class ReservedPublication extends IdModel {
    private MoneyAndAmount costPerHour;

    @JsonIgnore
    public Publication publication;

    public List<LocalDate> reservedDays;

    public AdressLocation getAcquireLocation() {
        return acquireLocation;
    }

    public void setAcquireLocation(AdressLocation acquireLocation) {
        this.acquireLocation = acquireLocation;
    }

    public AdressLocation acquireLocation;

    public AdressLocation getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(AdressLocation returnLocation) {
        this.returnLocation = returnLocation;
    }

    public AdressLocation returnLocation;
    private LocalDateTime returnTime;

    public ReservedPublication(){}

    @JsonIgnore
    private User customer;

    public ReservedPublication(Publication publication, List<LocalDate> reservedDays, User customer, AdressLocation returnLocation) {
        this.reservedDays = reservedDays;
        this.acquireLocation = publication.getAcquireLocation().createNew();
        this.returnLocation = returnLocation;
        this.costPerHour = publication.costPerHour.createNew();
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
        return this.reservedDays.stream().min(Comparator.comparing(LocalDate::getDayOfYear)).get().toLocalDateTime(LocalTime.now());
    }

    public LocalDateTime getReturnTime() {

        return returnTime;
    }

    @JsonIgnore
    public Integer getHoursBetween() { return Hours.hoursBetween(
                this.getAcquireTime(),
                this.getReturnTime()
        ).getHours();
    }
}
