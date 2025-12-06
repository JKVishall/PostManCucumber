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
import resources.AddPlaceRequestBody;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PlaceValidationsStepDef extends AddPlaceRequestBody {

    RequestSpecification reqSpec;
    ResponseSpecification responseSpec;
    Response response;

    @Given("user adds Place payload")
    public void addPlacePayload(){

        //now, instead of the body, we can send the bodyData object and it will serialize it into the expected Json on its own

        //Doing RequestSpecBuilder
        //This will include the baseURI and all other things we give in given() except .body
        //we need to create a new object of RequestSpecBuilder
        //need to give .build() for it to build it to the specBuilder type
        //given()
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON)
                .build();
        reqSpec = given().spec(requestSpec).body(addPlaceReqBody()).log().body();
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }
    @When("user calls AddPlaceAPI with post http request")
    public void addPlaceAPICall() {
        response = reqSpec.when().post("maps/api/place/add/json").then().log().status().log().body().spec(responseSpec).extract().response();

        System.out.println("Response Body: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Content-Type: " + response.getContentType());

    }
    @Then("gets statusCode as {int} in response body")
    public void getsStatusCodeAsInResponseBody(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
        System.out.println(response.statusCode());
    }

    @And("gets {string} as {string} in response body")
    public void getsAsInResponseBody(String actualResult, String expectedResult) {
        String addPlaceResponse = response.asString();
        System.out.println(addPlaceResponse);
        JsonPath js = new JsonPath(addPlaceResponse);
        Assert.assertEquals(js.getString(actualResult),expectedResult);
    }

}
