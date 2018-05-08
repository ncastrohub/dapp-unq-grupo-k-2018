package model;

public class InProcess extends StateNode {
    @Override
    public void ownerAccepts(ReservationState context) {
        context.setState(new Success());
    }

    @Override
    public void ownerRejects(ReservationState context) {
        context.setState(new Success());
    }

    @Override
    public void userAccepts(ReservationState context) {
        context.setState(new Success());
    }

    @Override
    public void userRejects(ReservationState context) {
        context.setState(new Success());
    }
}
