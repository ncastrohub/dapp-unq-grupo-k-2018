package repositories;

import model.Vehicle;

public class VehicleRepository extends HibernateGenericDAO<Vehicle> implements GenericRepository<Vehicle> {

    private static final long serialVersionUID = -4036535812105672110L;

    @Override
    protected Class<Vehicle> getDomainClass() {
        return Vehicle.class;
    }
}

