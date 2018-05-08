package model;

class ConfirmedByBothSides extends StateNode {

    @Override
    public void ownerAccepts(ReservationState context) {
        context.setState(new Rejected());
    }

    @Override
    public void ownerRejects(ReservationState context) {
        context.setState(new Rejected());
    }

    @Override
    public void userAccepts(ReservationState context) {
        context.setState(new InProcess());
    }

    @Override
    public void userRejects(ReservationState context) {
        context.setState(new InProcess());
    }
}
