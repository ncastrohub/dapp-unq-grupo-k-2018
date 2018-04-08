package model;

public class ReservationMoney {
    private final MoneyAndAmount reservedMoney;
    private final Reservation reservation;

    public ReservationMoney(MoneyAndAmount moneyAndAmount, Reservation reservation) {
        this.reservedMoney = moneyAndAmount;
        this.reservation = reservation;
    }
}
