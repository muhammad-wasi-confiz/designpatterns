package org.confiz.configurations;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class DevConfiguration implements Configuration {
    private static final ConfigType TYPE = ConfigType.DEV;
    private final Map<String, Object> config;

    public DevConfiguration() throws Exception {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/dev.json"));
        config = gson.fromJson(bufferedReader, HashMap.class);
    }

    @Override
    public ConfigType getType() {
        return TYPE;
    }

    @Override
    public Map<String, Object> getConfig() {
        return config;
    }


}
