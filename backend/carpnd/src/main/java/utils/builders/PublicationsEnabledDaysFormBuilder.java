package utils.builders;

import api.forms.PublicationsEnabledDaysForm;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;

import java.time.DayOfWeek;
import java.util.LinkedList;

public class PublicationsEnabledDaysFormBuilder {

    public static PublicationsEnabledDaysForm some() {
        PublicationsEnabledDaysForm pform = new PublicationsEnabledDaysForm();
        pform.disabledDays = new LinkedList<>();
        pform.disabledDays.add(DayOfWeek.FRIDAY.getValue());
        pform.disabledDays.add(DayOfWeek.MONDAY.getValue());
        pform.disabledDays.add(DayOfWeek.THURSDAY.getValue());

        pform.reservedDays = new LinkedList<>();
        pform.reservedDays.add(LocalDate.now().plusDays(6));
        pform.reservedDays.add(LocalDate.now().plusDays(7));
        pform.reservedDays.add(LocalDate.now().plusDays(8));
        return pform;
    }


}
