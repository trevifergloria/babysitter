package com.pillar.kata.babysitter;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BabysitterChargeTest {

    @Test
    public void whenTimesAndFamilyInputsAreValidItReturnsAValue() {
        BabysitterCharge babysitterCharge = new BabysitterCharge();
        LocalDateTime startTime = LocalDateTime.of(2019, 8,04, 18,20);
        LocalDateTime endTime = LocalDateTime.of(2019, 8,04, 18,20);
        int payment = babysitterCharge.calculate(startTime, endTime, "family-A");
        assertEquals(1, payment);
    }
}