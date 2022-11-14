package com.gridnine.testing.rule;

import com.gridnine.testing.data.TestFlightBuilder;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ArrivalBeforeDepartureFlightRuleTest {

    private LocalDateTime testDate = LocalDateTime.now();

    private static ArrivalBeforeDepartureFlightRule arrivalBeforeDepartureFlightRule;

    @BeforeAll
    static void init(){
        arrivalBeforeDepartureFlightRule = new ArrivalBeforeDepartureFlightRule();
    }

    @Test
    void testFlightNotMatchOneSegment() {
        Flight testFlight = TestFlightBuilder.createFlight(testDate, testDate.minusHours(1));

        var result = arrivalBeforeDepartureFlightRule.test(testFlight);

        assertFalse(result);
    }

    @Test
    void testFlightMatchOneSegment() {
        Flight testFlight = TestFlightBuilder.createFlight(testDate, testDate.plusHours(3));

        var result = arrivalBeforeDepartureFlightRule.test(testFlight);

        assertTrue(result);
    }

    @Test
    void testFlightNotMatchSeveralSegments() {
        Flight testFlight = TestFlightBuilder.createFlight(testDate, testDate.plusHours(2), testDate.plusHours(2), testDate);

        var result = arrivalBeforeDepartureFlightRule.test(testFlight);

        assertFalse(result);
    }
}