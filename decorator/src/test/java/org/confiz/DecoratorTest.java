package org.confiz;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Supplier;

public class DecoratorTest {

    @Test
    public void test() {
        RetryConfig retryConfig = RetryConfig.<String>custom()
                .maxAttempts(3)
                .maxInterval(2000L)
                .retryOnResult(s -> s.contains("Hello world"))
                .retryOnException((e) -> !(e instanceof NullPointerException))
                .build();

        Retry retry = Retry.of(retryConfig);
        Supplier<String> supplier = Retry.decorateSupplier(retry, () -> "Hello world");

        String result = supplier.get();
        Assert.assertNotNull(result);

        RetrySupplier<String> retrySupplier = new RetrySupplier<>(retryConfig, () -> "Hello world");
        String result1 = retrySupplier.get();
        Assert.assertNotNull(result1);

    }
}
