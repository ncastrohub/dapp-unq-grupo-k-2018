package fixtures;

import api.forms.PublicationForm;
import model.User;
import model.VehicleType;
import model.exceptions.FormValidationError;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import services.PublishService;
import utils.builders.PublicationFormBuilder;
import utils.builders.UserBuilder;
import utils.builders.VehicleBuilder;



/*
*
public class InitializeUser implements BeanPostProcessor {


    private PublishService publicationService;

    public PublishService getPublicationService() {
        return publicationService;
    }

    public void setPublicationService(PublishService publicationService) {
        this.publicationService = publicationService;
    }



    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        publicationService.getUserService().save(UserBuilder.someUser());
        return bean;
    }
}
*
* */


public class VehicleAndUserFixture implements BeanPostProcessor {


    private PublishService publishService;

    public PublishService getPublishService() {
        return publishService;
    }

    public void setPublishService(PublishService publishService) {
        this.publishService = publishService;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        User owner = UserBuilder.someUser();
        owner.addVehicle(VehicleBuilder.some());
        owner.addVehicle(VehicleBuilder.some());
        this.publishService.getUserService().save(owner);
        try {
            this.publishService.createPublicationForUser(owner.getId(), PublicationFormBuilder.some());
            this.publishService.createPublicationForUser(owner.getId(), PublicationFormBuilder.some());
            this.publishService.createPublicationForUser(owner.getId(), PublicationFormBuilder.some());
            this.publishService.createPublicationForUser(owner.getId(), PublicationFormBuilder.some());
        } catch (FormValidationError formValidationError) {
            formValidationError.printStackTrace();
        }

        PublicationForm secondPublicationForm = PublicationFormBuilder.some();
        secondPublicationForm.vehicle.type = VehicleType.SEDAN;
        try {
            this.publishService.createPublicationForUser(owner.getId(), secondPublicationForm);
        } catch (FormValidationError formValidationError) {
            formValidationError.printStackTrace();
        }

        PublicationForm thirdPublicationForm = PublicationFormBuilder.some();
        thirdPublicationForm.vehicle.type = VehicleType.VAN;
        try {
            this.publishService.createPublicationForUser(owner.getId(), thirdPublicationForm);
        } catch (FormValidationError formValidationError) {
            formValidationError.printStackTrace();
        }
        return bean;
    }
}
