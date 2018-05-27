package api.forms;
import me.geso.tinyvalidator.constraints.NotNull;
public class UserUpdateForm extends UserForm {

    @NotNull
    public Long id;


}
