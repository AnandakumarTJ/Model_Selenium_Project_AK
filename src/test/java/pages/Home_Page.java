package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertTrue;
import static support.World.*;

public class Home_Page {
    private static final Logger log = LoggerFactory.getLogger(Home_Page.class);
    private final WebDriver driver;
    @FindBy(xpath = "//div[@class='mt-4 grid grid-cols-2 gap-4 md:grid-cols-3 xl:grid-cols-4']")
    List<WebElement> returned_Products;
    @FindBy(xpath = "//a[@id = 'header-signin']")
    private WebElement home_signIn_button;
    @FindBy(xpath = "//input[@data-testid = 'input-header-search-desktop']")
    private WebElement search_products_input;
    @FindBy(xpath = "//button[@data-testid='btn-header-search-desktop']")
    private WebElement search_products_submit;
    @FindBy(xpath = "//span[@class='hidden text-sm sm:inline']")
    private WebElement userName;

    public Home_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openShopKart() {
        driver.get("https://cart-corner-com.lovable.app/");
    }

    public void searchProducts(String productName) {

        webDriverHelper.waitUntilVisible(search_products_input, 10, 2);
        search_products_input.sendKeys(productName);
        search_products_submit.click();
    }

    public void signIn() {
        webDriverHelper.waitUntilClickable(home_signIn_button, 10, 3);
        home_signIn_button.click();
    }

    public void verifySearchProducts(String expectedProductName) {

        webDriverHelper.waitUntilVisibilityOfAllElements(returned_Products, 10, 2);
        List<WebElement> products = returned_Products;
        for (WebElement product : products) {
            System.out.println(product.getText());
            assert product.getText().contains(expectedProductName);
        }
    }

    public void verifyUserLogin() {
        webDriverHelper.waitUntilClickable(userName, 10, 3);
        log.info(userName.getText().trim());
        Assert.assertTrue(userName.isDisplayed(), "Username field is not displayed");
    }

    public void verifyProductPrice(String productPrice) {

        int priceLimit = Integer.parseInt(productPrice);

        Pattern pricePattern = Pattern.compile("₹\\s*([\\d,]+)");

        for (WebElement product : returned_Products) {

            String productText = product.getText();

            Matcher matcher = pricePattern.matcher(productText);

            if (!matcher.find()) {
                throw new AssertionError(
                        "Price not found for product: " + productText
                );
            }

            int actualPrice = Integer.parseInt(
                    matcher.group(1).replace(",", "")
            );

            System.out.println(
                    "Product: " + productText +
                            " | Price: ₹" + actualPrice
            );

            assertTrue(
                    "Product price ₹" + actualPrice +
                            " is greater than price limit ₹" + priceLimit +
                            "\nProduct: " + productText,
                    actualPrice <= priceLimit
            );
        }
    }
}