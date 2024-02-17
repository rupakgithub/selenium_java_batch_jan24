Feature: All test cases for positive & negative scenarios

  @simpletest
  Scenario: Validate positive login flow
    Given User initilize the browser
    When User enters username 5
    And User enters password 4
    Then User should be able to login

  @smoke@Regression@Userstory
  Scenario Outline: Demo scenario outline
    Given HR searching for "<Job Title>" for post of "Two" "<Vacancy>"


    Examples:
      |Job Title| Vacancy|
      |QA Lead|SDET|
      |UI Lead|Architect|

  @dataexample
  Scenario: Login with positive/correct credentials
    When User enterss corrects credentials
      |standard_user|secret_sauce|
    Then User shoulds be ables to login