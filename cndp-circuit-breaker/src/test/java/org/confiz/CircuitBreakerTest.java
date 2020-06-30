package org.confiz;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.confiz.CircuitBreaker.State.HALF_OPEN;
import static org.confiz.CircuitBreaker.State.OPEN;

public class CircuitBreakerTest {

    @Test
    public void circuitOpenTest() {
        CircuitBreaker breaker = new CircuitBreaker(2, 2);
        Callable<Object> callable = CircuitBreakerDecorator.decorateCallable(breaker, () -> {
            throw new RuntimeException("Exception");
        });

        call(callable);

        call(callable);

        Assert.assertEquals(breaker.getState().get(), OPEN);
        Assert.assertFalse(breaker.allowExecution());

    }

    private void call(Callable<Object> callable) {
        try {
            callable.call();
        } catch (Exception e) {
        }
    }

    @Test
    public void circuitHalfOpen() throws InterruptedException {
        CircuitBreaker breaker = new CircuitBreaker(2, 2);
        Callable<Object> callable = CircuitBreakerDecorator.decorateCallable(breaker, () -> {
            throw new RuntimeException("Exception");
        });

        call(callable);

        call(callable);

        Assert.assertEquals(breaker.getState().get(), OPEN);
        Assert.assertFalse(breaker.allowExecution());

        Thread.sleep(2000);

        Assert.assertTrue(breaker.allowExecution());
        Assert.assertEquals(breaker.getState().get(), HALF_OPEN);
        Assert.assertTrue(breaker.allowExecution());
    }
}
