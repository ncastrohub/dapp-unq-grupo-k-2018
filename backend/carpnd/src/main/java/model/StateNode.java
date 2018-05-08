package model;

public abstract class StateNode{
        public abstract void ownerAccepts(ReservationState context);
        public abstract void ownerRejects(ReservationState context);
        public abstract void userAccepts(ReservationState context);
        public abstract void userRejects(ReservationState context);
    }

