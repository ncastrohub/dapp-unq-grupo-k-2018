package model;

import model.exceptions.NotEnoughCreditException;

import java.util.LinkedList;
import java.util.List;

public class CreditAccount {
    private User user;
    private MoneyAndAmount availableCredit;
    private List<ReservationMoney> reservedMoney;

    CreditAccount(User user) {
        this.availableCredit = new MoneyAndAmount(5.00, CustomCurrencies.ARS);
        this.reservedMoney = new LinkedList<>();
        this.user = user;
    }

    public MoneyAndAmount avaibleCredit() {
        return this.availableCredit;
    }

    public void addCredit(MoneyAndAmount moneyAndAmount) {
        this.availableCredit = this.availableCredit.sum(moneyAndAmount);
    }

    public void reserveMoney(MoneyAndAmount moneyAndAmount, Reservation reservation) throws NotEnoughCreditException {
        if (this.availableCredit.isMayorTo(moneyAndAmount) || this.availableCredit.equals(moneyAndAmount)){
            this.availableCredit.subtract(moneyAndAmount);
            this.reservedMoney.add(new ReservationMoney(moneyAndAmount, reservation));
        }else{
            throw new NotEnoughCreditException("customer has not enough money");
        }
    }
}
