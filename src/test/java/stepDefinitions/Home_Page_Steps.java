package stepDefinitions;


import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.PropertiesReader;

import static support.World.*;

public class Home_Page_Steps {

    @Given("a shopKart website opened")
    public void a_shopkart_website_opened() {
        System.out.println("ShopKart website opened");
        Home_Page.openShopKart();
    }

    @When("User clicks on Sign In button")
    public void user_clicks_on_sign_in_button() {
        System.out.println("User clicked Sign In");
        Home_Page.signIn();
    }


    @Then("User Successfully Logged In")
    public void user_successfully_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        Home_Page.verifyUserLogin();
    }

    @When("User Searches for a {string}")
    public void user_searches_for_a(String productName) {
        Home_Page.searchProducts(productName);
    }

    @Then("Searched Product {string} list displayed in UI")
    public void searched_product_list_displayed_in_ui(String expectedProduct) {
        Home_Page.verifySearchProducts(expectedProduct);
    }


    @And("Verify the Returned product amount with {string}")
    public void verifyTheReturnedProductAmountWith(String priceLimit) {
        Home_Page.verifyProductPrice(priceLimit);

    }
}
