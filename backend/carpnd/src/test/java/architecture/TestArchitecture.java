package architecture;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-test.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class TestArchitecture {

    @Autowired
    SessionFactory sessionFactory;


    @Test
    public void testAllMappingsForReads() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map metadata = sessionFactory.getAllClassMetadata();

        for (Object o : metadata.values()) {

            EntityPersister persister = (EntityPersister) o;

            Query q = sessionFactory.getCurrentSession().createQuery(

                    "from " + persister.getEntityName() + " c");

            Class<?> repo = Class.forName(persister.getEntityName());

            String mappedClass = persister.getMappedClass().getName().replace("model.", "");


            Class<?> BuilderClass = Class.forName("utils.builders." + mappedClass +"Builder");
            Method someClassMehthod = BuilderClass.getDeclaredMethod("some");
            Object intance = someClassMehthod.invoke(null, null);

            sessionFactory.getCurrentSession().save(intance);

            List objectsList = sessionFactory.getCurrentSession().createCriteria(intance.getClass().getName()).list();
            Object dbInstance = objectsList.get(0);

            String nachito = "nachito";

            EqualsBuilder equals = new EqualsBuilder();

            assert equals.reflectionEquals(intance, dbInstance, "");

            q.iterate();

        }
    }

}
