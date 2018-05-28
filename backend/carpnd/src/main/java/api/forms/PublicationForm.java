package api.forms;

import me.geso.tinyvalidator.constraints.NotNull;
import model.AdressLocation;
import model.Vehicle;

public class PublicationForm {

    @NotNull
    public VehicleForm vehicle;

    @NotNull
    public AdressLocationForm acquireLocation;

    public Vehicle getVehicleInstance() {
        return this.vehicle.getVehicleInstance();
    }

    public AdressLocation getAcquireLocationInstance() {
        return this.acquireLocation.getAcquireLocationInstance();
    }

//    @NotNull
//    private LinkedList<AdressLocation> returnLocations;
//
//    @NotNull
//    private PublicationsEnabledDays enabledDays;
//
//    @NotNull
//    private MoneyAndAmount costPerHour;

}
