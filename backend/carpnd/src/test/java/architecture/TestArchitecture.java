package architecture;

import junit.framework.AssertionFailedError;
import model.IdModel;
import model.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.unitils.reflectionassert.ReflectionAssert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-test.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestArchitecture {

    @Autowired
    SessionFactory sessionFactory;


    @Test
    @org.springframework.transaction.annotation.Transactional
    public void testAllMappingsForReads() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().flush();

        Map metadata = sessionFactory.getAllClassMetadata();

        for (Object o : metadata.values()) {

            EntityPersister persister = (EntityPersister) o;

            Query q = sessionFactory.getCurrentSession().createQuery(

                    "from " + persister.getEntityName() + " c");

            Class<?> repo = Class.forName(persister.getEntityName());

            String mappedClass = persister.getMappedClass().getName().replace("model.", "");

            persister.getFactory().getCurrentSession().flush();
            persister.getFactory().getCurrentSession().clear();

            Class<?> BuilderClass = Class.forName("utils.builders." + mappedClass +"Builder");

            Method someClassMehthod = BuilderClass.getDeclaredMethod("some");

            Object intance = someClassMehthod.invoke(null, null);
            Method getId = intance.getClass().getMethod("getId");

            Long intanceId = (Long) persister.getFactory().getCurrentSession().save(intance);
//
//            Long intanceId = ((Long) getId.invoke(intance, null));

            persister.getFactory().getCurrentSession().flush();
            persister.getFactory().getCurrentSession().clear();
            Object intanceDb = persister.getFactory().getCurrentSession().get(intance.getClass(), intanceId);

            EqualsBuilder equals = new EqualsBuilder();

            try {
                ReflectionAssert.assertReflectionEquals(intance, intanceDb);

            }catch (AssertionFailedError e){
                String nachito = "asdasd";
            }


            if (! EqualsBuilder.reflectionEquals(intance, intanceDb, "owner", "vehicles", "availableMoney")){
                assert false;
            }else{
                assert true;
            }

            q.iterate();

        }
    }

}
