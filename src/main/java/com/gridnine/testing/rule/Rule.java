package com.gridnine.testing.rule;

import com.gridnine.testing.model.Flight;

import java.util.function.Predicate;

public interface Rule<T> extends Predicate<T> {
}
