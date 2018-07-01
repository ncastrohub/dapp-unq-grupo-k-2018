package repositories;

import model.Publication;
import model.VehicleType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import utils.ListAndTotal;

public class PublicationRespository extends HibernateGenericDAO<Publication> implements GenericRepository<Publication> {

    @Override
    protected Class<Publication> getDomainClass() {
        return Publication.class;
    }


    @SuppressWarnings({"LambdaParameterTypeCanBeSpecified", "unchecked"})
    public ListAndTotal<Publication> findByVehicleType(VehicleType vehicleType, Integer pageNumber){
            int pageSize = 4;
            HibernateTemplate template = this.getHibernateTemplate();
            return (ListAndTotal<Publication>) template.execute((HibernateCallback) session -> {
                Criteria criteria = session.createCriteria(this.getDomainClass(), "publication").
                        createAlias("publication.vehicle", "vehicle")
                .add(Restrictions.eq("vehicle.type", vehicleType));
                int total = criteria.list().size();
                criteria.setMaxResults(pageSize);
                criteria.setFirstResult(pageSize * pageNumber);
                return new ListAndTotal<Publication>(criteria.list(), total);
            });
    }


}
