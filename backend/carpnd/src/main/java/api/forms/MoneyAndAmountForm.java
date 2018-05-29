package api.forms;

import model.CustomCurrencies;
import model.MoneyAndAmount;

public class MoneyAndAmountForm {

    public CustomCurrencies currency;
    public Double amount;

    public MoneyAndAmount getModelInstance() {
        return  new MoneyAndAmount(this.amount, this.currency);
    }

}
