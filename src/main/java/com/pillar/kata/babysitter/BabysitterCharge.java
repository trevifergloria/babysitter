package com.pillar.kata.babysitter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class BabysitterCharge {

    private final String MINIMUM_START_TIME = "17:00";
    private final String MAXIMUM_END_TIME = "04:00";

    public int calculate(LocalDateTime startDateAndTimeInput, LocalDateTime endDateAndTimeInput, String familyType) {
        validateTimes(startDateAndTimeInput, endDateAndTimeInput);
        int totalPayment = 0;
        if (familyType.equalsIgnoreCase("A")) {
            if (endDateAndTimeInput.toLocalTime().isBefore(LocalTime.of(23, 0))) {
                int diff = (int) ChronoUnit.HOURS.between(startDateAndTimeInput, endDateAndTimeInput);
                totalPayment = (diff + 1) * 15;
            }
            if (startDateAndTimeInput.toLocalTime().isAfter(LocalTime.of(23, 0))) {
                int diff = (int) ChronoUnit.HOURS.between(startDateAndTimeInput, endDateAndTimeInput);
                totalPayment = (diff + 1) * 20;
            }
        } else if (familyType.equalsIgnoreCase("B")) {

        } else if (familyType.equalsIgnoreCase("C")) {

        } else {
            throw new InvalidFamilyTypeException();
        }
        return totalPayment;
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