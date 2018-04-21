package model;

public class ConfirmedByBothSides extends StateNode {

    @Override
    void ownerAccepts(ReservationState context) {
        context.setState(new Rejected());
    }

    @Override
    void ownerRejects(ReservationState context) {
        context.setState(new Rejected());
    }

    @Override
    void userAccepts(ReservationState context) {
        context.setState(new InProcess());
    }

    @Override
    void userRejects(ReservationState context) {
        context.setState(new InProcess());
    }
}
