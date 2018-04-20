package com.configreader.utils;

import org.testng.annotations.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PropertyConfigTest {

    @Test
    public void loadGlobalProps() {
        Properties properties = new PropertyConfig().getConfig();
        assertThat(3, is(equalTo(properties.size())));
        assertThat("global", is(equalTo(properties.getProperty("property"))));
    }

    @Test
    public void loadMergedProps() {
        Properties properties = new PropertyConfig("specific").getConfig();
        assertThat(5, is(equalTo(properties.size())));
        assertThat("specific", is(equalTo(properties.getProperty("property"))));
    }


}
