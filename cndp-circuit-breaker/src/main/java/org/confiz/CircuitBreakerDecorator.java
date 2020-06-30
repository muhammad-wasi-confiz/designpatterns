package org.confiz;

import java.util.concurrent.Callable;

public interface CircuitBreakerDecorator {
    static Runnable decorateRunnable(CircuitBreaker circuitBreaker,
                                            Runnable runnable) {
        return () -> {
            circuitBreaker.allowExecution();
            try {
                runnable.run();
                circuitBreaker.recordSuccess();
            } catch (Exception exception) {
                circuitBreaker.recordFailure();
                throw exception;
            }
        };
    }

    static <T> Callable<T> decorateCallable(CircuitBreaker circuitBreaker,
                                            Callable<T> callable) {
        return () -> {
            circuitBreaker.allowExecution();
            try {
                T result = callable.call();
                circuitBreaker.recordSuccess();
                return result;
            } catch (Exception exception) {
                circuitBreaker.recordFailure();
                throw exception;
            }
        };
    }


}
