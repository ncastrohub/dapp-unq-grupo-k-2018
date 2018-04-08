package model;

import model.Exceptions.DayAlreadyReservedException;
import model.Exceptions.DayDisabledException;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Publication {


    private final User owner;
    private final Vehicle vehicle;
    private final AdressLocation acquireLocation;
    private final LinkedList<AdressLocation> returnLocations;
    private final PublicationsEnabledDays enabledDays;
    private MoneyAndAmount costPerHour;
    private List<ReservedPublication> reservedPublicationList;

    public Publication(User owner, MoneyAndAmount costPerHour,
                       Vehicle vehicle, AdressLocation acquireLocation,
                       LinkedList<AdressLocation> returnLocations,
                       PublicationsEnabledDays enabledDays) {
        this.owner = owner;
        this.costPerHour = costPerHour;
        this.vehicle = vehicle;
        this.acquireLocation = acquireLocation;
        this.returnLocations = returnLocations;
        this.enabledDays = enabledDays;
        this.reservedPublicationList = new LinkedList<>();
    }

    public MoneyAndAmount getCostPerHour() {
        return this.costPerHour;
    }

    public User getOwner() {
        return owner;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public AdressLocation getAcquireLocation() {
        return acquireLocation;
    }

    public LinkedList<AdressLocation> getReturnLocations() {
        return returnLocations;
    }

    public PublicationsEnabledDays getAvaiblesDays() {
        return this.enabledDays;
    }

    public void makeReservation(User customer, LinkedList<LocalDate> reservationDays) throws DayAlreadyReservedException, DayDisabledException {
        this.enabledDays.reserveDays(reservationDays);
        ReservedPublication newReservedPublication = new ReservedPublication(this, reservationDays, customer);
        this.reservedPublicationList.add(newReservedPublication);
    }

    public List<ReservedPublication> getReservedPublicationList() {
        return reservedPublicationList;
    }

    public boolean canReserve(LocalDate dayOne) {

        return this.enabledDays.canReserve(dayOne);
    }
}
