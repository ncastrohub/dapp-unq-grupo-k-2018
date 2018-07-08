package architecture;

import junit.framework.AssertionFailedError;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.unitils.reflectionassert.ReflectionAssert;
import services.ServiceForTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-test.xml"})
public class TestArchitecture {


    public ServiceForTest getServiceTest() {
        return serviceTest;
    }

    public void setServiceTest(ServiceForTest serviceTest) {
        this.serviceTest = serviceTest;
    }

    @Autowired
    public ServiceForTest serviceTest;

    @Test
    public void testAllMappingsForReads() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Collection persisters = serviceTest.persisterMetadata();

        for (Object o : persisters) {

            EntityPersister persister = (EntityPersister) o;

            Class<?> repo = Class.forName(persister.getEntityName());

            String mappedClass = persister.getMappedClass().getName().replace("model.", "");


            checkClass(mappedClass);

        }
    }

    @SuppressWarnings("JavaReflectionInvocation")
    private void checkClass(String mappedClass) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Class<?> BuilderClass = Class.forName("utils.builders." + mappedClass +"Builder");
        Object builderClass = BuilderClass.newInstance();
        Method someClassMehthod = BuilderClass.getDeclaredMethod("getOne");
        Object instance = someClassMehthod.invoke(builderClass, null);
        Method getId = instance.getClass().getMethod("getId");

        serviceTest.saveInstance(instance);

        Long intanceId = (Long) getId.invoke(instance, null);
        Object intanceDb = serviceTest.getIntance(intanceId, instance.getClass());

        try {
            ReflectionAssert.assertReflectionEquals(instance, intanceDb);
        }catch (AssertionFailedError e){
            assert false;
            String nachito = "asdasd";
        }
    }

}
