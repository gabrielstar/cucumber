Feature: Background Feature

  #Here you repeat tests with different data
  Background: I have a job
    Given The job is nice
    And I get paid

  Scenario Outline: <which> Hello
    Given I have "<what>" task
    And Step from "<which> Hello" in "Background Feature" feature file
    When I attempt to solve it
    Then I surely succeed

    Examples:
      | what | which  |
      | easy | first  |
      | hard | second |


