package architecture;

import model.IdModel;
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
            Method getId = intance.getClass().getMethod("getId");

            sessionFactory.getCurrentSession().save(intance);
            Long intaceId = ((Long) getId.invoke(intance, null));

            Object objectsList = sessionFactory.getCurrentSession().get(intance.getClass(), intaceId);

            EqualsBuilder equals = new EqualsBuilder();

            if (! EqualsBuilder.reflectionEquals(intance, objectsList, "")){
                String nachito = "nachito";
                assert false;
            }else{
                assert true;
            }


            q.iterate();

        }
    }

}
