package model;

public class MoneyAndAmount {
    public CustomCurrencies currency;
    public Double amount;

    public MoneyAndAmount(Double amount, CustomCurrencies aCurrency) {
        this.amount = amount;
        this.currency = aCurrency;
    }

    public MoneyAndAmount plusBy(Double value) {
        return new MoneyAndAmount(this.amount * value, this.currency);
    }

    @Override
    public boolean equals(Object model){
        if ( model instanceof MoneyAndAmount){
            MoneyAndAmount modelCasted = ((MoneyAndAmount) model);
            return this.currency == modelCasted.currency && this.amount.equals(modelCasted.amount);
        }else{
            return super.equals(model);
        }
    }

    public MoneyAndAmount add(MoneyAndAmount moneyAndAmount) {
        return new MoneyAndAmount(this.amount + moneyAndAmount.amount, CustomCurrencies.ARS);
    }

    public boolean isMayorTo(MoneyAndAmount moneyAndAmount) {
        return this.amount > moneyAndAmount.amount;
    }

    public void subtract(MoneyAndAmount moneyAndAmount) {
        this.amount = this.amount - moneyAndAmount.amount;
    }
}
