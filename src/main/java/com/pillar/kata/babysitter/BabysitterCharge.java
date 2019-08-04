package com.pillar.kata.babysitter;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class BabysitterCharge {

    private final String MINIMUM_START_TIME = "17:00";
    private final String MAXIMUM_END_TIME = "04:00";

    public int calculate(LocalDateTime startTimeInput, LocalDateTime  endTimeInput, String familyType) {
        LocalTime startTime = startTimeInput.toLocalTime();
        LocalTime endTime = endTimeInput.toLocalTime();
        validate(startTime, endTime);
        return 1;
    }

    private void validate(LocalTime startTimeInput, LocalTime endTimeInput) {
        LocalTime minimumStartTime = LocalTime.parse(MINIMUM_START_TIME);
        LocalTime maximumEndTime = LocalTime.parse(MAXIMUM_END_TIME);

        if(startTimeInput.isBefore(minimumStartTime) || endTimeInput.isAfter(maximumEndTime))
            throw new InvalidTimeException();
    }
}