package model;

import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class DaySchedule {

    private final LinkedList<Pair<LocalTime, LocalTime>> avaibleHours;
    private final LocalDate scheduleDate;

    public DaySchedule(LocalDate aDate) {

        this.avaibleHours = new LinkedList<>();
        this.avaibleHours.add(new Pair<>(LocalTime.MIN, LocalTime.MAX));
        this.scheduleDate = aDate;

    }

    public List<Pair<LocalTime,LocalTime>> avaibleHours() {
        return this.avaibleHours;
    }
}

