package services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;

public class ServiceForTest extends HibernateDaoSupport {

    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public Collection persisterMetadata(){
        Map metadata = sessionFactory.getAllClassMetadata();
        return metadata.values();
    }

    @Transactional
    public Long saveInstance(Object obj){
        this.sessionFactory.getCurrentSession().flush();
        this.sessionFactory.getCurrentSession().clear();
        return (Long) this.getHibernateTemplate().save(obj);
//        return (Long) sessionFactory.getCurrentSession().save(obj);
    }

    @Transactional(readOnly = true)
    public Object getIntance(Long intanceId, Class<?> aClass) {
        this.sessionFactory.getCurrentSession().flush();
        this.sessionFactory.getCurrentSession().clear();
        return sessionFactory.getCurrentSession().get(aClass, intanceId);
    }
}
