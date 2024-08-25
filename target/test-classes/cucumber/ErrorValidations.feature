
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
    Scenario Outline: Error Validation Scenario
    Given I landed on Ecommerce Home Page
    When Logged in with Username <name> and password <password>
    Then "Incorrect email or password." message is displayed
   
    Examples: 
      | name  									| password 			| 
      | himanshu.shop@gmail.com | #Deepak12# 		| 