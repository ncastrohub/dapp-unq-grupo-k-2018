package repositories;

import model.Vehicle;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;

import java.util.List;

public class VehicleRepository extends HibernateGenericDAO<Vehicle> implements GenericRepository<Vehicle> {

    private static final long serialVersionUID = -4036535812105672110L;

//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public List<Vehicle> filterPeople(final String pattern) {
//        return (List<Vehicle>) this.getHibernateTemplate().execute(new HibernateCallback() {
//            @Override
//            public List<Vehicle> doInHibernate(final Session session) throws HibernateException {
//                Criteria criteria = session.createCriteria(Vehicle.class);
//                criteria.add(Restrictions.like("name", "%" + pattern + "%"));
//                return criteria.list();
//            }
//
//        });
//    }

    @Override
    protected Class<Vehicle> getDomainClass() {
        return Vehicle.class;
    }
}

