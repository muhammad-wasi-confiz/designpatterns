package org.confiz;

import org.confiz.configurations.ConfigType;
import org.confiz.configurations.Configuration;
import org.junit.Assert;
import org.junit.Test;

public class FlyweightTest {

    @Test
    public void test() throws Exception {
        Configuration config = ConfigurationFactory.createConfiguration(ConfigType.DEV);
        Configuration config2 = ConfigurationFactory.createConfiguration(ConfigType.DEV);

        Assert.assertEquals(config, config2);
    }
}
