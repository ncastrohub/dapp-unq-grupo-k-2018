package model;

import model.exceptions.*;
import org.joda.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

public class PublicationsEnabledDays extends IdModel {

    private List<Integer> disabledDays;
    private List<LocalDate> reservedDays;
    public Publication publication;

    public List<LocalDate> getReservedDays() {
        return reservedDays;
    }


    public PublicationsEnabledDays(){}

    public void setReservedDays(List<LocalDate> reservedDays) {
        this.reservedDays = reservedDays;
    }

    PublicationsEnabledDays(List<Integer> disabledDays){
        this.disabledDays = disabledDays;
        this.reservedDays = new LinkedList<>();
    }

    public PublicationsEnabledDays(List<LocalDate> reservedDays, List<Integer> disabledDays) {
        this.disabledDays = disabledDays;
        this.reservedDays = reservedDays;

    }

    public void reserveDay(LocalDate dayOne) throws DayDisabledException, DayAlreadyReservedException {
        if (this.disabledDays.contains(dayOne.getDayOfWeek())) {
            throw new DayDisabledException("One Selected Day is Disabled");
        }
        if (reservedDays.contains(dayOne)){
            throw new DayAlreadyReservedException("One of selected Days are reserved");
        }
        this.reservedDays.add(dayOne);
    }

    public void setDisabledDays(List<Integer> disabledDays){
        this.disabledDays = disabledDays;
    }

    public boolean canReserve(LocalDate dayOne) {
        return !this.reservedDays.contains(dayOne) && !this.disabledDays.contains(dayOne.getDayOfWeek());
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

    public boolean canReserveDays(List<LocalDate> reservationDays) {
        Boolean can = true;
        for (LocalDate day: reservationDays){
            can = can && this.canReserve(day);
        }
        return can;
    }

    public List<Integer> getDisabledDays() {
        return disabledDays;
    }

    public void setDisabled(int disabled) {
        this.disabledDays.add(disabled);
    }
}

