package org.confiz;

import java.util.function.Predicate;

public class RetryConfig {

    private final int maxAttempts;
    private final long maxInterval;
    private final Predicate retryOnResultPredicate;
    private final Predicate<Throwable> retryOnExceptionPredicate;

    public RetryConfig(int maxAttempts, long maxInterval, Predicate retryOnResultPredicate, Predicate<Throwable> retryOnExceptionPredicate) {
        this.maxAttempts = maxAttempts;
        this.maxInterval = maxInterval;
        this.retryOnResultPredicate = retryOnResultPredicate;
        this.retryOnExceptionPredicate = retryOnExceptionPredicate;
    }

    public static <T> Builder<T> custom() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private int maxAttempts = 3;
        private long maxInterval = 5000L;
        private Predicate<Throwable> retryOnExceptionPredicate;
        private Predicate<T> retryOnResultPredicate;

        public Builder() {
        }

        public Builder<T> maxAttempts(int maxAttempts) {
            if (maxAttempts < 1) {
                throw new IllegalArgumentException("maxAttempts must be greater than or equal to 1");
            }
            this.maxAttempts = maxAttempts;
            return this;
        }

        public Builder<T> maxInterval(long maxInterval) {
            if (maxAttempts < 1) {
                throw new IllegalArgumentException("maxAttempts must be greater than or equal to 1");
            }
            this.maxInterval = maxInterval;
            return this;
        }

        public Builder<T> retryOnException(Predicate<Throwable> predicate) {
            this.retryOnExceptionPredicate = predicate;
            return this;
        }

        public Builder<T> retryOnResult(Predicate<T> predicate) {
            this.retryOnResultPredicate = predicate;
            return this;
        }

        public RetryConfig build() {
            return new RetryConfig(maxAttempts, maxInterval, retryOnResultPredicate, retryOnExceptionPredicate);
        }

    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public long getMaxInterval() {
        return maxInterval;
    }

    public <T> Predicate<T> getRetryOnResultPredicate() {
        return retryOnResultPredicate;
    }

    public Predicate<Throwable> getRetryOnExceptionPredicate() {
        return retryOnExceptionPredicate;
    }
}
