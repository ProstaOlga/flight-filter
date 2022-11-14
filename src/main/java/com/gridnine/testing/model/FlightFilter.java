package com.gridnine.testing.model;

import com.gridnine.testing.rule.FlightRule;

import java.util.LinkedList;
import java.util.List;

public class FlightFilter extends AbstractFilter<Flight, FlightRule> {
    private final List<FlightRule> flightRules;

    public FlightFilter() {
        this.flightRules = new LinkedList<>();
    }

    public FlightFilter applyRule(FlightRule flightRule){
        flightRules.add(flightRule);
        return this;
    }

    @Override
    public List<FlightRule> getRules() {
        return this.flightRules;
    }
}
