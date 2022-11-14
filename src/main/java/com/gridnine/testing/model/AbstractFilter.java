package com.gridnine.testing.model;

import com.gridnine.testing.rule.Rule;

import java.util.List;

/**
 *
 * @param <R> - модель, которую необходимо отфильтровать
 * @param <T> - правило фильтрации для модели
 */
public abstract class AbstractFilter<R, T extends Rule<R>> {

    public abstract AbstractFilter<R, T> applyRule(T rule);

    public abstract List<T> getRules();
}
