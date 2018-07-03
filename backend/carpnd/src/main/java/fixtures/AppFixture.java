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
        Vehicle vehiculo3 = VehicleBuilder.start().withCapacity(3).withDescription("Autazo, no se que hago prestandolo. Cuidalo").withPhoto("https://auto.ndtvimg.com/car-images/big/ferrari/488-spider/ferrari-488-spider.jpg").withType(VehicleType.SEDAN).withOwner(owner).build();
        Vehicle vehiculo4 = VehicleBuilder.start().withCapacity(15).withDescription("No te falla nunca. Hace mucho ruido").withPhoto("https://images.clarin.com/2018/04/25/S1UIzM02z_1256x620__1.jpg").withType(VehicleType.SEDAN).withOwner(owner).build();
        Vehicle vehiculo5 = VehicleBuilder.start().withCapacity(2).withDescription("Una batata pero necesito guita").withPhoto("http://media.deautos.com/PublishableItem/1999419/1280x960/1999419_01_033.jpg").withType(VehicleType.SEDAN).withOwner(owner).build();

        Vehicle vehiculo6 = VehicleBuilder.start().withCapacity(6).withDescription("Un fierrazo, tiene un alto valor sentimental").withPhoto("https://imganuncios.mitula.net/renault_12_renault_12_ts_break_7780134503686806414.jpg").withType(VehicleType.SEDAN).withOwner(owner).build();
        Vehicle vehiculo7 = VehicleBuilder.start().withCapacity(2).withDescription("Necesita descripcion ?").withPhoto("https://http2.mlstatic.com/porsche-911-38-carrera-s-400cv-991-D_NQ_NP_940979-MLA26680954072_012018-F.jpg").withType(VehicleType.COUPE).withOwner(owner).build();
        Vehicle vehiculo8 = VehicleBuilder.start().withCapacity(10).withDescription("No es para la calle").withPhoto("https://http2.mlstatic.com/lancha-f-marine-175-cascos-nuevos-0-km-precio-sin-motor-D_NQ_NP_385025-MLA25351556070_022017-F.jpg").withType(VehicleType.SEDAN).withOwner(owner).build();

        owner.addVehicle(vehiculo1);
        owner.addVehicle(vehiculo2);
        owner.addVehicle(vehiculo3);
        owner.addVehicle(vehiculo4);
        owner.addVehicle(vehiculo5);
        owner.addVehicle(vehiculo6);
        owner.addVehicle(vehiculo7);
        owner.addVehicle(vehiculo8);
        service.getUserService().save(owner);


        service.getPublicationService().save(createPublication(owner, vehiculo1));
        service.getPublicationService().save(createPublication(owner, vehiculo2));
        service.getPublicationService().save(createPublication(owner, vehiculo3));
        service.getPublicationService().save(createPublication(owner, vehiculo4));
        service.getPublicationService().save(createPublication(owner, vehiculo5));

        User customer = UserBuilder.start()
                .withCUIL("")
                .withEmail("nazareno.castro@intive-fdv.com").withName("Nazareno").withLastName("CastroFDV").build();
        service.getUserService().save(customer);

    }


    Publication createPublication(User owner, Vehicle auto){
        MoneyAndAmountForPublication costPublication = new MoneyAndAmountForPublication(20.00, CustomCurrencies.ARS);
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
        disabledDays.add(0);
        disabledDays.add(6);
        PublicationsEnabledDays publicationDays = new PublicationsEnabledDays();
        publicationDays.setDisabledDays(disabledDays);

        Publication newPublication = new Publication(
                owner,
                costPublication,
                auto,
                acquireLocation,
                returnLocation,
                disabledDays
        );
        return newPublication;
    }

}
