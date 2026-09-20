Feature: User Login

  Background:
    Given a shopKart website opened
    When User clicks on Sign In button
    Then User get redirected to Login Page

  @Regression
  Scenario: Verify User Login
    And User Enters valid Username and Password
    Then  User Successfully Logged In

  @Regression
  Scenario: Verify User Login with Email field Empty
    When User clicks on Sign In button without valid Input
    Then User gets Placeholder message on Email

  @Regression
  Scenario: Verify User Login with Password field Empty
    When User clicks on Sign In button without valid Input on Password Field
    Then User gets Placeholder message on Password

  @Regression
  Scenario Outline: Verify User Login with Invalid credentials
    When User enters Invalid Credentials "<username>", "<password>" and clicked on Sign-In
    Then User gets Invalid Credentials Alert

    Examples:

      | username            | password |
      | akamade23@gmail.com | abcbbc   |
      | abc@bbc.com         | ansjjhui |
