package api.forms;

import me.geso.tinyvalidator.constraints.NotNull;

public class VehicleUpdateForm extends VehicleForm {

    @NotNull
    public String id;
}
