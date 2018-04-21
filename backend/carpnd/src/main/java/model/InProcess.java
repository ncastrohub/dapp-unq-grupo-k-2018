package model;

import com.sun.net.httpserver.Authenticator;

public class InProcess extends StateNode {
    @Override
    void ownerAccepts(ReservationState context) {
        context.setState(new Success());
    }

    @Override
    void ownerRejects(ReservationState context) {
        context.setState(new Success());
    }

    @Override
    void userAccepts(ReservationState context) {
        context.setState(new Success());
    }

    @Override
    void userRejects(ReservationState context) {
        context.setState(new Success());
    }
}
