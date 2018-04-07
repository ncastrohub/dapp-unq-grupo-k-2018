package model;

import model.Exceptions.DayAlreadyDisabledException;
import model.Exceptions.DayDisabledException;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class PublicationsEnabledDays {
    private List<LocalDate> disabledDays;


    public PublicationsEnabledDays(){
        this.disabledDays = new LinkedList<>();
    }

    public List<LocalDate> getDisabledDays() {
        return disabledDays;
    }

    public void reserveDay(LocalDate dayOne) throws DayDisabledException {
        if (disabledDays.contains(dayOne)) {
            throw new DayDisabledException();
        }
    }

    public void setDisabled(LocalDate disabled) throws DayAlreadyDisabledException {
        if (this.disabledDays.contains(disabled)){
            throw new DayAlreadyDisabledException();
        }
        this.disabledDays.add(disabled);
    }
}

