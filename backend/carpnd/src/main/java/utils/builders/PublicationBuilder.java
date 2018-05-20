package utils.builders;

import model.*;
import java.util.LinkedList;

public class PublicationBuilder {
    private User owner;
    private MoneyAndAmount pricePerHour;
    private Vehicle vehicle;
    private AdressLocation acquireLocation;
    private LinkedList<AdressLocation> returnLocations;
    private PublicationsEnabledDays enabledDays;


    public Publication build() {
        return new Publication(this.owner, this.pricePerHour, this.vehicle, this.acquireLocation, this.returnLocations, this.enabledDays);
    }

    public static PublicationBuilder start() {
        return new PublicationBuilder();
}

    public PublicationBuilder withOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public PublicationBuilder withPricePerHour(MoneyAndAmount pricePerHour) {
        this.pricePerHour = pricePerHour;
        return this;
    }

    public PublicationBuilder withVehicle(Vehicle car) {
        this.vehicle = car;
        return this;
    }

    public PublicationBuilder withAcquireLocation(AdressLocation acquirePlace) {
        this.acquireLocation = acquirePlace;
        return this;
    }

    public PublicationBuilder withRestoreLocations(LinkedList<AdressLocation> returnLocations) {
        this.returnLocations = returnLocations;
        return this;
    }

    public PublicationBuilder withAvaibleDays(PublicationsEnabledDays avaiblesDays) {
        this.enabledDays = avaiblesDays;
        return this;
    }

}
