package com.configreader.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyConfig extends BaseConfig {

    private static final String EXTENSION = ".properties";



    public PropertyConfig() {
        this.properties = readConfig(BaseConfig.BASIC);
    }

    public PropertyConfig(String env) {
        this.properties = readConfig(env);
        mergeConfig(readConfig(BASIC));
    }

    public Map<String, String> readConfig(String file) {
        String propertyFile = getPath() + file + EXTENSION;
        Properties localProperties = new Properties();
        Map<String, String> map = new HashMap<>();
        try {
            InputStream input = new FileInputStream(new File(propertyFile));
            localProperties.load(input);
            localProperties.forEach( (k,v) -> map.put((String)k, (String)v));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return map;
    }
    
    public Map<String, String> getConfig() {
        return properties;
    }




}
