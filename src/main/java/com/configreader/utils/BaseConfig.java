package com.configreader.utils;

import com.configreader.ConfigReader;

import java.io.File;

public class BaseConfig implements ConfigReader {

    public static final String BASIC = "project";

    public String getPath() {
        return System.getProperty("user.dir") + File.separator
                + "src" + File.separator + "test" + File.separator
                + "resources" + File.separator;
    }

    public Object readConfig(String env) {
        return new Object();
    }


    public Object getConfig() {
        return new Object();
    }

    public String makeKey(String... parts) {
        return String.join(".", parts);
    }

}
