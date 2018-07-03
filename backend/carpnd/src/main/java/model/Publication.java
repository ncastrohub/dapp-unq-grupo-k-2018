package model;

import model.exceptions.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import org.joda.time.LocalDate;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Publication extends IdModel {

    private User owner;
    public Vehicle vehicle;

    private AdressLocation acquireLocation;

    private List<AdressLocation> returnLocations;

    private PublicationsEnabledDays enabledDays;

    public MoneyAndAmountForPublication costPerHour;

    public Publication() {
    }

    public Publication(User owner, MoneyAndAmountForPublication costPerHour,
                       Vehicle vehicle, AdressLocation acquireLocation,
                       LinkedList<AdressLocation> returnLocations,
                       List<Integer> disabledDays) {
        this.owner = owner;
        this.costPerHour = costPerHour;
        this.vehicle = vehicle;
        this.acquireLocation = acquireLocation;
        this.returnLocations = returnLocations;
        this.enabledDays = new PublicationsEnabledDays(disabledDays);
    }

    public Publication(User owner, MoneyAndAmountForPublication pricePerHour, Vehicle vehicle, AdressLocation acquireLocation, LinkedList<AdressLocation> returnLocations, PublicationsEnabledDays enabledDays) {
        this.owner = owner;
        this.vehicle = vehicle;
        this.acquireLocation = acquireLocation;
        this.returnLocations = returnLocations;
        this.enabledDays = enabledDays;
        this.costPerHour = pricePerHour;
    }

    public Reservation makeReservation(User customer, List<LocalDate> reservationDays, AdressLocation returnLocation) throws DayAlreadyReservedException, DayDisabledException, InvalidAmountOfDaysToReserveException, NotEnoughCreditException {
        this.canPay(customer.availableMoney, reservationDays.size());
        this.enabledDays.reserveDays(reservationDays);
        this.reserveMoney(customer, reservationDays.size());
        return new Reservation(this, reservationDays, customer, returnLocation);
    }

    private void reserveMoney(User customer, int size) {
        customer.reduceMoney(this.costPerHour.plusBy((double) size));
        this.owner.availableMoney.sum(this.costPerHour.plusBy((double) size));
    }


    public void canPay(MoneyAndAmount availableMoney, int amountOfDays) throws NotEnoughCreditException {
        if (this.costPerHour.plusBy((double) amountOfDays).isMayorTo(availableMoney)) {
            throw new NotEnoughCreditException("customer has not enough money");
        }
    }

    public boolean canReserve(LocalDate dayOne) {

        return this.enabledDays.canReserve(dayOne);
    }

    void releaseDays(LinkedList<LocalDate> reservationDays) throws DayNotReservedException {
        this.enabledDays.releaseDays(reservationDays);
    }

    void disabledDays(LinkedList<Integer> reservationDays) {
        this.enabledDays.setDisabledDays(reservationDays);
    }

    public void setReturnLocations(LinkedList<AdressLocation> returnLocations) {
        this.returnLocations = returnLocations;
    }

    public AdressLocation getReturnLocationsById(Long locationId) throws NoReturnLocationInPublicationException {
        Optional<AdressLocation> matchLocation = this.returnLocations.stream().filter(location -> location.getId().equals(locationId)).findFirst();
        if (matchLocation.isPresent()) {
            return matchLocation.get();
        } else {
            throw new NoReturnLocationInPublicationException("Return Location selected no corresponding to Publication");
        }
    }


//    public MoneyAndAmount getCostPerHour() {
//        return this.costPerHour;
//    }

    public User getOwner() {
        return owner;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public AdressLocation getAcquireLocation() {
        return acquireLocation;
    }

    public List<AdressLocation> getReturnLocations() {
        return returnLocations;
    }

    @JsonIgnore
    PublicationsEnabledDays getAvaiblesDays() {
        return this.enabledDays;
    }


    public List<Integer> getDisabledDays() {
        return this.enabledDays.getDisabledDays();
    }

    public List<LocalDate> getReservedDays() {
        return this.enabledDays.getReservedDays();
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setAcquireLocation(AdressLocation acquireLocation) {
        this.acquireLocation = acquireLocation;
    }

    public void setReturnLocations(List<AdressLocation> returnLocations) {
        this.returnLocations = returnLocations;
    }

    public PublicationsEnabledDays getEnabledDays() {
        return enabledDays;
    }

    public void setEnabledDays(PublicationsEnabledDays enabledDays) {
        this.enabledDays = enabledDays;
    }

//    public void setCostPerHour(MoneyAndAmount costPerHour) {
//        this.costPerHour = costPerHour;
//    }

    public void disabledDays(List<LocalDate> reservationDays) {
        LinkedList<Integer> daysOfWeek = new LinkedList<>();
        for (LocalDate date: reservationDays){
            daysOfWeek.add(date.getDayOfWeek());
        }
        this.disabledDays(daysOfWeek);
    }

    public MoneyAndAmountForPublication getCostPerHour() {
        return costPerHour;
    }
}
