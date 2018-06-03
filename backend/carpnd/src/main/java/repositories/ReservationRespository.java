package repositories;

import model.Reservation;

public class ReservationRespository extends HibernateGenericDAO<Reservation> implements GenericRepository<Reservation> {
    @Override
    protected Class<Reservation> getDomainClass() {
        return Reservation.class;
    }
}
