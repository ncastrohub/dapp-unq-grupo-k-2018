package services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import repositories.GenericRepository;

import java.io.Serializable;
import java.util.List;

public abstract class GenericService2<T> implements Serializable {

    private static final long serialVersionUID = -6540963495078524186L;

    abstract JpaRepository<T, Long> getRepository();

    @Transactional
    public void delete(final T object) {
        this.getRepository().delete(object);
    }

    @Transactional(readOnly = true)
    public List<T> retriveAll() {
        return this.getRepository().findAll();
    }

    @Transactional
    public void save(final T object) {
        this.getRepository().save(object);
    }

    public T getById(Long id) {
        return this.getRepository().findOne(id);
    }
}
