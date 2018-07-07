package utils.builders;

import model.AdressLocation;

public class AdressLocationBuilder {


    public String adressName;
    public String adressNumber;
    public String geoLatitude;
    public String geoLongitude;

    public static AdressLocationBuilder start() {
        return new AdressLocationBuilder();
    }

    public static AdressLocation some() {
        AdressLocation aAndLocation = new AdressLocation();
        aAndLocation.geoLongitude = "123123123";
        aAndLocation.geoLatitude = "098098098";
        aAndLocation.adressName = "Estanislao del Campo";
        aAndLocation.adressName = "2137";
        return aAndLocation;
    }

    public AdressLocation getOne() {
        AdressLocation aAndLocation = new AdressLocation();
        aAndLocation.geoLongitude = "123123123";
        aAndLocation.geoLatitude = "098098098";
        aAndLocation.adressName = "Estanislao del Campo";
        aAndLocation.adressName = "2137";
        return aAndLocation;
    }
}
