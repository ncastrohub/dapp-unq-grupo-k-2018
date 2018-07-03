package api.forms;

import lombok.Data;
import me.geso.tinyvalidator.constraints.NotNull;
import me.geso.tinyvalidator.constraints.Size;


@Data
public class UserForm {

    public UserForm() {}

    @Size(min=2, max=30)
    @NotNull(message = "required")
    public String name;

    @Size(min=2, max=30)
    @NotNull(message = "required")
    public String lastName;

    public String cuil;

    @Size(min=8, max=30)
    @NotNull(message = "required")
    public String email;

}
