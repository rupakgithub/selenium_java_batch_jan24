Feature: All test cases for positive & negative scenarios

  @simpletest
  Scenario: Validate positive login flow
    Given User initilize the browser
    When User enters username 5
    And User enters password 4
    Then User should be able to login