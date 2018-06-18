package utils.builders;

import api.forms.VehicleForm;
import api.forms.VehicleUpdateForm;
import model.VehicleType;

public class VehicleUpdateFormBuilder {

    public static VehicleUpdateForm some(){
        VehicleUpdateForm vForm = new VehicleUpdateForm();
        vForm.type = VehicleType.COUPE;
        vForm.capacity = 4;
        vForm.photo = "https://vignette.wikia.nocookie.net/inciclopedia/images/4/40/Ferrari.jpg/revision/latest?cb=20080228132933";
        vForm.description = "A nice and confortable car";
        return vForm;
    }

}
