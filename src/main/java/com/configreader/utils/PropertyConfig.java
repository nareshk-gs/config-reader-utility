package com.configreader.utils;

import java.io.*;
import java.util.Properties;

public class PropertyConfig extends BaseConfig {

    public static Properties properties;
    private static final String EXTENSION = ".properties";



    public PropertyConfig() {
        this.properties = readConfig(BaseConfig.BASIC);
    }

    public PropertyConfig(String env) {
        this.properties = readConfig(env);
        mergeConfig(readConfig(BASIC));
    }

    public Properties readConfig(String file) {
        String propertyFile = getPath() + file + EXTENSION;
        Properties localProperties = new Properties();
        try {
            InputStream input = new FileInputStream(new File(propertyFile));
            localProperties.load(input);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return localProperties;
    }

    private void mergeConfig(Properties props) {
        props.forEach(properties::putIfAbsent);
    }

    public Properties getConfig() {
        return properties;
    }




}
