package utils.builders;

import api.forms.VehicleForm;
import model.VehicleType;

public class VehicleFormBuilder {


    public static VehicleForm some(){
        VehicleForm vForm = new VehicleForm();
        vForm.type = VehicleType.COUPE;
        vForm.capacity = 4;
        vForm.photo = "https://image.jpg";
        vForm.description = "A nice and confortable car";
        return vForm;
    }

}
