package model;

public abstract class ReservationState extends IdModel {

    public Boolean waitingForOwner;

    public abstract Boolean getWaitingForOwner();

    ReservationState(){}

}
