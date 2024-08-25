@tag
Feature: Purchase the order from the ecommerce website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Home Page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with Username <name> and password <password>
    When I add product<productName> to cart
    And checkout <productName> and submit the order 
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  									| password 				| productName 		|
      | himanshu.shop@gmail.com | #Deepak1234# 		| ZARA COAT 3 		|
