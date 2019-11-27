Feature: Data Tables Feature
  #here you pack data into a complex structure as test argument
  @regression
  Scenario: First Hellol
    Given I have "first" task
    And Step from "First Hello" in "Data Tables Feature" feature file
    When I attempt to solve it
    Then I surely receive a prize
      | Name          | Value | Rank |
      | Cinema ticket | 100   | 1    |
      | Voucher       | 200   | 2    |