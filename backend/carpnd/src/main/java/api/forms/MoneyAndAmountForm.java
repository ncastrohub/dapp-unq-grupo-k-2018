package api.forms;

import lombok.Data;
import me.geso.tinyvalidator.constraints.NotNull;
import model.CustomCurrencies;
import model.MoneyAndAmount;
import model.MoneyAndAmountForPublication;

@Data
public class MoneyAndAmountForm {

    @NotNull
    public CustomCurrencies currency;

    @NotNull
    public Double amount;

    public MoneyAndAmount getModelInstance() {
        return  new MoneyAndAmount(this.amount, this.currency);
    }

    public MoneyAndAmountForPublication getModelInstancePublication() {
        return new MoneyAndAmountForPublication(this.amount, this.currency);
    }
}
