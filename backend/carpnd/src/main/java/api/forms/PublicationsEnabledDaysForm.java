package api.forms;

import lombok.Data;
import me.geso.tinyvalidator.constraints.NotNull;
import me.geso.tinyvalidator.constraints.Size;
import model.PublicationsEnabledDays;
import org.joda.time.LocalDate;

import java.util.List;

@Data
public class PublicationsEnabledDaysForm {

    @NotNull
    @Size(max = 7)
    public List<Integer> disabledDays;

    @NotNull
    public List<LocalDate> reservedDays;

    public PublicationsEnabledDays getModelInstance() {
        return new PublicationsEnabledDays(this.reservedDays, this.disabledDays);
    }

}
