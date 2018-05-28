package api.forms;

import me.geso.tinyvalidator.constraints.NotNull;
import model.AdressLocation;

public class AdressLocationForm {

    @NotNull
    public String adressName;

    @NotNull
    public String adressNumber;

    @NotNull
    public String geoLatitude;

    @NotNull
    public String geoLongitude;


    public AdressLocation getAcquireLocationInstance() {
        AdressLocation aAndLocation = new AdressLocation();
        aAndLocation.adressName = this.adressName;
        aAndLocation.adressNumber = this.adressNumber;
        aAndLocation.geoLatitude = this.geoLatitude;
        aAndLocation.geoLongitude = this.geoLongitude;
        return aAndLocation;
    }
}
