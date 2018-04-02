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
}
