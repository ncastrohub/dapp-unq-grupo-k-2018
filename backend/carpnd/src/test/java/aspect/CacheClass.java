package aspect;


import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.PublishService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-test.xml"})
public class CacheClass {

    public PublishService getService() {
        return service;
    }

    public void setService(PublishService service) {
        this.service = service;
    }

    @Autowired
    public PublishService service;

    @Test
    public void test_ascpect_works(){
        User user = new User();
        service.getMailService();
    }

}