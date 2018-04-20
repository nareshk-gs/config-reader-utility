package com.configreader.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class YamlConfig extends BaseConfig {

    private Map<String, String> properties;
    private static final String EXTENSION = ".yml";
    private static StringBuilder parentKey = new StringBuilder();


    public YamlConfig() {
        this.properties = readConfig(BASIC);
    }

    public YamlConfig(String env) {
        readConfig(env);
        Map<String, String> defaultProps = readConfig(BASIC);
        mergeProperties(defaultProps);
    }

    public Map<String, String> getConfig() {
        return properties;
    }

    public Map<String, String> readConfig(String file) {
        String propertyFile = getPath() + file + EXTENSION;
        Map<String, Object> map = new HashMap<>();
        try{
            InputStream stream = new FileInputStream(new File(propertyFile));
            map = new Yaml().load(stream);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        Map<String, String> configMap = delistData(map);
        if(!file.equalsIgnoreCase("project")) {
            this.properties = configMap;
        }
        return configMap;
    }

    private Map<String, String> delistData(Map<String, Object> map) {
        Map<String, String> thismap = new HashMap<>();

        for (Map.Entry<String, Object> entry :map.entrySet()) {

            String key = entry.getKey();
            Object value = entry.getValue();
            String valueClass = value.getClass().toString();

            if(valueClass.contains("String") || valueClass.contains("Integer") || valueClass.contains("ArrayList")) {
                thismap.put(key, String.valueOf(value));
            }

            if(valueClass.contains("LinkedHashMap")) {
                thismap.putAll(rebuildConfig(key, value));
            }
        }

        return thismap;
    }

    private Map<String, String> rebuildConfig(String key, Object value) {
        Map<String, Object> map = (Map<String, Object>) value;
        Map<String, String> returningMap = new HashMap<>();
        Set<String> valueKeys = map.keySet();
        if(parentKey.length() == 0) {
            parentKey.append(key);
        } else {
            parentKey.append("."+key);
        }

        for(String valueKey : valueKeys) {
            String className = map.get(valueKey).getClass().toString();
            if(className.contains("LinkedHashMap")) {
                returningMap.putAll(rebuildConfig(valueKey, map.get(valueKey)));
            } else {
                String v = String.valueOf(map.get(valueKey));
                returningMap.put(makeKey(parentKey.toString(), valueKey), v);
            }
        }

        return returningMap;
    }

    private void mergeProperties(Map<String, String> props) {
        props.forEach(properties::putIfAbsent);
    }

}
