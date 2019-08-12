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
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 17, 50);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 20, 0);
        int payment = babysitterCharge.calculate(startTime, endTime, "A");
        assertEquals(30, payment);
    }

    @Test
    public void whenFamilyTypeIsAAndStartTimeIsAfter2300ItPays20USDPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 23, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 1, 10);
        int payment = babysitterCharge.calculate(startTime, endTime, "A");
        assertEquals(20, payment);
    }

    @Test
    public void whenFamilyTypeIsAAndStartTimeIsBefore2300AndEndEndTimeIsAfterItPaysAccordinglyPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 21, 45);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 1, 10);
        int payment = babysitterCharge.calculate(startTime, endTime, "A");
        assertEquals(55, payment);
    }

    @Test
    public void whenFamilyTypeIsCAndEndTimeIsBefore2100ItPays21USDPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 19, 10);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 20, 10);
        int payment = babysitterCharge.calculate(startTime, endTime, "C");
        assertEquals(21, payment);
    }

    @Test
    public void whenFamilyTypeIsCAndStartTimeIsAfter2100ItPays15USDPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 23, 20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 1, 25);
        int payment = babysitterCharge.calculate(startTime, endTime, "C");
        assertEquals(30, payment);
    }

    @Test
    public void whenFamilyTypeIsCAndStartTimeIsBefore2100AndEndEndTimeIsAfterItPaysAccordinglyPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 20, 0);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 22, 0);
        int payment = babysitterCharge.calculate(startTime, endTime, "C");
        assertEquals(36, payment);
    }

    @Test
    public void whenFamilyTypeIsBAndEndTimeIsBefore2200ItPays12USDPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 19, 40);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 21, 50);
        int payment = babysitterCharge.calculate(startTime, endTime, "B");
        assertEquals(24, payment);
    }

    @Test
    public void whenFamilyTypeIsBAndStartTimeIsAfterMidnightItPays16USDPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 5, 0, 10);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 2, 0);
        int payment = babysitterCharge.calculate(startTime, endTime, "B");
        assertEquals(16, payment);
    }

    @Test
    public void whenFamilyTypeIsBAndTimesAreBetween2200AndMidnightItPays8USDPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 22, 10);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 23, 50);
        int payment = babysitterCharge.calculate(startTime, endTime, "B");
        assertEquals(8, payment);
    }

    @Test
    public void whenFamilyTypeIsBAndStartTimeIsBefore2200AndEndTimeIsAfterMidnightItPaysAccordinglyPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 20, 50);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 2, 10);
        int payment = babysitterCharge.calculate(startTime, endTime, "B");
        assertEquals(52, payment);
    }

    @Test
    public void whenFamilyTypeIsBAndStartTimeIsBefore2200AndEndTimeIsBeforeMidnightItPaysAccordinglyPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 21, 0);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 4, 23, 10);
        int payment = babysitterCharge.calculate(startTime, endTime, "B");
        assertEquals(20, payment);
    }

    @Test
    public void whenFamilyTypeIsBAndStartTimeIsAfter2200AndEndTimeIsAfterMidnightItPaysAccordinglyPerHour() {
        LocalDateTime startTime = LocalDateTime.of(2019, 8, 4, 23, 0);
        LocalDateTime endTime = LocalDateTime.of(2019, 8, 5, 2, 10);
        int payment = babysitterCharge.calculate(startTime, endTime, "B");
        assertEquals(40, payment);
    }

}