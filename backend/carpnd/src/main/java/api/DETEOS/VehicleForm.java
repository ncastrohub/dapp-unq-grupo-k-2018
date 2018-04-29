package api.DETEOS;

import lombok.Data;
import me.geso.tinyvalidator.constraints.HttpUrl;
import me.geso.tinyvalidator.constraints.Min;
import me.geso.tinyvalidator.constraints.NotNull;
import me.geso.tinyvalidator.constraints.Size;
import model.VehicleType;

@Data
public class VehicleForm {

    public VehicleForm() {}

    @NotNull
    @Min(1)
    public int capacity;

    @NotNull
    public VehicleType type;

    @Size(min=10, max=30)
    public String description;

    @HttpUrl
    public String photo; // Agregar el link a las fotos!

}

