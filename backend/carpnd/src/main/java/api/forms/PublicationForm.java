package api.forms;

import lombok.Data;
import me.geso.tinyvalidator.constraints.NotNull;
import me.geso.tinyvalidator.constraints.Size;
import model.AdressLocation;
import model.MoneyAndAmount;
import model.PublicationsEnabledDays;
import model.Vehicle;

import java.util.LinkedList;

@Data
public class PublicationForm {

    @NotNull
    public VehicleUpdateForm vehicle;

    @NotNull
    public AdressLocationForm acquireLocation;

    @NotNull
    @Size(min = 1)
    public LinkedList<AdressLocationForm> returnLocations;

    public PublicationsEnabledDaysForm enabledDays;

    @NotNull
    public MoneyAndAmountForm costPerHour;

    public Vehicle getVehicleInstance() {
        return this.vehicle.getVehicleInstance();
    }

    public AdressLocation getAcquireLocationInstance() {
        return this.acquireLocation.getAcquireLocationInstance();
    }

    public LinkedList<AdressLocation> getReturnLocationsList(){
        LinkedList<AdressLocation> locationList = new LinkedList<>();
        for (AdressLocationForm location: this.returnLocations) {
            locationList.add(location.getAcquireLocationInstance());
        }
        return locationList;
    }

    public PublicationsEnabledDays getEnabledDaysInstance() {
        return enabledDays.getModelInstance();
    }

    public MoneyAndAmount getCostPerDayInstance() {
        return costPerHour.getModelInstance();
    }
}
