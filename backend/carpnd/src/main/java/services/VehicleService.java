package services;

import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import repositories.VehicleRepository;

public class VehicleService extends GenericService2<Vehicle> {

    @Autowired()
    private VehicleRepository repository;

    public JpaRepository<Vehicle, Long> getRepository() {
        return this.repository;
    }

    public void setRepository(final VehicleRepository repository) {
        this.repository = repository;
    }

    private static final long serialVersionUID = 2131359482422367092L;

}

