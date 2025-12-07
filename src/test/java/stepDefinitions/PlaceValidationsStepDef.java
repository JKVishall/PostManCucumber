package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.LocationInfo;
import pojo.WebsiteDetails;
import resources.APIResources;
import resources.AddPlaceRequestBody;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PlaceValidationsStepDef extends Utils {

    RequestSpecification reqSpec;
    ResponseSpecification responseSpec;
    Response response;

    AddPlaceRequestBody body =new AddPlaceRequestBody();

    String place_id;

    @Given("user adds Place payload with {string} {string} {string}")
    public void addPlacePayload(String name, String phone_number, String address) throws IOException {
        //now, instead of the body, we can send the bodyData object and it will serialize it into the expected Json on its own
        reqSpec = given().spec(requestSpecification()).body(body.addPlaceReqBody(name, phone_number, address));

    }
    @When("user calls {string} with post http request")
    public void placeAPICall(String resource) {
         response = reqSpec.when().post(APIResources.endPointOf(resource));
    }
    @Then("gets statusCode as {int} in response body")
    public void getsStatusCodeAsInResponseBody(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
        System.out.println(response.statusCode());
    }

    @And("gets {string} as {string} in response body")
    public void getsAsInResponseBody(String actualResult, String expectedResult) {

        Assert.assertEquals(jsonPath(response, actualResult),expectedResult);
    }

    @And("user calls Get http request to verify if place_id response name matches post request {string} using {string}")
    public void userCallsGetHttpRequestToVerifyIfPlace_idResponseNameMatchesPostRequestUsing(String arg0, String resource) throws IOException {

        place_id = jsonPath(response, "place_id");
        reqSpec = given().spec(requestSpecification()).queryParam("place_id", place_id);
        response = reqSpec.get(APIResources.endPointOf(resource));
        String getResponse = response.asString();
        JsonPath js = new JsonPath(getResponse);
        System.out.println(js.getString("name"));
    }
}
