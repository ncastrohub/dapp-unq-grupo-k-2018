package fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import services.PublishService;

import javax.annotation.PostConstruct;

@Component
public class FixtureWirer {

    @Autowired
    private PublishService publishService;

    @Autowired
    private Environment environment;

    public PublishService getPublishService() {
        return publishService;
    }

    public void setPublishService(PublishService publishService) {
        this.publishService = publishService;
    }

    @PostConstruct
    public void init() {
       String nachito = "nachito";
    }
}