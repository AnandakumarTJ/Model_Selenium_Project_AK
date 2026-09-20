package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.PropertiesReader;

import static support.World.*;

public class Login_Page_Steps {

    @Then("User get redirected to Login Page")
    public void user_get_redirected_to_login_page() throws InterruptedException {
        Login_Page.verifyLoginPageLoaded();
        System.out.println("User redirected to Login Page");

    }

    @When("User Enters valid Username and Password")
    public void user_enters_valid_username_and_password() {
        Login_Page.Login_With_Password(PropertiesReader.user_name, PropertiesReader.password);
    }

    @When("User clicks on Sign In button without valid Input")
    public void user_clicks_on_sign_in_button_without_valid_input() {
        Login_Page.clickOnSignInButton();
    }

    @Then("User gets Placeholder message on Email")
    public void user_gets_placeholder_message_on_email() {
        Login_Page.verifyEmailRequiredValidation();

    }

    @When("User clicks on Sign In button without valid Input on Password Field")
    public void user_clicks_on_sign_in_button_without_valid_input_on_password_field() {
        Login_Page.enterEmail(PropertiesReader.user_name);

    }

    @Then("User gets Placeholder message on Password")
    public void user_gets_placeholder_message_on_password() {
        Login_Page.verifyPasswordRequiredValidation();
    }

    @When("User enters Invalid Credentials {string}, {string} and clicked on Sign-In")
    public void user_enters_invalid_credentials_and_clicked_on_sign_in(String username, String password) {
        Login_Page.enterEmail(username);
        Login_Page.enterPassword(password);
        Login_Page.clickOnSignInButton();
    }
    @Then("User gets Invalid Credentials Alert")
    public void user_gets_invalid_credentials_alert() {
        Login_Page.verifyInvalidCredentials();
    }
}
