Feature: Search Product in ShopKart

  Background:
    Given a shopKart website opened

  @Smoke
  Scenario Outline: Verify Returned Search Products
    When User Searches for a "<productName>"
    Then Searched Product "<productName>" list displayed in UI

    Examples:
      | productName |
      | Shirt       |
      | Bag        |
      | Dress       |

  @Smoke
  Scenario Outline: Verify the Price Limit of Searched Product
    When User Searches for a "<productName>"
    Then Searched Product "<productName>" list displayed in UI
    And Verify the Returned product amount with "<price_limit>"

    Examples:
      | productName | price_limit |
      | Shirt       | 5000         |
      | Bag        | 5000        |