package helpers;

import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private static PropertyProvider instance;
    private final Properties properties = new Properties();

    public static PropertyProvider getInstance() {
        if (instance == null) {
            instance = new PropertyProvider();
        }
        return instance;
    }
    private PropertyProvider() {
        String propertyFileName = "local.properties";
        try (InputStream propertiesInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFileName)) {
            properties.load(propertiesInputStream);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return getInstance().properties.getProperty(key);
    }
}
