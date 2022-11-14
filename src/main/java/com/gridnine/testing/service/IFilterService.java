package com.gridnine.testing.service;

import com.gridnine.testing.model.FlightFilter;

import java.util.List;

public interface IFilterService<T>{
    List<T> filter(List<T> flights, FlightFilter filter);
}
