package org.example.stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Saucedemostepdef {

    @When("User enters {string}")
    public void user_enters(String user_cred) {
        System.out.println("user entered "+user_cred);
    }

    @Then("User should be able to login")
    public void user_should_be_able_to_login() {
        System.out.println("user successfully login");
    }
}
