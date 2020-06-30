package org.confiz;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class RetryImpl<T> implements Retry {

    private final int maxAttempts;
    private final long maxInterval;
    private final Predicate<T> resultPredicate;
    private final Predicate<Throwable> exceptionPredicate;

    public RetryImpl(RetryConfig cfg) {
        this.maxAttempts = cfg.getMaxAttempts();
        this.maxInterval = cfg.getMaxInterval();
        this.resultPredicate = cfg.getRetryOnResultPredicate();
        this.exceptionPredicate = cfg.getRetryOnExceptionPredicate();
    }

    @Override
    public Context context() {
        return new ContextImpl();
    }

    public final class ContextImpl implements Retry.Context<T> {

        private final AtomicInteger numOfAttempts = new AtomicInteger(0);

        private ContextImpl() {
        }

        @Override
        public boolean onResult(T result) {
            if (null != resultPredicate && resultPredicate.test(result)) {
                int currentNumOfAttempts = numOfAttempts.incrementAndGet();
                if (currentNumOfAttempts >= maxAttempts) {
                    return false;
                } else {
                    waitIntervalAfterFailure();
                    return true;
                }
            }
            return false;
        }

        @Override
        public void onRuntimeError(RuntimeException runtimeException) {
            if (exceptionPredicate.test(runtimeException)) {
                throwOrSleepAfterRuntimeException(runtimeException);
            } else {
                throw runtimeException;
            }
        }

        private void throwOrSleepAfterRuntimeException(RuntimeException runtimeException) {
            int currentNumOfAttempts = numOfAttempts.incrementAndGet();
            if (currentNumOfAttempts >= maxAttempts) {
                throw runtimeException;
            } else {
                waitIntervalAfterFailure();
            }
        }

        private void waitIntervalAfterFailure() {
            try {
                Thread.sleep(maxInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
