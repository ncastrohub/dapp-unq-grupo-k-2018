package model;

import javax.xml.stream.Location;

public class Publication {


    public MoneyAndAmount costPerHour;
    public User ouner;
    public AdressLocation acquireLocation;
    public AdressLocation restoreLocation;
    public DaySchedule availableSchedules;
    public Telephone telephone;

    public Publication(MoneyAndAmount costPerHour, User user, AdressLocation acquireLocation, AdressLocation restoreLocation, DaySchedule availableSchedules, Telephone telephone)
    {
        this.costPerHour=costPerHour;
        this.ouner=user;
        this.acquireLocation=acquireLocation;
        this.restoreLocation=restoreLocation;
        this.availableSchedules=availableSchedules;
        this.telephone=telephone;
    }

    public MoneyAndAmount getCostPerHour() {
        return this.costPerHour;
    }
}
