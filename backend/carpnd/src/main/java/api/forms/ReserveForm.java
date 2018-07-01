package api.forms;

import lombok.Data;
import me.geso.tinyvalidator.constraints.Max;
import me.geso.tinyvalidator.constraints.Min;
import me.geso.tinyvalidator.constraints.NotNull;
import me.geso.tinyvalidator.constraints.Size;
import org.joda.time.LocalDate;

import java.util.List;

@Data
public class ReserveForm {

    @NotNull
    @Size(min = 1, max = 5)
    public List<LocalDate> reservationDays;

    @NotNull
    public Long returnLocation;

    @NotNull
    public Long publication;

}
