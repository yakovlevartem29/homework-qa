package com.homework.util;

import com.google.common.io.Resources;
import com.homework.TestException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Provides utility methods reading resource files.
 */
public class ResourcesHelper {

    private ResourcesHelper() {
    }

    public static List<String> getResourceAsListString(String resourcePath) {
        URL resourceUrl = Resources.getResource(resourcePath);
        try {
            return Resources.readLines(resourceUrl, StandardCharsets.UTF_8);
        } catch (IOException ioe) {
            throw new TestException("Failed to read resource: " + resourcePath, ioe);
        }
    }
}
