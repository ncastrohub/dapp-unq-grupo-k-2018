package model;

import model.Exceptions.DayAlreadyDisabledException;
import model.Exceptions.DayAlreadyReservedException;
import model.Exceptions.DayDisabledException;
import model.Exceptions.DayNotReservedException;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class PublicationsEnabledDays {
    private List<LocalDate> disabledDays;
    private List<LocalDate> reservedDays;


    public PublicationsEnabledDays(){
        this.disabledDays = new LinkedList<>();
        this.reservedDays = new LinkedList<>();
    }

    public List<LocalDate> getDisabledDays() {
        return disabledDays;
    }

    public void reserveDay(LocalDate dayOne) throws DayDisabledException, DayAlreadyReservedException {
        if (disabledDays.contains(dayOne)) {
            throw new DayDisabledException();
        }
        if (reservedDays.contains(dayOne)){
            throw new DayAlreadyReservedException();
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

    public void reserveDays(LinkedList<LocalDate> reservationDays) throws DayAlreadyReservedException, DayDisabledException {
        for (LocalDate localDate : reservationDays) {
            this.reserveDay(localDate);
        }
    }
}

