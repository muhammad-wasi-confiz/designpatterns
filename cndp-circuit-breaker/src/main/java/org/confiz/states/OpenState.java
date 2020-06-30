package org.confiz.states;

import org.confiz.CircuitBreaker;

public class OpenState implements CircuitBreakerState {
    private final CircuitBreaker breaker;
    private final long startTime = System.currentTimeMillis();
    private final long delayInMillis;

    public OpenState(CircuitBreaker breaker, long delayInMillis) {
        this.breaker = breaker;
        this.delayInMillis = delayInMillis;
    }

    @Override
    public CircuitBreaker.State get() {
        return CircuitBreaker.State.OPEN;
    }

    @Override
    public boolean allowExecution() {
        if (System.currentTimeMillis() - startTime >= delayInMillis) {
            breaker.halfOpen();
            return true;
        }

        return false;
    }
}
