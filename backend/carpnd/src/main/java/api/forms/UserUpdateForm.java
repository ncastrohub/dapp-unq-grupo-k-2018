package api.forms;
import api.forms.UserForm;
import me.geso.tinyvalidator.constraints.NotNull;

import java.io.Serializable;
public class UserUpdateForm extends UserForm {

    @NotNull
    public Serializable id;


}
