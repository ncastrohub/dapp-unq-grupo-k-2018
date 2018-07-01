package repositories;

import model.Publication;
import model.Vehicle;
import model.VehicleType;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import utils.OwnPaginationPage;

import java.util.List;

public class PublicationRespository extends HibernateGenericDAO<Publication> implements GenericRepository<Publication> {

    @Override
    protected Class<Publication> getDomainClass() {
        return Publication.class;
    }


    @SuppressWarnings({"LambdaParameterTypeCanBeSpecified", "unchecked"})
    public List<Publication> findByVehicleType(){
            int pageSize = 4;
            int pageNumber = 2;
            HibernateTemplate template = this.getHibernateTemplate();
            return (List<Publication>) template.execute((HibernateCallback) session -> {
                Criteria criteria = session.createCriteria(this.getDomainClass(), "publication").
                        createAlias("publication.vehicle", "vehicle") // here i changed

                .add(Restrictions.eq("vehicle.type", VehicleType.SEDAN));
//                projectionList.add(Projections.property("usercountry.name"),"usercountry.name");

//                createCriteria(String.valueOf(Vehicle.class)).add(Restrictions.eq("type", VehicleType.SEDAN));
//                Query query = session.createQuery("from "+ this.getDomainClass().getName() + " o");
//                criteria.setMaxResults(pageSize);
//                criteria.setFirstResult(pageSize * pageNumber);
                return criteria.list();
            });
    }


    public OwnPaginationPage<Publication> getPage(Integer pageNumber){
        List<Publication> elementList = this.getAllByPage(4, pageNumber);
        OwnPaginationPage<Publication> page = new OwnPaginationPage<>();
        page.setElementList(elementList);
        if (pageNumber > 0) {
            page.beforeUrl = "/" + this.getDomainClass().getSimpleName().toLowerCase() + "/list/" + (pageNumber - 1);
        }
        Integer total =  this.count();
        Integer currentPage = pageNumber + 1;
        if (total - (currentPage * 4) > 0) {
            page.nextUrl = "/" + this.getDomainClass().getSimpleName().toLowerCase() + "/list/" + (pageNumber  + 1);
        }
        return page;
    }

}
