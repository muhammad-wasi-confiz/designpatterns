package org.confiz;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class RetrySupplier<T> implements Supplier {
    private RetryConfig config;
    private Supplier<T> supplier;

    public RetrySupplier(RetryConfig config, Supplier<T> supplier) {
        this.config = config;
        this.supplier = supplier;
    }

    @Override
    public T get() {
        int counter = 0;
        Predicate<T> onResultPredicate = config.getRetryOnResultPredicate();
        Predicate<Throwable> onExceptionPredicate = config.getRetryOnExceptionPredicate();

        do try {
            T result = supplier.get();

            if (onResultPredicate != null && onResultPredicate.test(result)) {
                if (++counter >= config.getMaxAttempts()) {
                    return result;
                }
            }

            System.out.println("Retry -> " + counter);
        } catch (RuntimeException runtimeException) {
            if (onExceptionPredicate != null && onExceptionPredicate.test(runtimeException)) {
                if (++counter >= config.getMaxAttempts()) {
                    throw runtimeException;
                }
            }
        } while (true);
    }
}
