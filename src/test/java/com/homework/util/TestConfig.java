package com.homework.util;

import com.homework.TestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Provides required information about application under test
 */
public class TestConfig {

    private static final Logger LOG = LoggerFactory.getLogger(TestConfig.class);
    private static final String DEFAULT_CONFIG_FILE_NAME = "config.properties";
    private final Properties properties = new Properties();

    private TestConfig() {
        InputStream configFileStream = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE_NAME);
        if (configFileStream == null) {
            LOG.error("Test resource file [{}] is missing", DEFAULT_CONFIG_FILE_NAME);
            throw new TestException("Configuration file is missing");
        }
        try {
            properties.load(configFileStream);
        } catch (IOException ioe) {
            throw new TestException("Failed to read test configuration file", ioe);
        }

        // override params
        for (Param parameter : Param.values()) {
            String propertyValue = System.getProperty(parameter.getName());
            if (propertyValue != null) {
                properties.setProperty(parameter.getName(), propertyValue);
            }
        }
    }

    private static final class HolderInstance {

        static final TestConfig INSTANCE = new TestConfig();
    }

    private static String getParamValue(Param param) {
        return HolderInstance.INSTANCE.properties.getProperty(param.getName());
    }

    public static String getBaseUrl() {
        return getParamValue(Param.BASE_URL);
    }

    private enum Param {

        BASE_URL("baseUrl");
        private final String name;

        Param(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
