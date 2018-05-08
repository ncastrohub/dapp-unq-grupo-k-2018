package model;

import model.Exceptions.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Publication {



    private User owner;
    private Vehicle vehicle;
    private AdressLocation acquireLocation;
    private LinkedList<AdressLocation> returnLocations;
    private PublicationsEnabledDays enabledDays;
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

    public void makeReservation(User customer, LinkedList<LocalDate> reservationDays) throws DayAlreadyReservedException, DayDisabledException, InvalidAmountOfDaysToReserveException {
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

    public void releaseDays(LinkedList<LocalDate> reservationDays) throws DayNotReservedException {
        this.enabledDays.releaseDays(reservationDays);
    }

    public void disabledDays(LinkedList<LocalDate> reservationDays) throws DayAlreadyDisabledException {
        this.enabledDays.disableDays(reservationDays);
    }
}
