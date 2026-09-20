package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import support.World;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;
import static support.World.*;

public class Login_Page {

    private final WebDriver driver;

    @FindBy(xpath = "//input[@id='signin-email']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='signin-password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit'")
    private WebElement continue_button;

    @FindBy(xpath = "//button[@id='btn-signin']")
    private WebElement signIn_button;

    @FindBy(xpath = "//li[contains(normalize-space(),'Invalid login credentials')]")
    private WebElement invalid_email;

    public Login_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String Email) {
        username.sendKeys(Email);
    }

    public void enterPassword(String Password) {
        webDriverHelper.waitUntilVisible(password, 5, 2);
        password.sendKeys(Password);
    }

    public void Login_With_Password(String Email, String Password) {
        enterEmail(Email);
        enterPassword(Password);
        clickOnSignInButton();
    }

    public void verifyLoginPageLoaded() {
        webDriverHelper.waitUntilVisible(username, 10, 2);
        Assert.assertTrue(username.isDisplayed(), "Username field is not displayed");
    }

    public void clickOnSignInButton() {
        signIn_button.click();
    }

    public void verifyFieldRequiredValidation(WebElement elementRequired) {
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript(
                        "return arguments[0].validationMessage;",
                        elementRequired
                );

        assertEquals(validationMessage, "Please fill in this field.");
    }

    public void verifyEmailRequiredValidation() {
        verifyFieldRequiredValidation(username);
    }

    public void verifyPasswordRequiredValidation() {
        clickOnSignInButton();
        verifyFieldRequiredValidation(password);
    }

    public void verifyInvalidCredentials() {

        webDriverHelper.waitUntilVisible(invalid_email, 5, 2);
        assertEquals(invalid_email.getText().trim(), "Invalid login credentials");

        boolean alertDisappeared = webDriverHelper.waitUntilInvisibilityofElement(invalid_email,5,2);
        assertTrue(alertDisappeared, "Invalid login credentials Alert Not Disappeared");
    }
}