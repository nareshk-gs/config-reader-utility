package com.configreader.utils;

import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class YamlConfigTest {

    @Test
    public void loadGlobalProps() {
        Map<String, String> properties = new YamlConfig().getConfig();
        assertThat(3, is(equalTo(properties.size())));
        assertThat("Test User", is(equalTo(properties.get("name"))));
    }

    @Test
    public void loadAllProps() {
        Map<String, String> properties = new YamlConfig("user").getConfig();
        assertThat(9, is(equalTo(properties.size())));
        assertThat("20000", is(equalTo(properties.get("address.newAddress.finalAddress.zip"))));
    }

}
