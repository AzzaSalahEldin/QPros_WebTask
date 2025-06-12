package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static void loadProperties() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file.", e);
        }
    }
    public static String getRunMode() {
        return properties.getProperty("runMode", "local"); // default = local
    }

    public static String getGridUrl() {
        return properties.getProperty("gridUrl", "http://localhost:4444/wd/hub");
    }
    public static String get(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        return get("browser").toLowerCase();
    }

    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }
}