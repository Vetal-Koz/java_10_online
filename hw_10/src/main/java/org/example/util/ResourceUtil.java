package org.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceUtil {

    private ResourceUtil() {
    }

    ;

    public static Map<String, String> getResource(ClassLoader classLoader) {
        try (InputStream inputStream = classLoader.getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Map<String, String> resourcesMap = new HashMap<>();
            properties.forEach((key, value) -> {
                resourcesMap.put(key.toString(), value.toString());
            });
            return resourcesMap;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
