package org.plc.pocker.game;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public abstract class GameConfiguration<T> {
    private String yamlFileName;
    private T configuration;

    GameConfiguration(String yamlFileName, T configuration) {
        this.yamlFileName = yamlFileName;
        this.configuration = configuration;
        loadConfiguration();
    }

    public T getConfiguration() {
        return configuration;
    }

    private void loadConfiguration() {
        Yaml yaml = new Yaml(new Constructor(configuration.getClass()));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(yamlFileName);
        configuration = yaml.load(inputStream);
    }
}
