package utils.builders;

import api.forms.PublicationForm;

import java.util.LinkedList;

public class PublicationFormBuilder {

    public static PublicationForm some() {
        PublicationForm pForm = new PublicationForm();
        pForm.vehicle = VehicleUpdateFormBuilder.some();
        pForm.acquireLocation = AdressLocationFormBuilder.some();
        pForm.returnLocations = new LinkedList<>();
        pForm.returnLocations.add(AdressLocationFormBuilder.some());
        pForm.returnLocations.add(AdressLocationFormBuilder.some());
        pForm.disabledDays = new LinkedList<>();
        pForm.disabledDays.add(2);
        pForm.disabledDays.add(3);
        pForm.costPerHour = MoneyAndAmountFormBuilder.some();
        return pForm;
    }


}
