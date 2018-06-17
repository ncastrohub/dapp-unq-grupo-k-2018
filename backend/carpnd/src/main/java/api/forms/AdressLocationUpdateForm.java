package api.forms;

import me.geso.tinyvalidator.constraints.NotNull;

public class AdressLocationUpdateForm extends AdressLocationForm {

    @NotNull
    public Long id;

}
