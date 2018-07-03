package fixtures;

import model.User;
import model.Vehicle;
import model.VehicleType;
import services.PublishService;
import utils.builders.UserBuilder;
import utils.builders.VehicleBuilder;

public class AppFixture {

    public AppFixture(PublishService service) {
        String nacho = " nacho";
        User owner = UserBuilder.start()
                .withCUIL("")
                .withEmail("nazarenomartincastro@gmail.com").withName("Nazareno").withLastName("Castro").build();
        Vehicle vehiculo1 = VehicleBuilder.start().withCapacity(5).withDescription("El torino, el auto mas argento, listo para que lo uses").withPhoto("https://imganuncios.mitula.net/renault_torino_1970_nafta_gnc_renault_torino_3320040522510729888.jpg").withType(VehicleType.COUPE).withOwner(owner).build();
        owner.addVehicle(vehiculo1);
        service.getUserService().save(owner);

    }

}
