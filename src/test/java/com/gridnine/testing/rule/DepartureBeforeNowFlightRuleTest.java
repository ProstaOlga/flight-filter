package com.gridnine.testing.rule;

import com.gridnine.testing.data.TestFlightBuilder;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DepartureBeforeNowFlightRuleTest {

    private LocalDateTime testDate = LocalDateTime.now();

    private static DepartureBeforeNowFlightRule departureBeforeNowFlightRule;

    @BeforeAll
    static void init(){
        departureBeforeNowFlightRule = new DepartureBeforeNowFlightRule();
    }

    @Test
    void testFlightMatch() {
        Flight flight = TestFlightBuilder.createFlight(testDate, testDate.plusHours(2));

        boolean result = departureBeforeNowFlightRule.test(flight);

        assertTrue(result);
    }

    @Test
    void testFlightNotMatch() {
        Flight flight = TestFlightBuilder.createFlight(testDate.minusDays(6), testDate.minusHours(2));

        boolean result = departureBeforeNowFlightRule.test(flight);

        assertFalse(result);
    }
}