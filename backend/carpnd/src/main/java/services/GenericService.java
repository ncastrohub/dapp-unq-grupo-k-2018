package services;

import org.springframework.transaction.annotation.Transactional;
import repositories.GenericRepository;
import utils.OwnPaginationPage;

import java.io.Serializable;
import java.util.List;

public class GenericService<T> implements Serializable {

    private static final long serialVersionUID = -6540963495078524186L;

    private GenericRepository<T> repository;

    GenericRepository<T> getRepository() {
        return this.repository;
    }

    public void setRepository(final GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public void delete(final T object) {
        this.getRepository().delete(object);
    }

    @Transactional
    public void deleteById(Serializable id) {

        this.getRepository().delete(this.getRepository().findById(id));
    }

    @Transactional(readOnly = true)
    public List<T> retriveAll() {
        return this.getRepository().findAll();
    }

    @Transactional
    public void save(final T object) {
        this.getRepository().save(object);
    }

    @Transactional
    public void update(final T object) {
        this.getRepository().update(object);
    }

    @Transactional(readOnly = true)
    public T findById(Serializable id) {
        return this.repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<T> getAllByPage(final int pageSize, final int pageNumber) {return this.repository.getAllByPage(pageSize, pageNumber);}

    @Transactional(readOnly = true)
    public OwnPaginationPage<T> getPaginationPage(Integer pageNumber){return this.repository.getPaginationPage(pageNumber);}

    @Transactional(readOnly = true)
    public OwnPaginationPage<T> getPaginationPage(){return this.repository.getPaginationPage();}

}
