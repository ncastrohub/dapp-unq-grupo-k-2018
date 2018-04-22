package repositories;

import model.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "persistence.vehiclerepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}

