package model;

class WaitingForOwner extends StateNode {
    public void ownerAccepts(ReservationState context) {
//        context.setState(new ConfirmedByBothSides());
    }

    public void ownerRejects(ReservationState context) {

//        context.setState(new Rejected());
    }

    public void userAccepts(ReservationState context) {
        // Nada que hacer
    }

    public void userRejects(ReservationState context) {

//        context.setState(new Cancelled());
    }
}
