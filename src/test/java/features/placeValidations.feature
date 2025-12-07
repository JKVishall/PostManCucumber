Feature: Validating Place APIs

  Scenario Outline: Verify if place is being added via AddPlaceAPI
    Given user adds Place payload with "<name>" "<phone_number>" "<address>"
    When user calls "addPlaceAPI" with post http request
    Then gets statusCode as 200 in response body
    And gets "status" as "OK" in response body
    And gets "scope" as "APP" in response body
    And user calls Get http request to verify if place_id response name matches post request "<name>" using "getPlaceAPI"
    Examples:
      | name         | phone_number | address        |
      | John michael | 9992929229   | 320 adyar lane |
  #    | Mike Fannman | 8282828282   | 420 Adyar lane |




