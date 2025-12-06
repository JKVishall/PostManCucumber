Feature: Validating Place APIs

  Scenario: Verify if place is being added via AddPlaceAPI
    Given user adds Place payload
    When user calls AddPlaceAPI with post http request
    Then gets statusCode as 200 in response body
    And gets "status" as "OK" in response body
    And gets "scope" as "APP" in response body


