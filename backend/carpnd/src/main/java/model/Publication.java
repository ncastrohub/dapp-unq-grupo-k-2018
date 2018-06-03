package model;

import model.exceptions.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Publication extends IdModel {

    private User owner;
    private Vehicle vehicle;
    private AdressLocation acquireLocation;
    private List<AdressLocation> returnLocations;
    private PublicationsEnabledDays enabledDays;
    private MoneyAndAmount costPerHour;

    public Publication(){}
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

    }

    @JsonIgnore
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

    @JsonIgnore
    public List<AdressLocation> getReturnLocations() {
        return returnLocations;
    }

    @JsonIgnore
    public PublicationsEnabledDays getAvaiblesDays() {
        return this.enabledDays;
    }

    public Reservation makeReservation(User customer, List<LocalDate> reservationDays, AdressLocation returnLocation) throws DayAlreadyReservedException, DayDisabledException, InvalidAmountOfDaysToReserveException {
        this.enabledDays.reserveDays(reservationDays);
        return new Reservation(this, reservationDays, customer, returnLocation);
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

    public List<LocalDate> getDisabledDays(){
        return this.enabledDays.getDisabledDays();
    }

    public List<LocalDate> getReservedDays() {
        return  this.enabledDays.getReservedDays();
    }

    public void setReturnLocations(LinkedList<AdressLocation> returnLocations) {
        this.returnLocations = returnLocations;
    }

    public AdressLocation getReturnLocationsById(Long locationId) throws NoReturnLocationInPublicationException {
        Optional<AdressLocation> matchLocation = this.returnLocations.stream().filter(location -> location.getId().equals(locationId)).findFirst();
        if (matchLocation.isPresent()){
            return matchLocation.get();
        }else {
            throw new NoReturnLocationInPublicationException();
        }
    }
}
