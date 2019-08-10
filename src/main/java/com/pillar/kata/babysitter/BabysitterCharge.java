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
            LocalDateTime rateChangeTime = startDateAndTimeInput.toLocalDate().atTime(23, 0);
            if (endDateAndTimeInput.isBefore(rateChangeTime)) {
                totalPayment = getPayableHours(startDateAndTimeInput, endDateAndTimeInput, 15);
            } else {
                if (startDateAndTimeInput.isAfter(rateChangeTime)) {
                    totalPayment = getPayableHours(startDateAndTimeInput, endDateAndTimeInput, 20);
                } else {
                    int partialPaymentBefore11pm = getPayableHours(startDateAndTimeInput, rateChangeTime, 15);
                    int partialPaymentAfter11pm = getPayableHours(rateChangeTime, endDateAndTimeInput, 20);
                    totalPayment = partialPaymentBefore11pm + partialPaymentAfter11pm;
                }
            }
        } else if (familyType.equalsIgnoreCase("B")) {

        } else if (familyType.equalsIgnoreCase("C")) {
            LocalDateTime rateChangeTime = startDateAndTimeInput.toLocalDate().atTime(21, 0);
            if (endDateAndTimeInput.isBefore(rateChangeTime)) {
                totalPayment = getPayableHours(startDateAndTimeInput, endDateAndTimeInput, 21);
            } else {
                if (startDateAndTimeInput.isAfter(rateChangeTime)) {
                    totalPayment = getPayableHours(startDateAndTimeInput, endDateAndTimeInput, 15);
                } else {
                    int partialPaymentBefore11pm = getPayableHours(startDateAndTimeInput, rateChangeTime, 21);
                    int partialPaymentAfter11pm = getPayableHours(rateChangeTime, endDateAndTimeInput, 15);
                    totalPayment = partialPaymentBefore11pm + partialPaymentAfter11pm;
                }
            }
        } else {
            throw new InvalidFamilyTypeException();
        }
        return totalPayment;
    }

    private int getPayableHours(LocalDateTime startDateAndTimeInput, LocalDateTime endDateAndTimeInput, int perHour) {
        return ((int) ChronoUnit.HOURS.between(startDateAndTimeInput, endDateAndTimeInput) + 1) * perHour;
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