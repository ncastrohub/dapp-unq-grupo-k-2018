package utils.builders;

import model.PublicationsEnabledDays;
import org.joda.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

public class PublicationsEnabledDaysBuilder {


    public static PublicationsEnabledDays some() {
        PublicationsEnabledDays days = new PublicationsEnabledDays();
        List dDays = new LinkedList();
//        dDays.add(1);
//        dDays.add(2);
//
        List rDays = new LinkedList();
//        rDays.add(LocalDate.now());
//
        days.setDisabledDays(dDays);
        days.setReservedDays(rDays);
//        days.publication = null;
        return days;
    }

    public PublicationsEnabledDays getOne() {
        PublicationsEnabledDays days = new PublicationsEnabledDays();
        List dDays = new LinkedList();
        dDays.add(5);
//        dDays.add(2);
//
        List rDays = new LinkedList();
//        rDays.add(LocalDate.now());
//
        days.setDisabledDays(dDays);
        days.setReservedDays(rDays);
//        days.publication = null;
        return days;
    }
}
