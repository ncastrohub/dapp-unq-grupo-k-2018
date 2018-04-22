package services;

import model.Vehicle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class VehicleService extends GenericService<Vehicle> {

    private static final long serialVersionUID = 2131359482422367092L;

//    @Transactional(readOnly = true)
//    public List<Vehicle> vehicleList(Long id) {
//        List<Vehicle> vehicles = this.retriveAll();
//
//        return vehicles;
//    }



}

