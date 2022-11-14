package com.gridnine.testing.rule;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Реализация фильтра из задания : "2. Присутствуют сeгменты с датой прилета раньше даты вылета".
 */
public class ArrivalBeforeDepartureFlightRule implements FlightRule {

    @Override
    public boolean test(Flight flight) {
        boolean isMatch = flight.getSegments().stream()
                .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));

        var iterator = flight.getSegments().iterator();

            var departureDate = LocalDateTime.MIN;
            var arrivalDate = LocalDateTime.MAX;

            while (iterator.hasNext() && isMatch){
                var segment = iterator.next();
                departureDate = segment.getDepartureDate();

                isMatch = departureDate.isBefore(arrivalDate);

                arrivalDate = segment.getArrivalDate();
            }

        return isMatch;
    }



}
