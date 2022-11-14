package com.gridnine.testing.rule;

import com.gridnine.testing.data.TestFlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.util.FlightUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransferTimeFlightRuleTest {

    LocalDateTime testDate = LocalDateTime.now();


    @Test
    void testFlightNotMatch() {
        TransferTimeFlightRule transferTimeFlightRule = new TransferTimeFlightRule(Duration.ofHours(2));

        Flight testFlight = TestFlightBuilder.createFlight(testDate, testDate.plusHours(2), testDate.plusHours(5), testDate.plusHours(7));

        var result = transferTimeFlightRule.test(testFlight);

        assertFalse(result);
    }

    @Test
    void testFlightMatch() {
        TransferTimeFlightRule transferTimeFlightRule = new TransferTimeFlightRule(Duration.ofHours(2));

        Flight testFlight = TestFlightBuilder.createFlight(testDate, testDate.plusHours(2), testDate.plusHours(3), testDate.plusHours(7));

        var result = transferTimeFlightRule.test(testFlight);

        assertTrue(result);
    }

    @Test
    void testDurationIsZeroTrue() {
        TransferTimeFlightRule transferTimeFlightRule = new TransferTimeFlightRule(Duration.ZERO);

        Flight testFlight = TestFlightBuilder.createFlight(testDate, testDate.plusHours(2));

        var result = transferTimeFlightRule.test(testFlight);

        assertTrue(result);
    }

    @Test
    void testDurationIsZeroFalse() {
        TransferTimeFlightRule transferTimeFlightRule = new TransferTimeFlightRule(Duration.ZERO);

        Flight testFlight = TestFlightBuilder.createFlight(testDate, testDate.plusHours(2), testDate.plusHours(3), testDate.plusHours(7));

        var result = transferTimeFlightRule.test(testFlight);

        assertFalse(result);
    }

    @Test
    void testFlightIsEmpty() {
        TransferTimeFlightRule transferTimeFlightRule = new TransferTimeFlightRule(Duration.ofHours(2));

        Flight testFlight = new Flight(Collections.emptyList());

        var result = transferTimeFlightRule.test(testFlight);

        assertTrue(result);
    }
}