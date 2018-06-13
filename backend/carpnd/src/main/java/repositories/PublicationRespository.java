package repositories;

import model.Publication;

public class PublicationRespository extends HibernateGenericDAO<Publication> implements GenericRepository<Publication> {
    @Override
    protected Class<Publication> getDomainClass() {
        return Publication.class;
    }
}
