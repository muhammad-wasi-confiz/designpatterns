package org.confiz;

import org.confiz.configurations.*;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationFactory {
    private static Map<ConfigType, Configuration> configurations = new HashMap<>();

    public static Configuration createConfiguration(ConfigType type) throws Exception {
        Configuration configuration = configurations.get(type);
        if (configuration == null) {
            switch (type) {
                case DEV:
                    configuration = new DevConfiguration();
                    configurations.put(ConfigType.DEV, configuration);
                    break;
                case STAGE:
                    configuration = new StageConfiguration();
                    configurations.put(ConfigType.STAGE, configuration);
                    break;
                case PROD:
                    configuration = new ProdConfiguration();
                    configurations.put(ConfigType.PROD, configuration);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }
        }

        return configuration;
    }
}
