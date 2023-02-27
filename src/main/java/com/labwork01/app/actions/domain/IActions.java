package com.labwork01.app.actions.domain;

public interface IActions<T> {
    T sum(T first, T second);
    T minus(T first, T second);
    T multiply(T first, T second);
    Boolean isExactLength(T first, T second);
}
