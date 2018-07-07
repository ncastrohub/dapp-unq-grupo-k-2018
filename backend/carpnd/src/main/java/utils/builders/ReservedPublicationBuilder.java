package utils.builders;

import model.AdressLocation;
import model.Publication;
import model.ReservedPublication;
import org.joda.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

public class ReservedPublicationBuilder {

    public static ReservedPublication some(){
        AdressLocation returnLocationA = new AdressLocation();
        returnLocationA.adressName = "Arturo Illia";
        returnLocationA.geoLatitude = "-7000000000";
        returnLocationA.geoLongitude = "7000000000";

        AdressLocation returnLocationB = new AdressLocation();
        returnLocationB.adressName = "San Martin";
        returnLocationB.geoLatitude = "-7000000000";
        returnLocationB.geoLongitude = "7000000000";

        ReservedPublication reserved = new ReservedPublication();
        List<LocalDate> reservedDays = new LinkedList<>();
        reservedDays.add(LocalDate.now());
        reservedDays.add(LocalDate.now().plusDays(2));
        reserved.reservedDays = reservedDays;
        reserved.acquireLocation = returnLocationA;
        reserved.returnLocation = returnLocationB;
        reserved.publication = new Publication();

        return reserved;
    }


    public ReservedPublication getOne(){
        AdressLocation returnLocationA = new AdressLocation();
        returnLocationA.adressName = "Arturo Illia";
        returnLocationA.geoLatitude = "-7000000000";
        returnLocationA.geoLongitude = "7000000000";

        AdressLocation returnLocationB = new AdressLocation();
        returnLocationB.adressName = "San Martin";
        returnLocationB.geoLatitude = "-7000000000";
        returnLocationB.geoLongitude = "7000000000";

        ReservedPublication reserved = new ReservedPublication();
        List<LocalDate> reservedDays = new LinkedList<>();
        reservedDays.add(LocalDate.now());
        reservedDays.add(LocalDate.now().plusDays(2));
        reserved.reservedDays = reservedDays;
        reserved.acquireLocation = returnLocationA;
        reserved.returnLocation = returnLocationB;
        reserved.publication = new Publication();

        return reserved;
    }
}
