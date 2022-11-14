package com.gridnine.testing.exception;

public enum RuleCause {
    EMPTY_FLIGHT_SEGMENTS("List<Segment> is empty");

    private final String text;

    RuleCause(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
