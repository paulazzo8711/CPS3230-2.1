Feature: Searching for a specific Product

  Scenario Outline: User can search for a product and view details
    Given I am a user of the website
    When I visit the xpert website
    And I search for a product using the term "<product>" product
    Then I should be taken to "<product>" product results page
    And the page should show at least <num-products> products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

    Examples:
      | product        |num-products |
      | USB            |5|
      | Keyboard       |5|
      | Mouse          |5|
      | Modem          |5|
      | Samsung        |5|
