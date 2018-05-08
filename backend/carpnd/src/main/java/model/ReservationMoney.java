package model;

class ReservationMoney {
    private MoneyAndAmount reservedMoney;
    private Reservation reservation;

    ReservationMoney(MoneyAndAmount moneyAndAmount, Reservation reservation) {
        this.reservedMoney = moneyAndAmount;
        this.reservation = reservation;
    }
}
