package org.confiz.configurations;

import java.util.Map;

public interface Configuration {

    ConfigType getType();

    Map<String, Object> getConfig();
}
