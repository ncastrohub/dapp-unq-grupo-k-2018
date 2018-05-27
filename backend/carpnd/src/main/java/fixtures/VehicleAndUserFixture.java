package fixtures;

import model.User;
import services.PublicationConcernService;
import utils.builders.UserBuilder;
import utils.builders.VehicleBuilder;

public class VehicleAndUserFixture {


    PublicationConcernService publicationService;

    public PublicationConcernService getPublicationService() {
        return publicationService;
    }

    public void setPublicationService(PublicationConcernService publicationService) {
        this.publicationService = publicationService;
    }

    public VehicleAndUserFixture() {
        User user = UserBuilder.someUser();
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        publicationService.getUserService().save(user);
    }


}
