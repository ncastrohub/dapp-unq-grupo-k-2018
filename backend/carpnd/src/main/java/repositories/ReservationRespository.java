package repositories;

import model.Publication;
import model.Reservation;
import model.VehicleType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import utils.ListAndTotal;

public class ReservationRespository extends HibernateGenericDAO<Reservation> implements GenericRepository<Reservation> {
    @Override
    protected Class<Reservation> getDomainClass() {
        return Reservation.class;
    }

    @SuppressWarnings("unchecked")
    public ListAndTotal<Reservation> findForOwner(Long ownerId, Integer pageNumber){
        int pageSize = 4;
        HibernateTemplate template = this.getHibernateTemplate();
        return (ListAndTotal<Reservation>) template.execute((HibernateCallback) session -> {
            Criteria criteria = session.createCriteria(this.getDomainClass(), "reservation").
                    createAlias("reservation.publication", "publication")
                    .createAlias("publication.owner", "owner")
                    .add(Restrictions.eq("owner.id", ownerId));
            int total = criteria.list().size();
            criteria.setMaxResults(pageSize);
            criteria.setFirstResult(pageSize * pageNumber);
            return new ListAndTotal<Publication>(criteria.list(), total);
        });
    }

    @SuppressWarnings("unchecked")
    public ListAndTotal<Reservation> findForCustomer(Long customerId, Integer pageNumber){
        int pageSize = 4;
        HibernateTemplate template = this.getHibernateTemplate();
        return (ListAndTotal<Reservation>) template.execute((HibernateCallback) session -> {
            Criteria criteria = session.createCriteria(this.getDomainClass(), "reservation").
                    createAlias("reservation.customer", "customer")
                    .add(Restrictions.eq("customer.id", customerId));
            int total = criteria.list().size();
            criteria.setMaxResults(pageSize);
            criteria.setFirstResult(pageSize * pageNumber);
            return new ListAndTotal<Publication>(criteria.list(), total);
        });
    }

}
