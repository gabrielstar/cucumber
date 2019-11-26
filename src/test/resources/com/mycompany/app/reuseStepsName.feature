Feature: Re-use Steps Feature

  Scenario: First Hello
    Given I have first task
    And Step from "First Hello" in "Re-use Steps Feature" feature file
    When I attempt to solve it
    Then I surely succeed