package architeture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import java.util.Map;
import api.forms.PublicationForm;
import api.forms.UserForm;
import api.forms.VehicleForm;
import model.Publication;
import model.User;
import model.VehicleType;
import model.exceptions.FormValidationError;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.PublishService;
import services.UserService;
import utils.builders.PublicationFormBuilder;
import utils.builders.UserBuilder;
import utils.builders.VehicleBuilder;


@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration( {"/META-INF/spring-persistence-context.xml",
                        "/META-INF/spring-services-test.xml"})

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class TestArchitecture extends AbstractTransactionalJUnit4SpringContextTests {

        private Session session;

        @Autowired
        private SessionFactory sessionFactory;
        private UserService userService;
        private PublishService publicationService;

        @Before
        public void setUp() {
            session = sessionFactory.openSession();
        }

        @After
        public void tearDown() {
            session.close();
        }

        @Test
        public void testMaps() {
            Map metadata = sessionFactory.getAllClassMetadata();

            for (Iterator it = metadata.values().iterator(); it.hasNext();) {
                EntityPersister persister = (EntityPersister) it.next();
                Query q = session.createQuery("from " + persister.getEntityName() + " c");
                q.setMaxResults(1);
                q.iterate();
            }
            assertTrue(true);
        }

    }
