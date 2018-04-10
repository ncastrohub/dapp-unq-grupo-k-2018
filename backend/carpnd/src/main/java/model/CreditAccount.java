package model;

import model.exceptions.NotEnoughCreditException;

import java.util.LinkedList;

public class CreditAccount {
    private MoneyAndAmount availableCredit;
    private LinkedList<ReservationMoney> reservedMoney;

    public CreditAccount(User user) {
        this.availableCredit = new MoneyAndAmount(0.00, CustomCurrencies.ARS);
        this.reservedMoney = new LinkedList<>();
    }

    public MoneyAndAmount avaibleCredit() {
        return this.availableCredit;
    }

    public void addCredit(MoneyAndAmount moneyAndAmount) {
        this.availableCredit = this.availableCredit.add(moneyAndAmount);
    }

    public void reserveMoney(MoneyAndAmount moneyAndAmount, Reservation reservation) throws NotEnoughCreditException {
        if (this.availableCredit.isMayorTo(moneyAndAmount) || this.availableCredit.equals(moneyAndAmount)){
            this.availableCredit.subtract(moneyAndAmount);
            this.reservedMoney.add(new ReservationMoney(moneyAndAmount, reservation));
        }else{
            throw new NotEnoughCreditException();
        }
    }
}
