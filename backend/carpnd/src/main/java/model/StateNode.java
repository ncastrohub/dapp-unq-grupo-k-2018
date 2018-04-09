package model;

public abstract class StateNode{
        abstract void ownerAccepts(ReservationState context);
        abstract void ownerRejects(ReservationState context);
        abstract void userAccepts(ReservationState context);
        abstract void userRejects(ReservationState context);
    }

