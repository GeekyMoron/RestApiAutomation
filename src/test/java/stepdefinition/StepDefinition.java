package stepdefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;
import groovyjarjarantlr4.v4.codegen.model.Action;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.GoogleAPIPojo;
import pojo.Gorestinput;
import pojo.LocationPOJO;
import resources.ApiResourses;
import resources.TestData;
import resources.Utils;

public class StepDefinition extends Utils{
	RequestSpecification req;
	Response resp;
	ResponseSpecification response;
	static String place_id;
	public static String userid=null;
	TestData t=new TestData();
	@Given("Add Place PayLoad {string} {string} {string}")
	public void add_Place_PayLoad(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		//requestSpecbuilder returns request specification object
		req=given().spec(requestSpecification()).body(t.AddPlacePayload(name,language,address));
	}

	@When("User calls {string} with {string} Http request")
	public void user_calls_with_Http_request(String resourse, String method) {
	    // Write code here that turns the phrase above into concrete actions
		 ApiResourses responseApi=ApiResourses.valueOf(resourse);//constructor will be called from value of function
		 System.out.println(method+":"+responseApi.getResource());
		response=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("Post"))
		resp=req.when()
				.post(responseApi.getResource()).then().spec(response).extract().response();
		else if(method.equalsIgnoreCase("Get"))
			resp=req.when()
			.get(responseApi.getResource()).then().spec(response).extract().response();
		else if(method.equalsIgnoreCase("Delete"))
			resp=req.when()
			.delete(responseApi.getResource()).then().spec(response).extract().response();
		else
			resp=req.when()
			.put(responseApi.getResource()).then().spec(response).extract().response();
	}

	@Then("The API call gets success with status code {string}")
	public void the_API_call_gets_success_with_status_code(String int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(resp.getStatusCode(),Integer.parseInt(int1));
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		String Status=getJsonpath(resp, key);
		assertEquals(Status,value);
	}
	@Then("User id is present in response")
	public void user_id_is_present_in_response() {
	    // Write code here that turns the phrase above into concrete actions
		userid=getJsonpath(resp,"result.id");
	}
	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String name, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    //prepare on request spec for getPlaceApi call
		place_id=getJsonpath(resp,"place_id");
		req=given().spec(req).queryParam("place_id",place_id);
		user_calls_with_Http_request(string2,"Get");
		String nm=getJsonpath(resp,"name");
		assertEquals(name,nm);
	}
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    req=given().spec(requestSpecification()).body(t.DeletePlacePayload(place_id));
	}
	@Given("Add user payload with {string} {string} {string} {string} {string}")
	public void add_user_payload_with(String email, String first_name, String gender, String last_name, String status) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    req=given().spec(gorestrequestSpecification()).body(t.gorestpayload(email, first_name, gender, last_name, status));
	}

	@Given("{string} user payload with {string} {string} {string} {string} {string} and userid")
	public void user_payload_with_and_userid(String method,String email, String first_name, String gender, String last_name, String status) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req=given().spec(gorestrequestSpecification()).body(t.gorestpayload(email, first_name, gender, last_name, status));
	}

	@When("User calls {string} with {string} Http request with user id as path parameter")
	public void user_calls_with_Http_request_with_user_id_as_path_parameter(String resourse, String method) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		if(userid!=null)
		{
			ApiResourses responseApi=ApiResourses.valueOf(resourse);//constructor will be called from value of function
			System.out.println(method+":"+responseApi.getResource()+userid);
			response=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			if(method.equalsIgnoreCase("Post"))
			resp=req.when()
					.post(responseApi.getResource()+userid).then().spec(response).extract().response();
			else if(method.equalsIgnoreCase("Get")) {
				resp=req.when()
				.get(responseApi.getResource()+userid).then().spec(response).extract().response();
			}
			else if(method.equalsIgnoreCase("Delete"))
				resp=req.when()
				.delete(responseApi.getResource()+userid).then().spec(response).extract().response();
			else
				resp=req.when()
				.put(responseApi.getResource()+userid).then().spec(response).extract().response();
		}
		else
		System.out.println("User id is null.....");
	}
	    
}
