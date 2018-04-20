package com.configreader;

public interface ConfigReader {

    String getPath();

    Object readConfig(String env);

    Object getConfig();
}
