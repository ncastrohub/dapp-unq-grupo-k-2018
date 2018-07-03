package api.forms;

import lombok.Data;
import me.geso.tinyvalidator.constraints.NotNull;
import me.geso.tinyvalidator.constraints.Size;
import model.*;

import java.util.LinkedList;
import java.util.List;

@Data
public class PublicationForm {

    @NotNull
    public VehicleUpdateForm vehicle;

    @NotNull
    public AdressLocationForm acquireLocation;

    @NotNull
    @Size(min = 1)
    public LinkedList<AdressLocationForm> returnLocations;

    public void setDisabledDays(List<Integer> disabledDays) {
        this.disabledDays = disabledDays;
    }

    @NotNull
    @Size(max = 7)
    public List<Integer> disabledDays;

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

    public List<Integer> getDisabledDays() {
        return disabledDays;
    }

    public MoneyAndAmountForPublication getCostPerDayInstance() {
        return costPerHour.getModelInstancePublication();
    }
}
