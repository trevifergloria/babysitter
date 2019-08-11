package com.pillar.kata.babysitter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class BabysitterCharge {

    private final LocalTime MINIMUM_START_TIME = LocalTime.of(17, 0);
    private final LocalTime MAXIMUM_END_TIME = LocalTime.of(4, 0);

    public int calculate(LocalDateTime startDateAndTimeInput, LocalDateTime endDateAndTimeInput, String familyType) {
        validateTimes(startDateAndTimeInput, endDateAndTimeInput);
        LocalDateTime rateChangeTime;
        int totalPayment = 0;
        if (familyType.equalsIgnoreCase("A")) {
            rateChangeTime = startDateAndTimeInput.toLocalDate().atTime(23, 0);
            totalPayment = getTotalPayment(startDateAndTimeInput, endDateAndTimeInput, rateChangeTime, 15, 20);
        } else if (familyType.equalsIgnoreCase("B")) {
            rateChangeTime = startDateAndTimeInput.toLocalDate().atTime(22, 0);

            LocalDateTime midnight = ((startDateAndTimeInput.toLocalDate().equals(endDateAndTimeInput.toLocalDate()) &&
                    !startDateAndTimeInput.toLocalTime().isBefore(MAXIMUM_END_TIME))) ?
                    endDateAndTimeInput.toLocalDate().atStartOfDay().plusDays(1) :
                    endDateAndTimeInput.toLocalDate().atStartOfDay();
            if (startDateAndTimeInput.toLocalTime().isAfter(MINIMUM_START_TIME) &&
                    endDateAndTimeInput.isBefore(rateChangeTime)) {
                totalPayment = getPayableHours(startDateAndTimeInput, endDateAndTimeInput, 12);
            }
            if (startDateAndTimeInput.isAfter(rateChangeTime) &&
                    endDateAndTimeInput.isBefore(midnight)) {
                totalPayment = getPayableHours(startDateAndTimeInput, endDateAndTimeInput, 8);
            }
            if (startDateAndTimeInput.isAfter(midnight)) {
                totalPayment = getPayableHours(startDateAndTimeInput, endDateAndTimeInput, 16);
            }
            if (endDateAndTimeInput.isBefore(midnight)) {
                totalPayment = getTotalPayment(startDateAndTimeInput, endDateAndTimeInput, rateChangeTime, 12, 8);
            }
            if (startDateAndTimeInput.isAfter(rateChangeTime)
                    && endDateAndTimeInput.isAfter(midnight)) {
                totalPayment = getTotalPayment(startDateAndTimeInput, endDateAndTimeInput, midnight, 8, 16);
            }
            if (startDateAndTimeInput.toLocalTime().isAfter(MINIMUM_START_TIME)
                    && startDateAndTimeInput.isBefore(rateChangeTime)
                    && endDateAndTimeInput.isAfter(midnight)) {
                totalPayment = getTotalPayment(startDateAndTimeInput, midnight.minusMinutes(1), rateChangeTime, 12, 8);
                totalPayment += getPayableHours(midnight, endDateAndTimeInput, 16);
            }
        } else if (familyType.equalsIgnoreCase("C")) {
            rateChangeTime = startDateAndTimeInput.toLocalDate().atTime(21, 0);
            totalPayment = getTotalPayment(startDateAndTimeInput, endDateAndTimeInput, rateChangeTime, 21, 15);
        } else {
            throw new InvalidFamilyTypeException();
        }
        return totalPayment;
    }

    private int getTotalPayment(LocalDateTime startDateAndTimeInput, LocalDateTime endDateAndTimeInput, LocalDateTime rateChangeTime, int initialRate, int afterRate) {
        if (endDateAndTimeInput.isBefore(rateChangeTime)) {
            return getPayableHours(startDateAndTimeInput, endDateAndTimeInput, initialRate);
        }
        if (startDateAndTimeInput.isAfter(rateChangeTime)) {
            return getPayableHours(startDateAndTimeInput, endDateAndTimeInput, afterRate);
        }

        int partialPaymentBeforeRateChanges = getPayableHours(startDateAndTimeInput, rateChangeTime, initialRate);
        int partialPaymentAfterRateChanges = getPayableHours(rateChangeTime, endDateAndTimeInput, afterRate);
        return partialPaymentBeforeRateChanges + partialPaymentAfterRateChanges;
    }

    private int getPayableHours(LocalDateTime startDateAndTimeInput, LocalDateTime endDateAndTimeInput, int perHour) {
        return ((int) ChronoUnit.HOURS.between(startDateAndTimeInput, endDateAndTimeInput) + 1) * perHour;
    }

    private void validateTimes(LocalDateTime startDateAndTime, LocalDateTime endDateAndTime) {
        if (endDateAndTime.isBefore(startDateAndTime)) {
            throw new InvalidTimeException();
        } else {
            if (startDateAndTime.toLocalDate().equals(endDateAndTime.toLocalDate())) {
                if (startDateAndTime.toLocalTime().isAfter(MAXIMUM_END_TIME) && startDateAndTime.toLocalTime().isBefore(MINIMUM_START_TIME)) {
                    throw new InvalidTimeException();
                }
            } else {
                if (endDateAndTime.toLocalTime().isAfter(MAXIMUM_END_TIME)) {
                    throw new InvalidTimeException();
                }
            }
        }
    }
}