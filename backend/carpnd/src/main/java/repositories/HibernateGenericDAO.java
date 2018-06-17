package repositories;

import org.hibernate.Query;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import utils.OwnPaginationPage;

import java.io.Serializable;
import java.util.List;

/**
 * Generic hibernate DAO
 *
 * @param <T>
 */
public abstract class HibernateGenericDAO<T> extends HibernateDaoSupport implements GenericRepository<T>, Serializable {

    private static final long serialVersionUID = 5058950102420892922L;

    private Class<T> persistentClass = this.getDomainClass();

    @SuppressWarnings("unchecked")
    public int count() {
        List<Long> list = (List<Long>) this.getHibernateTemplate()
                .find("select count(*) from " + this.persistentClass.getName() + " o");

        // this.getHibernateTemplate().execute(new HibernateCallback<Car>() {
        //
        // @Override
        // public Car doInHibernate(final Session session) throws
        // HibernateException, SQLException {
        // throw new UnsupportedOperationException();
        // }
        // });
        Long count = list.get(0);
        return count.intValue();

    }

    public void delete(final T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getHibernateTemplate().delete(obj);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from " + this.persistentClass.getName() + " o");
    }

    public List<T> findByExample(final T exampleObject) {
        return this.getHibernateTemplate().findByExample(exampleObject);

    }

    public T findById(final Serializable id) {
        return this.getHibernateTemplate().get(this.persistentClass, id);
    }

    protected abstract Class<T> getDomainClass();

    public void save(final T entity) {
        this.getHibernateTemplate().save(entity);
        this.getHibernateTemplate().flush();
    }

    public void update(final T entity) {
        this.getHibernateTemplate().update(entity);
    }

    /*Esto deberia ir en el service, pero no puedo obtener el domainClass`*/
    public OwnPaginationPage<T> getPaginationPage(Integer pageNumber){
        List<T> elementList = this.getAllByPage(4, pageNumber);
        OwnPaginationPage<T> page = new OwnPaginationPage<>();
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

    public OwnPaginationPage<T> getPaginationPage(){
        return this.getPaginationPage(0);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAllByPage(final int pageSize, final int pageNumber) {
        HibernateTemplate template = this.getHibernateTemplate();
        return (List<T>) template.execute((HibernateCallback) session -> {
            Query query = session.createQuery("from "+ this.persistentClass.getName() + " o");
            query.setMaxResults(pageSize);
            query.setFirstResult(pageSize * pageNumber);
            return query.list();
        });
    }

    @SuppressWarnings("unchecked")
    public List<T> getPageList(final int pageSize, final int pageNumber, String queryParameter) {
        HibernateTemplate template = this.getHibernateTemplate();
        return (List<T>) template.execute((HibernateCallback) session -> {
            Query query = session.createQuery(queryParameter);
            query.setMaxResults(pageSize);
            query.setFirstResult(pageSize * pageNumber);
            return query.list();
        });
    }

}
