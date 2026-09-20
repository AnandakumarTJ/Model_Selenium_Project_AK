package support;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static support.World.webDriverHelper;

public class WebDriverHelper {

    public static Wait<WebDriver> wait;
    private final WebDriver driver;
    static Logger log = LoggerFactory.getLogger(WebDriverHelper.class);
    public String parentWindowHandle = null;
    public String childWindowHandle = null;

    public WebDriverHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilVisible(WebElement element, int timeout, int pollingTime) {
        //setting timeout to be at least 30 seconds + time specified by timeout
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout + 30))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitUntilInvisibilityofElement(WebElement element, int timeout, int pollingTime) {
        //setting timeout to be at least 30 seconds + time specified by timeout
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout + 30))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.invisibilityOf(element));
        return true;
    }

    public void waitUntilPresenceOfElement(By element, int timeout) {
        //wait until presence of element located before proceeding with executing the code
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout)).ignoreAll(ImmutableList.of(NoSuchElementException.class, TimeoutException.class));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void waitUntilVisibilityOfElement(WebElement element, int timeout) {
        //wait until the element is present
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout)).ignoreAll(ImmutableList.of(NoSuchElementException.class, TimeoutException.class));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilTheTextToBePresent(WebElement element, int timeout, String str) {
        //wait until the text is visible
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.textToBePresentInElement(element, str));
    }

    public void verifyPageTitle(String expectedPageTitle) {
        // verifies a supplied string text matches the current pages title
        Assert.assertTrue(driver.getTitle().contains(expectedPageTitle));
    }

    public void waitUntilClickable(WebElement element, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilPresenceOfAllElements(String element, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(element)));
    }

    public boolean verifyAlertPresent() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            explicitWait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilInvisibilityOfAllElements(List<WebElement> element, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    public void waitUntilVisibilityOfAllElements(List<WebElement> element, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }


    public void waitUntilFrameToBeAvailableAndSwitchToIt(WebElement element, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void waitUntilURLContains(String url, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.urlContains(url));
    }

    public void waitUntilInvisibilityOfElementWithText(String element, String value, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(element), value));
    }

    public void waitUntilTextToBePresentInElement(WebElement element, int timeout, int pollingTime, String text) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitUntilAttributeToBePresent(WebElement element, String attribute, String value, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public void focusElementAndReplaceText(WebElement element, String value) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
    }

    public WebElement getDynamicWebElement(WebElement element, String dummyValue, String actualValue) {
        String xpath = getLocatorStringFromWebElement(element);
        String elementXpathWithActualValue = xpath.replaceAll(dummyValue, actualValue);
        WebElement dynamicElement = null;
        if (element.toString().contains("xpath:"))
            dynamicElement = driver.findElement(By.xpath(elementXpathWithActualValue));
        else if (element.toString().contains("className:"))
            dynamicElement = driver.findElement(By.className(elementXpathWithActualValue));
        else if (element.toString().contains("id:"))
            dynamicElement = driver.findElement(By.id(elementXpathWithActualValue));
        return dynamicElement;
    }

    public String getLocatorStringFromWebElement(WebElement element) {
        // If we print webElement variable which has xpath, It prints the below format
        // [[FirefoxDriver: firefox on WINDOWS (4520a9a1-c58b-4b34-ae3e-48613e6e7005)] -> xpath: //div[@class='page-title category-title']]`
        // From this, we have to extract xpath and return WebElement
        String locatorString = "";
        if (element.toString().contains("xpath:"))
            locatorString = StringUtils.chop(element.toString().split("xpath:")[1].trim());
        else if (element.toString().contains("class name:"))
            locatorString = StringUtils.chop(element.toString().split("class name:")[1].trim());
        else if (element.toString().contains("id:"))
            locatorString = StringUtils.chop(element.toString().split("id:")[1].trim());
        return locatorString;
    }

    // This method will wait until element gets invisible (Ex: On page load, if verifying page spinner gets invisible)
    public void waitUntilInvisibilityOfElementLocated(String elementLocator) {
        if (elementLocator.startsWith("//")) {
            new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementLocator)));
        } else if (elementLocator.startsWith(".")) {
            elementLocator = elementLocator.replace(".", "");
            new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.invisibilityOfElementLocated(By.className(elementLocator)));
        }
    }

    public void waitForAnElementTextToBeChanged(WebElement element, String expectedText, int totalWaitTime, int pageRefreshTimeInSeconds) {
        // Ex: if pageRefreshTimeInSeconds set to 30 seconds
        // Ex: Every 30 seconds, we are refreshing page if expected round status is not matching in CAT tool.
        int noOfIntervals = (totalWaitTime * 60) / 30;
        webDriverHelper.waitUntilVisible(element, 20, 1);
        log.info("Waiting for an element text to be changes to '" + expectedText + "'");
        while (!element.getText().equalsIgnoreCase(expectedText) && noOfIntervals > 0) {
            if (element.getText().equalsIgnoreCase(expectedText))
                break;
            driver.navigate().refresh();
            webDriverHelper.threadSleep(pageRefreshTimeInSeconds * 1000);
            webDriverHelper.waitUntilVisible(element, 20, 1);
            noOfIntervals--;
        }
    }

    public void openNewTabAndSwitchDriverToNewWindow() {
       // javascriptUtility.openNewTab();
        parentWindowHandle = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(parentWindowHandle)) {
                childWindowHandle = winHandle;
                driver.switchTo().window(winHandle);
                log.info("game launched in new window successfully");
                break;
            }
        }
    }

    public void closeNewlyOpenedTabAndSwitchDriverToParentWindow() {
        if (parentWindowHandle != null) {
            driver.close();
            driver.switchTo().window(parentWindowHandle);
            log.info("game window has been closed successfully and driver switched to parent window");
        }
    }

    public void switchToParentWindow() {
        if (parentWindowHandle != null) {
            driver.switchTo().window(parentWindowHandle);
        }
    }

    public void switchToChildWindow() {
        if (childWindowHandle != null) {
            driver.switchTo().window(childWindowHandle);
        }
    }

    public void threadSleep(int sleepTimeInMilliSec) {
        try {
            Thread.sleep(sleepTimeInMilliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigateBack() {
        driver.navigate().back();
    }
}