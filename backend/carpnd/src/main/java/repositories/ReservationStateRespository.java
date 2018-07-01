package repositories;

import model.ReservationState;

public class ReservationStateRespository extends HibernateGenericDAO<ReservationState> implements GenericRepository<ReservationState> {
    @Override
    protected Class<ReservationState> getDomainClass() {
        return ReservationState.class;
    }
}
