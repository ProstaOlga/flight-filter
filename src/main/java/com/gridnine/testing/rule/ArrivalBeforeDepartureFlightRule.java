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
        boolean isMatch = true;

        var iterator = flight.getSegments().iterator();

            var departureDate = LocalDateTime.MIN;
            var arrivalDate = LocalDateTime.MIN;

            while (iterator.hasNext() && isMatch){
                var segment = iterator.next();
                departureDate = segment.getDepartureDate();

                isMatch = checkNextSegmentDates(departureDate, arrivalDate);

                arrivalDate = segment.getArrivalDate();
                isMatch = isMatch ? checkSegmentDates(departureDate, arrivalDate) : isMatch;
            }

        return isMatch;
    }

    private boolean checkNextSegmentDates(LocalDateTime departureDate, LocalDateTime arrivalDate){
        return !departureDate.isBefore(arrivalDate);
    }

    private boolean checkSegmentDates(LocalDateTime departureDate, LocalDateTime arrivalDate){
        return departureDate.isBefore(arrivalDate);
    }
}
