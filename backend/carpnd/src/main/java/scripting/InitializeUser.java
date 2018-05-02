package scripting;

import model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import services.PublicationConcernService;
import utils.builders.UserBuilder;
import utils.builders.VehicleBuilder;

public class InitializeUser implements BeanPostProcessor {


    private PublicationConcernService publicationService;

    public PublicationConcernService getPublicationService() {
        return publicationService;
    }

    public void setPublicationService(PublicationConcernService publicationService) {
        this.publicationService = publicationService;
    }



    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        User user = UserBuilder.someUser();
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        user.addVehicle(VehicleBuilder.onlyWithOwner(user));
        publicationService.getUserService().save(user);


        return bean;
    }
}
