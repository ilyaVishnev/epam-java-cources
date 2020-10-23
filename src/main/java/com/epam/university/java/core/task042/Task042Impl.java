package com.epam.university.java.core.task042;

import java.util.List;

public class Task042Impl implements Task042 {

    @Override
    public BookingResponse checkAvailability(List<String> schedule, String currentTime) {
        if (schedule == null || currentTime == null) {
            throw new IllegalArgumentException();
        }
        int min = Integer.parseInt(currentTime.split(":")[0]) * 60
                + Integer.parseInt(currentTime.split(":")[1]);
        int prev = Integer.MAX_VALUE;
        String availableTime = "";
        int availableTimeMin = Integer.MAX_VALUE;
        int endMin = 0;
        String end = "";
        for (String timeSpan : schedule) {
            String start = timeSpan.split("-")[0];
            end = timeSpan.split("-")[1];
            int startMin = Integer.parseInt(start.split(":")[0]) * 60
                    + Integer.parseInt(start.split(":")[1]);
            endMin = Integer.parseInt(end.split(":")[0]) * 60
                    + Integer.parseInt(end.split(":")[1]);
            if (availableTimeMin < startMin) {
                TimeProposalResponse response = new TimeProposalResponse();
                response.setProposedTime(availableTime);
                return response;
            } else {
                availableTimeMin = Integer.MAX_VALUE;
            }
            if (min >= startMin
                    && min <= endMin) {
                if (endMin >= 1080) {
                    return new BusyResponse();
                } else {
                    availableTime = end;
                    availableTimeMin = endMin;
                }
            }
            if (min > prev
                    && min < startMin) {
                return new AvailableResponse();
            }
            prev = endMin;
        }
        if (min > 540
                && min < 1080) {
            if (endMin < 1080
                    && !schedule.isEmpty()) {
                TimeProposalResponse response = new TimeProposalResponse();
                response.setProposedTime(end);
                return response;
            }
            return new AvailableResponse();
        }
        if (min < 540) {
            TimeProposalResponse response = new TimeProposalResponse();
            response.setProposedTime("09:00");
            return response;
        }
        return new BusyResponse();
    }
}
