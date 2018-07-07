package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class AdressLocation {


    private Long id;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public String adressName;

    public String getAdressName() {
        return adressName;
    }

    public void setAdressName(String adressName) {
        this.adressName = adressName;
    }

    public String getAdressNumber() {
        return adressNumber;
    }

    public void setAdressNumber(String adressNumber) {
        this.adressNumber = adressNumber;
    }

    public String getGeoLatitude() {
        return geoLatitude;
    }

    public void setGeoLatitude(String geoLatitude) {
        this.geoLatitude = geoLatitude;
    }

    public ReservedPublication getReservedPublication() {
        return reservedPublication;
    }

    public void setReservedPublication(ReservedPublication reservedPublication) {
        this.reservedPublication = reservedPublication;
    }

    public String adressNumber;
    public String geoLatitude;

    public String getGeoLongitude() {
        return geoLongitude;
    }

    public void setGeoLongitude(String geoLongitude) {
        this.geoLongitude = geoLongitude;
    }

    public String geoLongitude;
    public ReservedPublication reservedPublication;


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
