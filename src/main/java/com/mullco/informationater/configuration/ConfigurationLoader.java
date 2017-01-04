package com.mullco.informationater.configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {

    public static Configuration configs() {
        Properties p = getProperties();

        return new Configuration(
                val(p, "jiraUrl"),
                val(p, "jiraUid"),
                val(p, "jiraPwd"),
                val(p, "emailHost"),
                val(p, "emailUid"),
                val(p, "emailPwd"));
    }

    private static String val(Properties p, String key) {
        return p.getProperty(key);
    }

    private static Properties getProperties() {
        try {
            String homeDirectory = System.getProperty("user.home");
            FileReader fileReader = new FileReader(homeDirectory + "/.propertizer");

            Properties prop = new Properties();
            prop.load(fileReader);

            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
