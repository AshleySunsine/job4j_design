package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithCommentTakeValue() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairTakeValueFail() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("ooo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWrongPatternKeyValue() {
        String path = "app.propertiesFailKeyValue";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenKeyIsnt() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("hh"));
    }

}