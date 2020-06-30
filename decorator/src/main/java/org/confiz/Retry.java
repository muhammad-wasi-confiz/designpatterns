package org.confiz;

import java.util.function.Supplier;

public interface Retry {

    <T> Retry.Context<T> context();

    interface Context<T> {

        boolean onResult(T result);

        void onRuntimeError(RuntimeException runtimeException);


    }

    static Retry of(RetryConfig retryConfig) {
        return new RetryImpl(retryConfig);
    }

    static <T> Supplier<T> decorateSupplier(Retry retry, Supplier<T> supplier) {
        return () -> {
            Context<T> context = retry.context();
            do try {
                T result = supplier.get();
                final boolean validationOfResult = context.onResult(result);
                if (!validationOfResult) {
                    return result;
                }
            } catch (RuntimeException runtimeException) {
                context.onRuntimeError(runtimeException);
            } while (true);
        };
    }
}
