package utils.builders;

import api.forms.PublicationsEnabledDaysForm;

import org.joda.time.LocalDate;
import java.util.LinkedList;

public class PublicationsEnabledDaysFormBuilder {

    public static PublicationsEnabledDaysForm some() {
        PublicationsEnabledDaysForm pform = new PublicationsEnabledDaysForm();
        pform.disabledDays = new LinkedList<>();
        pform.disabledDays.add(LocalDate.now());
        pform.disabledDays.add(LocalDate.now().plusDays(1));
        pform.disabledDays.add(LocalDate.now().plusDays(2));

        pform.reservedDays = new LinkedList<>();
        pform.reservedDays.add(LocalDate.now().plusDays(6));
        pform.reservedDays.add(LocalDate.now().plusDays(7));
        pform.reservedDays.add(LocalDate.now().plusDays(8));
        return pform;
    }


}
