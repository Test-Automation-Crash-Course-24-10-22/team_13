Feature: Laptop sorting scenarios

  Background: User is on the mane page
    Given User open rozetka main page

  Scenario: Verify if user can sort laptops from lower to higher
    When User search laptops
    And User select the 'Від дешевих до дорогих' option
    Then Every next goods is more expensive or equal than the previous

  Scenario: Verify if user can sort laptops from higher to lower price
    When User search laptops
    And User select the 'Від дорогих до дешевих' option
    Then Every next goods is cheaper or equal than the previous