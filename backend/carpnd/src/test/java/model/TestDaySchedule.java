package model;

import javafx.util.Pair;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestDaySchedule {

    @Test
    public void testDayScheduleStartsFullyFree(){

        DaySchedule day = new DaySchedule(LocalDate.now());

        LocalTime startOfDay = LocalTime.MIN;
        LocalTime midnigth = LocalTime.MAX;
        
        assertThat(day.avaibleHours().size()).isEqualTo(1);
        assertThat(day.avaibleHours().get(0)).isEqualTo(new Pair<>(startOfDay, midnigth));
    }



}
