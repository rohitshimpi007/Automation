package stepDefinitions;

import io.cucumber.java.en.*;

public class LoginSteps {

    @Given("User is on Home Page")
    public void user_is_on_home_page() {
        System.out.println("User is on Home Page");
    }

    @When("User navigate to Login Page")
    public void user_navigate_to_login_page() {
        System.out.println("User navigates to Login Page");
    }

    @Then("User enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        System.out.println("User enters: " + username + " / " + password);
    }

    @Then("Keeping case as Valid")
    public void keeping_case_as_valid() {
        System.out.println("Valid case");
    }

    @Then("Keeping case as InValid")
    public void keeping_case_as_invalid() {
        System.out.println("Invalid case");
    }

    @Then("User should get logged in")
    public void user_should_get_logged_in() {
        System.out.println("User should get logged in");
    }

    @Then("Message displayed Login Successfully")
    public void message_displayed_login_successfully() {
        System.out.println("Login Successful");
    }

    @Then("user will be asked to go back to login page")
    public void user_will_be_asked_to_go_back_to_login_page() {
        System.out.println("Go back to login page");
    }

    @Then("Provide correct credentials")
    public void provide_correct_credentials() {
        System.out.println("Provide correct credentials");
    }
}
