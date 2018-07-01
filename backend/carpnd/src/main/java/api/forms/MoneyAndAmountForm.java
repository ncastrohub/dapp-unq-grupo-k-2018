package api.forms;

import lombok.Data;
import me.geso.tinyvalidator.constraints.Min;
import me.geso.tinyvalidator.constraints.NotNull;
import model.CustomCurrencies;
import model.MoneyAndAmount;

@Data
public class MoneyAndAmountForm {

    @NotNull
    public CustomCurrencies currency;

    @NotNull
    public Double amount;

    public MoneyAndAmount getModelInstance() {
        return  new MoneyAndAmount(this.amount, this.currency);
    }

}
