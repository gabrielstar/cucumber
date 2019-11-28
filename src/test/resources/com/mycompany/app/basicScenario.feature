Feature: First Feature

  @hello @XRAY-1 @XRAY-18
  Scenario: First Hello
    Given I have "first" task
    And Step from "First Hello" in "First Feature" feature file
    When I attempt to solve it
    Then I surely succeed