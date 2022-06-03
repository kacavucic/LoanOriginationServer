package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    private Properties properties;
    private static PropertiesLoader instance;

    private PropertiesLoader() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("config/serverconfig.properties"));
            properties.load(new FileInputStream("config/dbconfig.properties"));
           

            System.out.println("Connected to database");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static PropertiesLoader getInstance() {
        if (instance == null) {
            instance = new PropertiesLoader();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void saveServerProperties() throws FileNotFoundException, IOException {
        properties.store(new FileOutputStream("config/serverconfig.properties"), "Successfully saved");
    }

    public void saveDBProperties() throws FileNotFoundException, IOException {
        properties.store(new FileOutputStream("config/dbconfig.properties"), "Successfully saved");
    }
}
