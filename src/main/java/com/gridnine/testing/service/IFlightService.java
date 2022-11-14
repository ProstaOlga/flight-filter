package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.time.Duration;

public interface IFlightService {

    Duration evaluateTransferTime(Flight flight);
}
