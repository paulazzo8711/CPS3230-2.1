Feature: Reachability of Product Subcategories

  Scenario Outline: User can navigate to a product subcategory and view products
    Given I am a user of the website
    When I visit the xpert website
    And I click on All Categories
    And I click on the "<category>" category
    And I click on the "<subcategory>" subcategory
    Then I should be taken to "<subcategory>" subcategory page
    And the page should show at least <num-products> products
    When I click on the first product in the results
    Then I should be taken to the details page for that product

    Examples:
      | category         | subcategory  | num-products |
      | Audio            | Headsets     | 10           |
      | Displays         | Monitors     | 15           |
      | External Storage | External Drives | 2      |
      | Components       | Cpu          | 5            |
      | Notebooks & Tablets | Tablets | 2         |
