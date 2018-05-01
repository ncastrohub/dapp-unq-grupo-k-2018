package api.DETEOS;

import me.geso.tinyvalidator.constraints.NotNull;

import java.io.Serializable;

public class VehicleUpdateForm extends VehicleForm {

    @NotNull
    public Serializable id;
}
