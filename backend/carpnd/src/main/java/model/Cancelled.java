package model;

public class Cancelled extends StateNode {
    @Override
    public void ownerAccepts(ReservationState context) {
        // Nada que hacer
    }

    @Override
    public void ownerRejects(ReservationState context) {
        // Nada que hacer
    }

    @Override
    public void userAccepts(ReservationState context) {
        // Nada que hacer
    }

    @Override
    public void userRejects(ReservationState context) {
        // Nada que hacer
    }
}
