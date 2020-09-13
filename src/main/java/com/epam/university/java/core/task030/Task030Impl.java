package com.epam.university.java.core.task030;

import java.time.LocalDate;
import java.time.Duration;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Task030Impl implements Task030 {
    @Override
    public int daysBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        return Math.abs((int) Duration.between(dateEnd.atStartOfDay(), dateStart
                .atStartOfDay()).toDays());
    }

    @Override
    public LocalDate adjustDays(LocalDate dateStart, int daysToAdd) {
        return dateStart.plusDays(daysToAdd);
    }

    @Override
    public long distanceBetween(Instant instantStart, Instant instantEnd) {
        return instantEnd.getEpochSecond() - instantStart.getEpochSecond();
    }

    @Override
    public DayOfWeek getDayOfWeek(LocalDate localDate) {
        return localDate.getDayOfWeek();
    }

    @Override
    public LocalDate getNextWeekend(LocalDate localDate) {
        LocalDate res = null;
        switch (localDate.getDayOfWeek()) {
            case MONDAY:
                res = localDate.plusDays(5);
                break;
            case TUESDAY:
                res = localDate.plusDays(4);
                break;
            case WEDNESDAY:
                res = localDate.plusDays(3);
                break;
            case THURSDAY:
                res = localDate.plusDays(2);
                break;
            case FRIDAY:
                res = localDate.plusDays(1);
                break;
            case SATURDAY:
                res = localDate.plusDays(7);
                break;
            case SUNDAY:
                res = localDate.plusDays(6);
                break;
            default:
                break;
        }
        return res;
    }

    @Override
    public LocalTime getLocalTime(String timeString) {
        String[] time = timeString.split(":");
        String format = "";
        List<String> nextFormat = Arrays.asList("hh", ":mm", ":ss");
        for (int i = 0; i < time.length; i++) {
            format += nextFormat.get(i);
            if (i == time.length - 1 && (time[i].substring(time[i].length() - 2).equals("PM")
                    || time[i].substring(time[i].length() - 2).equals("AM"))) {
                format += " a";
                String am = " " + timeString.substring(timeString.length() - 2);
                timeString = timeString.substring(0, timeString.length() - 2) + am;
            }
        }
        LocalTime res = LocalTime.parse(timeString, DateTimeFormatter.ofPattern(format));
        return res;
    }

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, 8, 13);

    }
}
