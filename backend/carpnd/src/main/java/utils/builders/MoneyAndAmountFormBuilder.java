package utils.builders;

import api.forms.MoneyAndAmountForm;
import model.CustomCurrencies;

public class MoneyAndAmountFormBuilder {
    public static MoneyAndAmountForm some() {
        MoneyAndAmountForm moneyAndAmountForm = new MoneyAndAmountForm();
        moneyAndAmountForm.amount = 12.12;
        moneyAndAmountForm.currency = CustomCurrencies.ARS;
        return moneyAndAmountForm;
    }
}
