package com.pillar.kata.babysitter;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BabysitterChargeTest {

    @Test
    public void whenTimesAndFamilyInputsAreValidItReturnsAValue() {
        BabysitterCharge babysitterCharge = new BabysitterCharge();
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 18, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 22, 40);
        int payment = babysitterCharge.calculate(startTime, endTime, "A");
        assertEquals(1, payment);
    }

    @Test
    public void whenTimesAreOutOfValidHoursRangeItThrowsException() {
        BabysitterCharge babysitterCharge = new BabysitterCharge();
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 17, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 4, 1);
        assertThrows(InvalidTimeException.class, () ->
                babysitterCharge.calculate(startTime, endTime, "A")
        );
    }

    @Test
    public void whenEndTimeIsEarlierThanStartTimeItThrowsException() {
        BabysitterCharge babysitterCharge = new BabysitterCharge();
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 22, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 19, 1);
        assertThrows(InvalidTimeException.class, () ->
                babysitterCharge.calculate(startTime, endTime, "A")
        );
    }

    @Test
    public void whenFamilyTypeIsInvalidItThrowsException() {
        BabysitterCharge babysitterCharge = new BabysitterCharge();
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 17, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 2, 1);
        assertThrows(InvalidFamilyTypeException.class, () ->
                babysitterCharge.calculate(startTime, endTime, "X")
        );
    }
}