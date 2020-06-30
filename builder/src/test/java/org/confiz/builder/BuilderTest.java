package org.confiz.builder;

import org.junit.Assert;
import org.junit.Test;

public class BuilderTest {
    @Test
    public void testBuildConfiguration() {
        Configuration config = Configuration.builder()
                .withUrl("http://localhost:8001")
                .withConnectTimeout(3000)
                .withRequestTimeout(3000)
                .withSocketTimeout(3000)
                .withIgnoreErrors(true)
                .withDebug(true)
                .build();

        Assert.assertNotNull(config);
        Assert.assertEquals("http://localhost:8001", config.getUrl());
        Assert.assertEquals(3000, config.getConnectTimeout());
        Assert.assertEquals(3000, config.getRequestTimeout());
        Assert.assertEquals(3000, config.getSocketTimeout());
        Assert.assertEquals(true, config.isIgnoreErrors());
        Assert.assertEquals(true, config.isDebug());
    }
}
