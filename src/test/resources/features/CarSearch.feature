Feature: Car Search

  Background: Validate the landing page
    Given : User at the home page
    Then : User should see Motors link
    When : User selects the Motors tab
    Then : User landed at the car search tab
    Then : User should see the Make dropdown

@Positive
Scenario: Verify the number of named car makes available
 Given : User at the car search tab
 When : User selects the Make dropdown
 Then : Verify the number of available car makes

  @Positive
  Scenario: Verify the number of cars returned when search is based on particular make
    Given : User at the car search tab
    When : User selects the car make from Car Details Excel Sheet for <rownumber>
    And : Click on Search button
    Then : Cars are listed in the search list for <rownumber>
    Then : Verify the number of cars returned in the search list

    Examples:
      | rownumber |
      | 1         |
      | 2         |
      | 3         |
      | 4         |
