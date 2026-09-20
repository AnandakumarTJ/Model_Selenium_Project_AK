package support;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class PropertiesReader {
    public static String browser_required;
    public static String browser_type;
    public static String browser_headless;
    public static String environment;
    public static String base_url;
    public static String user_name;
    public static String password;


    public void loadProperties() {
        Properties settings = new Properties();
        Properties env = new Properties();
        Properties userData = new Properties();

        try {
            settings.load(new FileInputStream("config/settings.properties"));
            env.load(new FileInputStream("environment/env.properties"));
            userData.load(new FileInputStream("data/userData.properties"));

            // get the property value and set it to a global variable
            // get the property value and set it to a global variable
            browser_required = settings.getProperty("browser.required");
            browser_type = settings.getProperty("browser.type");
            browser_headless = settings.getProperty("browser.headless");
            environment = settings.getProperty("environment");
            base_url = env.getProperty(environment + ".base_url");
            user_name = userData.getProperty("userName");
            password = userData.getProperty("passWord");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadProperties(String key) {
        Properties data = new Properties();
        try {
            data.load(new FileInputStream("data/commonData.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.getProperty(key);
    }
}