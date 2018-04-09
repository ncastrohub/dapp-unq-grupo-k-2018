package model;

    class WaitingForOwner extends StateNode {
        void ownerAccepts(ReservationState context){
            context.setState(new ConfirmedByBothSides());
        }

        void ownerRejects(ReservationState context){
            context.setState(new Rejected());
        }

        void userAccepts(ReservationState context) {
            // Nada que hacer, El usuario se acepta a sí mismo.
        }

        void userRejects(ReservationState context) {
            context.setState(new Cancelled());
        }
    }
