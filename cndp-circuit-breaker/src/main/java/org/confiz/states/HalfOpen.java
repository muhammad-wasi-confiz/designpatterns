package org.confiz.states;

import org.confiz.CircuitBreaker;

public class HalfOpen implements CircuitBreakerState {
    private final CircuitBreaker breaker;
    private int successExecution;
    private int failedExecution;

    public HalfOpen(CircuitBreaker breaker) {
        this.breaker = breaker;
    }

    @Override
    public CircuitBreaker.State get() {
        return CircuitBreaker.State.HALF_OPEN;
    }

    @Override
    public boolean allowExecution() {
        return true;
    }

    @Override
    public void recordFailure() {
        failedExecution++;
        if (failedExecution >= breaker.getFailureThreshold()) {
            breaker.open();
        }
    }

    @Override
    public void recordSuccess() {
        successExecution++;
        if (successExecution >= breaker.getSuccessThreshold()) {
            breaker.close();
        }
    }
}
