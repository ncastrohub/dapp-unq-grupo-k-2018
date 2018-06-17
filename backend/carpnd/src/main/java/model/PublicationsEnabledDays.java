package model;


import model.exceptions.*;
import org.joda.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

public class PublicationsEnabledDays extends IdModel {

    private List<LocalDate> disabledDays;
    private List<LocalDate> reservedDays;

    public List<LocalDate> getReservedDays() {
        return reservedDays;
    }

    public List<LocalDate> getDisabledDays() {
        return this.disabledDays;
    }

    PublicationsEnabledDays(){

        this.disabledDays = new LinkedList<>();
        this.reservedDays = new LinkedList<>();
    }

    public PublicationsEnabledDays(List<LocalDate> reservedDays, List<LocalDate> disabledDays) {
        this.disabledDays = disabledDays;
        this.reservedDays = reservedDays;
    }

    public void reserveDay(LocalDate dayOne) throws DayDisabledException, DayAlreadyReservedException {
        if (disabledDays.contains(dayOne)) {
            throw new DayDisabledException("One Selected Day is Disabled");
        }
        if (reservedDays.contains(dayOne)){
            throw new DayAlreadyReservedException("One of selected Days are reserved");
        }
        this.reservedDays.add(dayOne);
    }

    public void setDisabled(LocalDate disabled) throws DayAlreadyDisabledException {
        if (this.disabledDays.contains(disabled)){
            throw new DayAlreadyDisabledException();
        }
        this.disabledDays.add(disabled);
    }

    public boolean canReserve(LocalDate dayOne) {
        return !this.reservedDays.contains(dayOne) && !this.disabledDays.contains(dayOne);
    }

    public void releaseDay(LocalDate date) throws DayNotReservedException {
        if (!this.reservedDays.contains(date)){
            throw new DayNotReservedException();
        }else {
            this.reservedDays.remove(date);
        }
    }

    public void reserveDays(List<LocalDate> reservationDays) throws DayAlreadyReservedException, DayDisabledException, InvalidAmountOfDaysToReserveException {

        if (reservationDays.size() > 5) {
            throw new InvalidAmountOfDaysToReserveException("Days to reserve must be less than 5");
        }
        for (LocalDate localDate : reservationDays) {
            this.reserveDay(localDate);
        }
    }

    public void releaseDays(LinkedList<LocalDate> reservationDays) throws DayNotReservedException {
        for (LocalDate localDate : reservationDays) {
            this.releaseDay(localDate);
        }
    }

    public void disableDays(List<LocalDate> reservationDays) throws DayAlreadyDisabledException {
        for (LocalDate localDate : reservationDays) {
            this.setDisabled(localDate);
        }
    }

}

