package com.momagic.uploadstatus.managefils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Nati
 */
public class ManageProperties {

    public static void saveProperties(String PropertyValue, String PropertyName) {
        File configFile = new File("E:/Properties/ConfigProperties/config.properties");
        try {
            Properties props = new Properties();

            props.setProperty(PropertyName, PropertyValue);
            FileWriter writer;
            if (configFile.exists()) {
                writer = new FileWriter(configFile, true);
                props.store(writer, "");
            } else {
                writer = new FileWriter(configFile);
                props.store(writer, "People Search properties");
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
    }

    public static String getProperties(String propertyName) {
        File configFile = new File("E:/Properties/ConfigProperties/config.properties");
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            String propertyValue = props.getProperty(propertyName);
            reader.close();

            return propertyValue;

        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }

        return "";
    }

    public static Properties getJdbcProperties() {
        File configFile = new File("E:/Properties/JdbcProperties/JDBCSettings.properties");
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            //String propertyValue = props.getProperty(propertyName);
            reader.close();

            return props;

        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
        return null;
    }
}
