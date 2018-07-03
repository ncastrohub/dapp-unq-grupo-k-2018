package model;

public class MoneyAndAmount extends IdModel {

    public CustomCurrencies currency;
    public Double amount;

    public CustomCurrencies getCurrency() {
        return currency;
    }

    public void setCurrency(CustomCurrencies currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public MoneyAndAmount(){}

    public MoneyAndAmount(Double amount, CustomCurrencies aCurrency) {
        this.amount = amount;
        this.currency = aCurrency;
    }

    public MoneyAndAmount plusBy(Double value) {
        return new MoneyAndAmount(this.amount * value, this.currency);
    }

    public boolean equalss(Object model){
        if ( model instanceof MoneyAndAmount){
            MoneyAndAmount modelCasted = ((MoneyAndAmount) model);
            return this.currency == modelCasted.currency && this.amount.equals(modelCasted.amount);
        }else{
            return super.equals(model);
        }
    }

    public MoneyAndAmount sum(MoneyAndAmount moneyAndAmount) {
        this.amount = this.amount + moneyAndAmount.amount;
        return this;
    }

    public boolean isMayorTo(MoneyAndAmount moneyAndAmount) {
        return this.amount > moneyAndAmount.amount;
    }

    public void subtract(MoneyAndAmount moneyAndAmount) {
        this.amount = this.amount - moneyAndAmount.amount;
    }

    public MoneyAndAmount createNew() {
        return new MoneyAndAmount(this.amount, this.currency);
    }

    public MoneyAndAmount reduceAndGet(MoneyAndAmount moneyAndAmount) {
        return new MoneyAndAmount(this.amount - moneyAndAmount.amount, CustomCurrencies.ARS);
    }
}
