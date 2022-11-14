package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.model.FlightFilter;
import com.gridnine.testing.rule.ArrivalBeforeDepartureFlightRule;
import com.gridnine.testing.rule.DepartureBeforeNowFlightRule;
import com.gridnine.testing.rule.TransferTimeFlightRule;
import com.gridnine.testing.service.FlightFilterService;
import com.gridnine.testing.service.IFilterService;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IFilterService<Flight> filterService = new FlightFilterService();
        List<Flight> testFlights = FlightBuilder.createFlights();

        FlightFilter filter1 = new FlightFilter();
        FlightFilter filter2 = new FlightFilter();
        FlightFilter filter3 = new FlightFilter();
        FlightFilter filter4 = new FlightFilter();

        filter1.applyRule(new DepartureBeforeNowFlightRule());
        filter2.applyRule(new ArrivalBeforeDepartureFlightRule());
        filter3.applyRule(new TransferTimeFlightRule(Duration.ofHours(2)));

        System.out.println(filterService.filter(testFlights, filter1));
        System.out.println(filterService.filter(testFlights, filter2));
        System.out.println(filterService.filter(testFlights, filter3));
    }
}
