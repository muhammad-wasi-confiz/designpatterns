package org.confiz.states;

import org.confiz.CircuitBreaker;

import static org.confiz.CircuitBreaker.*;

public interface CircuitBreakerState {

    State get();

    boolean allowExecution();

    default void recordFailure() {}
    default void recordSuccess() {}


}
