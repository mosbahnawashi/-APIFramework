package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

public class StepDefination extends Utils {

	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {

		APIResources resourceAPI = APIResources.valueOf(resource);

		System.out.println(resourceAPI.getResource());

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (httpMethod.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (httpMethod.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());

		// response =
		// res.when().post(resourceAPI.getResource()).then().spec(resSpec).extract().response();
		// Try the following line once with the chenge in the URL to fail the test and
		// check it in the Logging file.
		// response =
		// res.when().post("/maps/api/placePLACE/add/json").then().spec(resSpec).extract().response();
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		// You have to import the following package manually: import static
		// org.junit.Assert.*;
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertEquals(getJsonPath(response, key), value);
	}

	@Then("verify Place_id created maps to {string} using {string}")
	public void verify_Place_id_created_maps_to_using(String expected_Name, String resourse) throws IOException {
		// Prepare request spec:
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resourse, "GET");
		String actual_Name = getJsonPath(response, "name");
		assertEquals(actual_Name, expected_Name);
		System.out.println(expected_Name);
	}

	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}
