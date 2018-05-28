package scripting;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import services.PublishService;
import utils.builders.UserBuilder;

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
