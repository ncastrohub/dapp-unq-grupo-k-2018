package model;

import javafx.util.Pair;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class TestDaySchedule {

    @Test
    public void testDayScheduleStartsFullyFree(){

        DaySchedule day = new DaySchedule(LocalDate.now());

        Integer startOfDay = HoursOf
        LocalTime midnigth = LocalTime.MAX;
        
        assertThat(day.avaibleHours().size()).isEqualTo(1);
        assertThat(day.avaibleHours().get(0)).isEqualTo(new Pair<>(startOfDay, midnigth));
    }


    public void testWhenReserveATimeLapseOfDateThatTimeAreOccuped(){
        DaySchedule day = new DaySchedule(LocalDate.now());
        day.reserveFromTo(10, 15);

        LocalTime expectedStartHour = LocalTime.of(00,00);
        LocalTime expectedEndHour = LocalTime.of(10, 00);

        LocalTime expectedStartHour2 = LocalTime.of(15,00);
        LocalTime expectedEndHour2 = LocalTime.MAX;

        assertThat(day.avaibleHours().size()).isEqualTo(2);
        assertThat(day.avaibleHours().get(0)).isEqualTo(new Pair<>(expectedStartHour, expectedEndHour));

    }

}
