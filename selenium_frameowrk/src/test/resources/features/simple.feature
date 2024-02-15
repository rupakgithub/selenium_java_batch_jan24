Feature: All test cases for positive & negative scenarios

  @simpletest
  Scenario: Validate positive login flow
    Given User initilize the browser
    When User enters username "standard_user"
    And User enters password "secret_sauce"
    Then User should be able to login