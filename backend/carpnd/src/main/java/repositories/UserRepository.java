package repositories;

import model.User;

public class UserRepository extends HibernateGenericDAO<User> implements GenericRepository<User> {

    private static final long serialVersionUID = -4036535812105672110L;

    @Override
    protected Class<User> getDomainClass() {
        return User.class;
    }
}
