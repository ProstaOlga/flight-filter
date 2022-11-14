package com.gridnine.testing.rule;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.util.FlightUtil;

import java.time.Duration;

/**
 * Реализация фильтра из задания : "2. Есть сегменты с датой прилета раньше даты вылета".
 */
public class TransferTimeFlightRule implements FlightRule{
    private final Duration limit;

    public TransferTimeFlightRule(Duration limit) {
        this.limit = limit;
    }

    @Override
    public boolean test(Flight flight) {
        var flightTransferDuration = FlightUtil.evaluateTransferTime(flight);

        return !limit.minus(flightTransferDuration).isNegative();
    }
}
