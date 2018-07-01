package services;

import api.forms.ReserveForm;
import model.AdressLocation;
import model.Publication;
import model.Reservation;
import model.User;
import model.exceptions.*;
import org.joda.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;
import services.Validators.GenericValidator;

import java.util.List;

public class ReserveService {

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public PublicationService getPublicationService() {
        return publicationService;
    }

    public void setPublicationService(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }

    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    private UserService userService;
    private VehicleService vehicleService;
    private PublicationService publicationService;
    private ReservationService reservationService;

    public Reservation makeReservation(Long customerId, List<LocalDate> daysToReserve, Long publicationId,
                                       Long returnLocationId) throws DayDisabledException, DayAlreadyReservedException,
            InvalidAmountOfDaysToReserveException, NoReturnLocationInPublicationException, NotEnoughCreditException {

        Publication publication = this.publicationService.findById(publicationId);
        User customer = this.userService.findById(customerId);
        AdressLocation returnLocation = publication.getReturnLocationsById(returnLocationId);
        Reservation reservation = publication.makeReservation(customer, daysToReserve, returnLocation);
        this.publicationService.update(publication);
        this.reservationService.save(reservation);
        return reservation;
    }

    public Reservation reservePublication(ReserveForm reserveForm) throws FormValidationError, NoReturnLocationInPublicationException, InvalidAmountOfDaysToReserveException, DayDisabledException, DayAlreadyReservedException, NotEnoughCreditException {
        GenericValidator.validate(reserveForm);
        return this.makeReservation(reserveForm.customer, reserveForm.reservationDays, reserveForm.publication, reserveForm.returnLocation);
    }

    @Transactional()
    public Reservation confirmByOwner(Long reservationId, User owner) throws ReservationStateError, NotReservationOwnerException {
        Reservation reservation = this.reservationService.getRepository().findById(reservationId);
        reservation.confirmByOwner(owner);
        this.reservationService.getRepository().update(reservation);
        return reservation;
    }

    @Transactional()
    public Reservation returnVehicle(Long reservationId, User owner) throws NotReservationOwnerException, ReservationStateError {
        Reservation reservation = this.reservationService.findById(reservationId);
        reservation.returnVehicle(owner);
        this.reservationService.update(reservation);
        return reservation;
    }

    @Transactional()
    public Object reject(Long reservationId, User owner) throws NotReservationOwnerException, ReservationStateError {
        Reservation reservation = this.reservationService.findById(reservationId);
        reservation.reject(owner);
        this.reservationService.update(reservation);
        return reservation;
    }
}
