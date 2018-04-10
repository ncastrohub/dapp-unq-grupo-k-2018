package model;

import model.exceptions.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Publication {


<<<<<<< HEAD
<<<<<<< HEAD
    public MoneyAndAmount costPerHour;
    public User ouner;
    public AdressLocation acquireLocation;
    public AdressLocation restoreLocation;
    public DaySchedule availableSchedules;
    public Telephone telephone;

    public Publication(MoneyAndAmount costPerHour, User user, AdressLocation acquireLocation, AdressLocation restoreLocation, DaySchedule availableSchedules, Telephone telephone)
    {
        this.costPerHour=costPerHour;
        this.ouner=user;
        this.acquireLocation=acquireLocation;
        this.restoreLocation=restoreLocation;
        this.availableSchedules=availableSchedules;
        this.telephone=telephone;
=======
=======
>>>>>>> e6643514710e5e47ad2929b04bf60b44250b1eb4
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

<<<<<<< HEAD
>>>>>>> 63375923e7b3e880706e034960b230841cf94c3f
=======
>>>>>>> e6643514710e5e47ad2929b04bf60b44250b1eb4
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
