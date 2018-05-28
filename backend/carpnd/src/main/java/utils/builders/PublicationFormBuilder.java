package utils.builders;

import api.forms.PublicationForm;

public class PublicationFormBuilder {

    public static PublicationForm some() {
        PublicationForm pForm = new PublicationForm();
        pForm.vehicle = VehicleFormBuilder.some();
        pForm.acquireLocation = AdressLocationFormBuilder.some();
        return pForm;
    }


}
