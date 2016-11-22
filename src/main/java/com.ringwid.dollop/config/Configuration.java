package com.ringwid.dollop.config;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class Configuration {

    private ConfigurationProvider configurationProvider;

    public void load(String path) {
        ConfigFilesProvider configFilesProvider = () -> Arrays.asList(Paths.get(path));
        ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);
        configurationProvider = new ConfigurationProviderBuilder().withConfigurationSource(source).build();
    }

    public ConfigurationProvider getConfigurationProvider() {
        return configurationProvider;
    }

}
