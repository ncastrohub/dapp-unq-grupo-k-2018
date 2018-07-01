package repositories;

import org.hibernate.Query;
import utils.OwnPaginationPage;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for generic DAO
 *
 * @param <T>
 */
public interface GenericRepository<T> {

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    T findById(Serializable id);

    List<T> findAll();

    void deleteById(Serializable id);

    int count();

    List<T> findByExample(T exampleObject);

    List<T> getAllByPage(final int pageSize, final int pageNumber);

    OwnPaginationPage<T> getPaginationPage();

    OwnPaginationPage<T> getPaginationPage(Integer pageNumber);

    List<T> getPageList(final int pageSize, final int pageNumber, String query);
}

