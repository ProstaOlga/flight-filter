package com.gridnine.testing.service;

import com.gridnine.testing.BaseUnitClass;
import com.gridnine.testing.data.TestFlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightFilter;
import com.gridnine.testing.rule.ArrivalBeforeDepartureFlightRule;
import com.gridnine.testing.rule.DepartureBeforeNowFlightRule;
import com.gridnine.testing.rule.TransferTimeFlightRule;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class FlightFilterServiceTest extends BaseUnitClass {

    private List<Flight> flights = TestFlightBuilder.createFlights();

    @Mock
    private ArrivalBeforeDepartureFlightRule arrivalBeforeDepartureFlightRule;

    @Mock
    private DepartureBeforeNowFlightRule departureBeforeNowFlightRule;

    @Mock
    private TransferTimeFlightRule transferTimeFlightRule;


    @Test
    void filterWithEmptyRules() {
        FlightFilter flightFilterEmpty = new FlightFilter();

        FlightFilterService filterService = new FlightFilterService();
        var resultFlights = filterService.filter(flights, flightFilterEmpty);

        assertNotNull(resultFlights);
        assertEquals(flights.size(), resultFlights.size());
        assertEquals(flights, resultFlights);
    }

    @Test
    void filterAllFlightsNoMatch1() {
        FlightFilter flightFilter = new FlightFilter();
        flightFilter.applyRule(arrivalBeforeDepartureFlightRule).applyRule(departureBeforeNowFlightRule).applyRule(transferTimeFlightRule);
        FlightFilterService filterService = new FlightFilterService();

        Mockito.when(arrivalBeforeDepartureFlightRule.test(ArgumentMatchers.any(Flight.class)))
                .thenReturn(false);

        var resultFlights = filterService.filter(flights, flightFilter);

        Mockito.verify(arrivalBeforeDepartureFlightRule, Mockito.times(6)).test(ArgumentMatchers.any(Flight.class));
        Mockito.verify(departureBeforeNowFlightRule, Mockito.times(0)).test(ArgumentMatchers.any(Flight.class));
        Mockito.verify(transferTimeFlightRule, Mockito.times(0)).test(ArgumentMatchers.any(Flight.class));

        assertNotEquals(flights, resultFlights);
        assertEquals(resultFlights.size(), 0);
    }

    @Test
    void filterAllFlightsNoMatch2() {
        FlightFilter flightFilter = new FlightFilter();
        flightFilter.applyRule(arrivalBeforeDepartureFlightRule).applyRule(departureBeforeNowFlightRule).applyRule(transferTimeFlightRule);
        FlightFilterService filterService = new FlightFilterService();

        Mockito.when(arrivalBeforeDepartureFlightRule.test(ArgumentMatchers.any(Flight.class)))
                .thenReturn(true);
        Mockito.when(departureBeforeNowFlightRule.test(ArgumentMatchers.any(Flight.class)))
                .thenReturn(true);
        Mockito.when(transferTimeFlightRule.test(ArgumentMatchers.any(Flight.class)))
                .thenReturn(false);

        var resultFlights = filterService.filter(flights, flightFilter);

        Mockito.verify(arrivalBeforeDepartureFlightRule, Mockito.times(6)).test(ArgumentMatchers.any(Flight.class));
        Mockito.verify(departureBeforeNowFlightRule, Mockito.times(6)).test(ArgumentMatchers.any(Flight.class));
        Mockito.verify(transferTimeFlightRule, Mockito.times(6)).test(ArgumentMatchers.any(Flight.class));

        assertNotEquals(flights, resultFlights);
        assertEquals(resultFlights.size(), 0);
    }

    @Test
    void filterAllFlightsMatch() {
        FlightFilter flightFilter = new FlightFilter();
        flightFilter.applyRule(arrivalBeforeDepartureFlightRule).applyRule(departureBeforeNowFlightRule);
        FlightFilterService filterService = new FlightFilterService();

        Mockito.when(arrivalBeforeDepartureFlightRule.test(ArgumentMatchers.any(Flight.class)))
                .thenReturn(true);
        Mockito.when(departureBeforeNowFlightRule.test(ArgumentMatchers.any(Flight.class)))
                .thenReturn(true);

        var resultFlights = filterService.filter(flights, flightFilter);

        Mockito.verify(arrivalBeforeDepartureFlightRule, Mockito.times(6)).test(ArgumentMatchers.any(Flight.class));
        Mockito.verify(departureBeforeNowFlightRule, Mockito.times(6)).test(ArgumentMatchers.any(Flight.class));

        assertNotNull(resultFlights);
        assertEquals(flights.size(), resultFlights.size());
        assertEquals(flights, resultFlights);
    }

    @Test
    void filterNotAllFlightsMatch() {
        FlightFilter flightFilter = new FlightFilter();
        flightFilter.applyRule(arrivalBeforeDepartureFlightRule).applyRule(departureBeforeNowFlightRule);
        FlightFilterService filterService = new FlightFilterService();

        Mockito.when(arrivalBeforeDepartureFlightRule.test(ArgumentMatchers.any(Flight.class)))
                .thenReturn(true);
        Mockito.when(arrivalBeforeDepartureFlightRule.test(ArgumentMatchers.eq(flights.get(0))))
                .thenReturn(false);
        Mockito.when(departureBeforeNowFlightRule.test(ArgumentMatchers.any(Flight.class)))
                .thenReturn(true);

        var resultFlights = filterService.filter(flights, flightFilter);

        Mockito.verify(arrivalBeforeDepartureFlightRule, Mockito.times(6)).test(ArgumentMatchers.any(Flight.class));
        Mockito.verify(departureBeforeNowFlightRule, Mockito.times(5)).test(ArgumentMatchers.any(Flight.class));

        assertNotNull(resultFlights);
        assertNotEquals(flights.size(), resultFlights.size());
        assertEquals(resultFlights.size(), 5);
        assertFalse(resultFlights.contains(flights.get(0)));
    }


}