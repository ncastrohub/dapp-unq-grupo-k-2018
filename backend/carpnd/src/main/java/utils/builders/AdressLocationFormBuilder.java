package utils.builders;

import api.forms.AdressLocationForm;

public class AdressLocationFormBuilder {

    public static AdressLocationForm some(){
        AdressLocationForm aLocationForm = new AdressLocationForm();
        aLocationForm.geoLongitude = "123123123";
        aLocationForm.geoLatitude = "78979789";
        aLocationForm.adressName = "Estanislao del Campo";
        return aLocationForm;
    }

}
