Feature: Laptop filtering scenarios

  Background: User is on the mane page
    Given User open rozetka main page

  Scenario: Verify if user can filter laptops from apple brand
    When User search laptops
    And User choose apple brand checkbox
    Then All search results contains "Apple" in goods name

  Scenario: Verify if user can filter with 20000 min price and 30000 max price
    When User search laptops
    And User filter by min price 20000 and max price 30000
    Then All goods have price from 20000 to 30000 griven

  Scenario: Verify that filtering with 30000 min price and 20000 max price is not allowed
    When User search laptops
    And User input min price 30000
    And User Input max price 20000
    Then OK button in price filter module is disabled

  Scenario: Verify if user can filter only available laptops from apple brand
    When User search laptops
    And User choose apple brand checkbox
    Then All search results contains "Apple" in goods name
    When User choose available goods checkbox
    Then All search results contains "Apple" in goods name and with "Є в наявності" or "Готовий до відправлення" label