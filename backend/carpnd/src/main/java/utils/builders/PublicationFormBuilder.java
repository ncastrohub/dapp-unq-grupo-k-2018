package utils.builders;

import api.forms.PublicationForm;

import java.util.LinkedList;

public class PublicationFormBuilder {

    public static PublicationForm some() {
        PublicationForm pForm = new PublicationForm();
        pForm.vehicle = VehicleFormBuilder.some();
        pForm.acquireLocation = AdressLocationFormBuilder.some();
        pForm.returnLocations = new LinkedList<>();
        pForm.returnLocations.add(AdressLocationFormBuilder.some());
        pForm.returnLocations.add(AdressLocationFormBuilder.some());
        pForm.enabledDays = PublicationsEnabledDaysFormBuilder.some();
        return pForm;
    }


}
