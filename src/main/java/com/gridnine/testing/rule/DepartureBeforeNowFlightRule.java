package com.gridnine.testing.rule;

import com.gridnine.testing.exception.RuleCause;
import com.gridnine.testing.exception.RuleException;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;

/**
 *  Реализация фильтра из задания : "1. Вылет до текущего момента времени".
 *  Предполагается, что сегменты в полете отсортированы по порядку.
 */
public class DepartureBeforeNowFlightRule implements FlightRule {

    @Override
    public boolean test(Flight flight) {
        var firstSegment = flight.getSegments().stream()
                .findFirst()
                .orElseThrow(() -> new RuleException(RuleCause.EMPTY_FLIGHT_SEGMENTS));

        return !firstSegment.getDepartureDate().isBefore(LocalDateTime.now());
    }
}
