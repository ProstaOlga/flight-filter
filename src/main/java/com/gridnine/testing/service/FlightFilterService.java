package com.gridnine.testing.service;

import com.gridnine.testing.model.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterService implements IFilterService<Flight>{

    @Override
    public List<Flight> filter(List<Flight> flights, FlightFilter filter) {
        return flights.stream()
                .filter(flight -> doFilter(flight, filter))
                .collect(Collectors.toList());
    }

    private boolean doFilter(Flight flight, FlightFilter filter){
        var iterator = filter.getRules().iterator();
        boolean isMatch = true;

        while (isMatch && iterator.hasNext()){
            var rule = iterator.next();
            isMatch = rule.test(flight);
        }

        return isMatch;
    }
}
