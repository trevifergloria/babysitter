package com.pillar.kata.babysitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BabysitterChargeTest {

    BabysitterCharge babysitterCharge;

    @BeforeEach
    public void setUp() {
        babysitterCharge = new BabysitterCharge();
    }

    @Test
    public void whenTimesAreOutOfValidHoursRangeItThrowsException() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 17, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 4, 1);
        assertThrows(InvalidTimeException.class, () ->
                babysitterCharge.calculate(startTime, endTime, "A")
        );
    }

    @Test
    public void whenEndTimeIsEarlierThanStartTimeItThrowsException() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 22, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 19, 1);
        assertThrows(InvalidTimeException.class, () ->
                babysitterCharge.calculate(startTime, endTime, "A")
        );
    }

    @Test
    public void whenFamilyTypeIsInvalidItThrowsException() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 17, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 2, 1);
        assertThrows(InvalidFamilyTypeException.class, () ->
                babysitterCharge.calculate(startTime, endTime, "X")
        );
    }

    @Test
    public void whenFamilyTypeIsAAndEndTimeIsBefore11PMItPays15USDPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 18, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 20, 0);
        int payment = babysitterCharge.calculate(startTime, endTime, "A");
        assertEquals(30, payment);
    }

    @Test
    public void whenFamilyTypeIsAAndStartTimeIsAfter11PMItPays20USDPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 23, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 1, 10);
        int payment = babysitterCharge.calculate(startTime, endTime, "A");
        assertEquals(40, payment);
    }

    @Test
    public void whenFamilyTypeIsAAndStartTimeIsBefore11PMAndEndEndTimeIsAfterItPaysAccordinglyPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 22, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 23, 50);
        int payment = babysitterCharge.calculate(startTime, endTime, "A");
        assertEquals(35, payment);
    }
}