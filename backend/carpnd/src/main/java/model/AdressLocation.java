package model;

public class AdressLocation extends IdModel {

    public String adressName;
    public String adressNumber;
    public String geoLatitude;
    public String geoLongitude;

    public AdressLocation(String adressName, String adressNumber, String geoLatitude, String geoLongitude) {
        this.adressName = adressName;
        this.adressNumber = adressNumber;
        this.geoLatitude = geoLatitude;
        this.geoLongitude = geoLongitude;
    }

    public AdressLocation() {
    }

    public AdressLocation createNew() {

        return new AdressLocation(adressName, adressNumber, geoLatitude, geoLongitude);
    }
}
