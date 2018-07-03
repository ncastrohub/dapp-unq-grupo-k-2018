package fixtures;

import model.*;
import services.PublishService;
import utils.builders.UserBuilder;
import utils.builders.VehicleBuilder;

import java.util.LinkedList;

public class AppFixture {

    public AppFixture(PublishService service) {
        String nacho = " nacho";
        User owner = UserBuilder.start()
                .withCUIL("")
                .withEmail("nazarenomartincastro@gmail.com").withName("Nazareno").withLastName("Castro").build();
        Vehicle vehiculo1 = VehicleBuilder.start().withCapacity(5).withDescription("El torino, el auto mas argento, listo para que lo uses").withPhoto("https://imganuncios.mitula.net/renault_torino_1970_nafta_gnc_renault_torino_3320040522510729888.jpg").withType(VehicleType.COUPE).withOwner(owner).build();
        Vehicle vehiculo2 = VehicleBuilder.start().withCapacity(5).withDescription("Un lindo falcon rojo para salir de joda").withPhoto("https://upload.wikimedia.org/wikipedia/commons/6/64/Ford_Falcon_%28Auto_classique_St-Lin-Laurentides_%2713%29.JPG").withType(VehicleType.SEDAN).withOwner(owner).build();

        owner.addVehicle(vehiculo1);
        owner.addVehicle(vehiculo2);
        service.getUserService().save(owner);
        MoneyAndAmount costPublication = new MoneyAndAmountForPublication(20.00, CustomCurrencies.ARS);
        AdressLocation acquireLocation = new AdressLocation();
        acquireLocation.adressName = "Presidente Peron";
        acquireLocation.geoLatitude = "-7000000000";
        acquireLocation.geoLongitude = "7000000000";


        AdressLocation returnLocationA = new AdressLocation();
        returnLocationA.adressName = "Arturo Illia";
        returnLocationA.geoLatitude = "-7000000000";
        returnLocationA.geoLongitude = "7000000000";

        AdressLocation returnLocationB = new AdressLocation();
        returnLocationB.adressName = "San Martin";
        returnLocationB.geoLatitude = "-7000000000";
        returnLocationB.geoLongitude = "7000000000";

        LinkedList<AdressLocation> returnLocation = new LinkedList<>();
        returnLocation.add(returnLocationA);
        returnLocation.add(returnLocationB);

        LinkedList<Integer> disabledDays = new LinkedList<>();
        disabledDays.add(6);
        disabledDays.add(7);
        PublicationsEnabledDays publicationDays = new PublicationsEnabledDays();
        publicationDays.setDisabledDays(disabledDays);

        Publication newPublication = new Publication(
                owner,
                costPublication,
                vehiculo1,
                acquireLocation,
                returnLocation,
                disabledDays
        );
        service.getPublicationService().save(newPublication);

        User customer = UserBuilder.start()
                .withCUIL("")
                .withEmail("nazareno.castro@intive-fdv.com").withName("Nazareno").withLastName("CastroFDV").build();
        service.getUserService().save(customer);

    }

}
