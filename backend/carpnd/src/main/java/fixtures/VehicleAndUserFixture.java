package fixtures;

import model.User;
import services.PublishService;
import utils.builders.UserBuilder;
import utils.builders.VehicleBuilder;

public class VehicleAndUserFixture {


    PublishService publicationService;

    public PublishService getPublicationService() {
        return publicationService;
    }

    public void setPublicationService(PublishService publicationService) {
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
