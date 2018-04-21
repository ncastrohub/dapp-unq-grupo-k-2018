package model;

public class Cancelled extends StateNode {
    @Override
    void ownerAccepts(ReservationState context) {
        // Nada que hacer
    }

    @Override
    void ownerRejects(ReservationState context) {
        // Nada que hacer
    }

    @Override
    void userAccepts(ReservationState context) {
        // Nada que hacer
    }

    @Override
    void userRejects(ReservationState context) {
        // Nada que hacer
    }
}
