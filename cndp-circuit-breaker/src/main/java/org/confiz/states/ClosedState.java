package org.confiz.states;

import org.confiz.CircuitBreaker;

public class ClosedState implements CircuitBreakerState {

    private final CircuitBreaker breaker;
    private int failedExecution;

    public ClosedState(CircuitBreaker breaker) {
        this.breaker = breaker;
    }

    @Override
    public CircuitBreaker.State get() {
        return CircuitBreaker.State.CLOSED;
    }

    @Override
    public boolean allowExecution() {
        return true;
    }

    @Override
    public void recordFailure() {
        failedExecution++;

        int failureThreshold = breaker.getFailureThreshold();
        if (failedExecution >= failureThreshold) {
            breaker.open();
        }
    }
}
