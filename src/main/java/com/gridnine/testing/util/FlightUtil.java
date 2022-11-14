package com.gridnine.testing.util;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;

public class FlightUtil {

    public static Duration evaluateTransferTime(Flight flight){
        var segmentDuration = Duration.ZERO;
        var departureDate = LocalDateTime.MAX;
        var arrivalDate = LocalDateTime.MIN;

        for(Segment segment : flight.getSegments()){
            departureDate = segment.getDepartureDate();

            if (!arrivalDate.isEqual(LocalDateTime.MIN)){
                var between = Duration.between(arrivalDate, departureDate);
                segmentDuration = segmentDuration.plus(between);
            }
            arrivalDate = segment.getArrivalDate();
        }

        return segmentDuration;
    }
}
