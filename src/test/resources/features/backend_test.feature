Feature: Validate the posts response

  @smoke @testcase01
  Scenario: Send a GET request to determine the expected number of posts
    Given A collection of posts uri endpoint
    When I send the request to retrieve the posts
    Then the status code is 200
    And the number of posts that should be included in the responses is 100

  @smoke @testcase02
  Scenario: Send a GET request to check whether there are multiple posts
    Given A collection of posts uri endpoint
    When I send the request to retrieve the posts
    Then the status code is 200
    And the total number of posts in response
      | greater than zero |
      | less than 101     |

  @smoke @testcase03
  Scenario: Make a request to get the posts with non-existent uri
    Given A collection of posts uri endpoint
    When I send the request to retrieve the posts with uri that doesn't exist
    Then the status code is 404
    And the description is "Not Found"
    And the response should include empty "{}"

  @smoke @testcase04
  Scenario: Send a GET request for the specific userID
    Given A collection of posts uri endpoint
    When I send the request to retrieve the posts for the userID 1
    Then the status code is 200
    And the number of posts should include posts greater than zero

  @smoke @testcase05
  Scenario: Send a GET request with a userID that does not exist in the database
    Given A collection of posts uri endpoint
    When I send the request to retrieve the posts for the userID 77
    Then the status code is 200
    And the response should include empty "[]"

  @smoke @testcase06
  Scenario: Make a request to retrieve the posts to validate each post's parameter
    Given A collection of posts uri endpoint
    When I send the request to retrieve the posts
    Then the status code is 200
    And the number of posts should include posts greater than zero
    And validate the post contains below information
      | userId |
      | id     |
      | title  |
      | body   |
