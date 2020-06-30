package org.confiz.configurations;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ProdConfiguration implements Configuration {
    private static final ConfigType TYPE = ConfigType.PROD;
    private final Map<String, Object> config;

    public ProdConfiguration() throws Exception {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/prod.json"));
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
