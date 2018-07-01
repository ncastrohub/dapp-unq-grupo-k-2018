package services;

import model.Publication;
import org.springframework.transaction.annotation.Transactional;
import repositories.PublicationRespository;

import java.util.List;

public class PublicationService extends GenericService<Publication> {


    @Transactional(readOnly = true)
    @SuppressWarnings({"LambdaParameterTypeCanBeSpecified", "unchecked"})
    public List<Publication> findByVehicleType() {
        return ((PublicationRespository) this.getRepository()).findByVehicleType();
    }
        //        int pageSize = 4;
//        int pageNumber = 2;
//        HibernateTemplate template = this.getRepository().getHibernateTemplate();
//        return (List<Publication>) template.execute((HibernateCallback) session -> {
//            Criteria criteria = session.createCriteria(this.getDomainClass()).
//                    createCriteria(String.valueOf(Vehicle.class)).add(Restrictions.eq("type", VehicleType.SEDAN));
////                Query query = session.createQuery("from "+ this.getDomainClass().getName() + " o");
//            criteria.setMaxResults(pageSize);
//            criteria.setFirstResult(pageSize * pageNumber);
//            return criteria.list();
//        });


}
