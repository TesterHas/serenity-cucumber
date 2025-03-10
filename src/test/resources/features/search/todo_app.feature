Feature: Test Cases for toDo App

  @green
  Scenario: Verify that a new todo can be added with valid input.
    Given Hassan is on the Todo App
    When he adds a new task "test"
    Then he should see the task "test" added to the list

