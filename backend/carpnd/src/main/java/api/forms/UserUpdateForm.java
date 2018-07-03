package api.forms;
import lombok.Data;
import me.geso.tinyvalidator.constraints.NotNull;

@Data
public class UserUpdateForm extends UserForm {

    @NotNull
    public Long id;


    MoneyAndAmountUpdateForm availableMoney;

}
