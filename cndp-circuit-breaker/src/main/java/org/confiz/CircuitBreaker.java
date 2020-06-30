package org.confiz;

import org.confiz.states.CircuitBreakerState;
import org.confiz.states.ClosedState;
import org.confiz.states.HalfOpen;
import org.confiz.states.OpenState;

import java.util.concurrent.atomic.AtomicReference;

public class CircuitBreaker {

    private final AtomicReference<CircuitBreakerState> state = new AtomicReference<>();
    private int failureThreshold;
    private int successThreshold;

    public CircuitBreaker(int failureThreshold, int successThreshold) {
        this.failureThreshold = failureThreshold;
        this.successThreshold = successThreshold;
        state.set(new ClosedState(this));
    }

    public boolean allowExecution() {
        return state.get().allowExecution();
    }

    public void close() {
        transitionTo(State.CLOSED);
    }

    public void open() {
        transitionTo(State.OPEN);
    }

    public void halfOpen() {
        transitionTo(State.HALF_OPEN);
    }

    private void transitionTo(State newState) {
        synchronized (this) {
            switch (newState) {
                case OPEN:
                    state.set(new OpenState(this, 2000));
                    break;
                case HALF_OPEN:
                    state.set(new HalfOpen(this));
                    break;
                case CLOSED:
                    state.set(new ClosedState(this));
                    break;
                default:
                    throw new IllegalArgumentException("Illegal transition from " + getState() +
                            " to " + newState);
            }
        }
    }

    void recordFailure() {
        state.get().recordFailure();
    }

    public void recordSuccess() {
        state.get().recordSuccess();
    }

    public int getFailureThreshold() {
        return failureThreshold;
    }

    public int getSuccessThreshold() {
        return successThreshold;
    }

    public CircuitBreakerState getState() {
        return state.get();
    }

    public enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }
}
