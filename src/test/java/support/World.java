package support;


import org.openqa.selenium.WebDriver;

import pages.Home_Page;
import pages.Login_Page;


public class World {
    public static Home_Page Home_Page;
    public static Login_Page Login_Page;
    public static WebDriverHelper webDriverHelper;


    final WebDriver driver = new BrowserCreation().getDriver();

    public void driverClass(WebDriver driver) {
        try {
            World.Home_Page = new Home_Page(this.driver);
            World.Login_Page = new Login_Page(this.driver);
            World.webDriverHelper = new WebDriverHelper(this.driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}