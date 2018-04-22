package services;

public class GeneralService {

    private VehicleService vehicleService;

    public VehicleService getShiftService() {
        return vehicleService;
    }

    public void setVehicleService(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

}
