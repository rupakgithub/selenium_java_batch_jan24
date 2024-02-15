Feature: All test cases for positive & negative scenarios

  @simpletest
  Scenario: Validate positive login flow
    When User enters "standard_user"
    And User enters "secret_sauce"
    Then User should be able to login