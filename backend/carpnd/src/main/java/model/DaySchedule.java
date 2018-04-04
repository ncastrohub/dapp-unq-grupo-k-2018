package model;

import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void reserveFromTo(Integer startHour, Integer endHour) {
//        Que existe una hora de inicio de un segmento menor o igual al valor de inicio
//        Que existe una hora de inicio de un segmento mayor o igual al valor de final

        LocalTime startHourToTime = LocalTime.of(startHour, 00);
        LocalTime endHourToTime = LocalTime.of(endHour, 00);
        List<Pair<LocalTime, LocalTime>> containedTimeLapses = this.avaibleHours.stream().filter(t -> {
            Boolean supportMinHour = t.getKey().isBefore(startHourToTime) || t.getKey().equals(startHourToTime);
            Boolean supportMax = t.getValue().isAfter(endHourToTime) || t.getValue().equals(startHourToTime);
            return supportMinHour && supportMax;
        }).collect(Collectors.toList());

    }
}

