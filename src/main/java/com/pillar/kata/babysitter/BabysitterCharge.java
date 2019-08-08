package com.pillar.kata.babysitter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class BabysitterCharge {

    private final String MINIMUM_START_TIME = "17:00";
    private final String MAXIMUM_END_TIME = "04:00";

    public int calculate(LocalDateTime startTimeInput, LocalDateTime endTimeInput, String familyType) {
        validate(startTimeInput, endTimeInput, familyType);
        return 1;
    }

    private void validate(LocalDateTime startDateAndTime, LocalDateTime endDateAndTime, String familyType) {
        LocalTime minimumStartTime = LocalTime.parse(MINIMUM_START_TIME);
        LocalTime maximumEndTime = LocalTime.parse(MAXIMUM_END_TIME);
        List<String> validFamilyTypes = Arrays.asList("A", "B", "C");

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

        if (!validFamilyTypes.contains(familyType))
            throw new InvalidFamilyTypeException();
    }
}