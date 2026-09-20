package support;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static support.World.*;

public class BrowserCreation {

    private static final Logger log = LoggerFactory.getLogger(BrowserCreation.class);
    public static Scenario scenario;
    private static WebDriver driver;
    private final ChromiumOptions<ChromeOptions> chromeOptions = new ChromeOptions();
    private final ChromiumOptions<EdgeOptions> edgeOptions = new EdgeOptions();
    private final LoggingPreferences logPrefs = new LoggingPreferences();
    private JavascriptExecutor jse;

    public static void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) log.info("Scenario --> " + scenario.getName() + " has failed");
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
        //scenario.embed(screenshot, "image/png", scenario.getName());

    }

    @Before
    public void setUp(Scenario scenario) {
        new PropertiesReader().loadProperties();

        log.info("Currently executing Scenario --> " + scenario.getName());
        BrowserCreation.scenario = scenario;

//        if (PropertiesReader.browser_required.equals("true")) {
//            switch (PropertiesReader.browser_type) {
//                case "chrome":
//                    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
//                    System.out.println("os.name  --> " + System.getProperty("os.name"));
//                    //if (System.getProperty("os.name").contains("Linux"))
//                    //    WebDriverManager.chromedriver().driverVersion("107.0.5304.62").setup();
//                    driver = new ChromeDriver((ChromeOptions) loadChromeOrEdgeBrowserCapabilities(chromeOptions));
//                    break;
//                case "edge":
//                    WebDriverManager.edgedriver().setup();
//                    driver = new EdgeDriver((EdgeOptions) loadChromeOrEdgeBrowserCapabilities(edgeOptions));
//                    break;
//                case "firefox":
//                    WebDriverManager.firefoxdriver().setup();
//                    driver = new FirefoxDriver();
//                    break;
//            }

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        new World().driverClass(driver);
    }

    public ChromiumOptions loadChromeOrEdgeBrowserCapabilities(ChromiumOptions options) {
        if (PropertiesReader.browser_headless.equals("true") && PropertiesReader.browser_type.equalsIgnoreCase("chrome"))
            options.addArguments("--disable-gpu", "--window-size=1600,900", "--no-sandbox", "--allow-insecure-localhost", "--headless" + (scenario.getName().contains("Game Interactions") ? "" : "=new"));
        options.addArguments("--disable-gpu", "--disable-extensions", "--test-type", "start-maximized", "--silent", "--disable-dev-shm-usage", "--ignore-certificate-errors");
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
            put("download.default_directory", System.getProperty("user.dir"));
            put("acceptInsecureCerts", true);
            put("pageLoadStrategy", "normal"); // normal, eager, none
        }});
        return options;
    }

//    @After
//    public void tearDown() {
//        if (PropertiesReader.browser_required.equals("true")) {
//            driver.quit();
//        }
//    }
//
    public WebDriver getDriver() {
        return driver;
    }
//
    @After
    public void embedScreenshot(Scenario scenario) {
        //If test fails takes a screenshot and embeds it in the Cucumber report
        // if testcase fails at testRail GET Request, no need to capture the screenshot.
        if ((scenario.isFailed())) {
            // This method will embed the screenshot in cucumber report.
            takeScreenshot(scenario);
            // Take screenshot and save it in reporting folder to attach screenshot for the failed testcase result for testRail.

        }

        driver.quit();

    }


}