package com.pillar.kata.babysitter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class BabysitterCharge {

    private final String MINIMUM_START_TIME = "17:00";
    private final String MAXIMUM_END_TIME = "04:00";

    public int calculate(LocalDateTime startTimeInput, LocalDateTime endTimeInput, String familyType) {
        validateTimes(startTimeInput, endTimeInput);
        if(familyType.equalsIgnoreCase("A")){

        }
        if (familyType.equalsIgnoreCase("B")){

        }
        if (familyType.equalsIgnoreCase("C")){

        }else {
            throw new InvalidFamilyTypeException();
        }
        return 1;
    }

    private void validateTimes(LocalDateTime startDateAndTime, LocalDateTime endDateAndTime) {
        LocalTime minimumStartTime = LocalTime.parse(MINIMUM_START_TIME);
        LocalTime maximumEndTime = LocalTime.parse(MAXIMUM_END_TIME);
        if (endDateAndTime.isBefore(startDateAndTime)) {
            throw new InvalidTimeException();
        } else {
            if (startDateAndTime.toLocalDate().equals(endDateAndTime.toLocalDate())) {
                if (startDateAndTime.toLocalTime().isBefore(minimumStartTime)) {
                    throw new InvalidTimeException();
                }
            } else {
                if (endDateAndTime.toLocalTime().isAfter(maximumEndTime)) {
                    throw new InvalidTimeException();
                }
            }
        }
    }
}