Feature: Login Action

  Scenario Outline: Login with valid and Invalid Credentials
    Given User is on Home Page
    When User navigate to Login Page
    Then User enters "<username>" and "<password>"
    And Keeping case as <caseType>
    Then User should get logged in
    And Message displayed Login Successfully
    Then user will be asked to go back to login page
    And Provide correct credentials

    Examples:
      | username                 | password  | caseType |
      | shimpirohit077@gmail.com | Rohit@123 | Valid    |
      | abc1@gmail.com           | dfsd2     | InValid  |
