package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static RequestSpecification requestSpec;

    public RequestSpecification requestSpecification() throws IOException {

        if(requestSpec==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));

            RestAssured.useRelaxedHTTPSValidation();
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(globalProp("baseURI"))
                    .addQueryParam("key", "qaclick123")
                    .setContentType(ContentType.JSON)
                    /* We are using this same requestSpecification in both Post and get calls
                    but in getCall we need only two query parameters instead of
                    one query parameter and contentType Json, it needs
                    two query parameters.
                    One of the query parameters is already declared here.
                    You would think how we can reuse this in get call, since this one already has
                    contentType declared. Thing is, the contentType doesn't mandatorily
                    require us to add .body, so we can call this same code for get api call
                    and add one more queryParameter on top of this and make it work
                     */
                    //both .addFilter methods are added to log the logs in logs.txt file
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return requestSpec;
        }
        return requestSpec;
    }

    public String globalProp(String valFromPropertyFile) throws IOException {
       Properties prop = new Properties();
       FileInputStream fis = new FileInputStream("C:\\Users\\UU489ZG\\OneDrive - EY\\Documents\\Automation\\APICucumberRestAssuredFinal\\APICucumberRestAssuredFinal\\src\\test\\java\\resources\\global.properties");
       prop.load(fis);
       return prop.getProperty(valFromPropertyFile);
    }

    public String jsonPath(Response response, String key){
        String responseOut = response.asString();
        JsonPath js = new JsonPath(responseOut);
        return js.getString(key);
    }
}
