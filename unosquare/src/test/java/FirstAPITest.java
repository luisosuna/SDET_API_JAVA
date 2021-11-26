
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import unosquare.ApiCore;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class FirstAPITest {
	
	ApiCore apiCore;
	
  @Test
  public void f() {
	  
		/*
		 * RestAssured.baseURI = "https://reqres.in/api/"; RequestSpecification
		 * httpRequest = RestAssured.given(); Response response =
		 * httpRequest.get("/users/2");
		 * 
		 * int statusCode = response.getStatusCode();
		 * 
		 * // Assert that correct status code is returned.
		 * Assert.assertEquals(statusCode,200); Reporter.log("Sucess 200 validation");
		 * 
		 * response.then().body("data.first_name", equalTo("Janet"));
		 * Reporter.log(response.body().asString());
		 */
  }
  
  @Test
  public void f_Gherkin() {
	  
	  given()
	  .when()
	  	.get("https://reqres.in/api/users/2")
	  		.then()
	  		.assertThat().statusCode(200)
	  		.assertThat().contentType(ContentType.JSON)
	  		.assertThat().body("data.'first_name'", equalTo("Janet"));
	  
	  Reporter.log("Sucess 200 validation");
}
  
  @Test
  public void PostCreate() {
	  
	  JSONObject requestParams = new JSONObject();
	  requestParams.put("name","JohnAPI");
	  requestParams.put("job","QA");
	  
		 RestAssured.baseURI = "https://reqres.in/api"; 
		 RequestSpecification httpRequest = RestAssured.given(); 
		 httpRequest.headers("Content-Type", "application/json");
		 httpRequest.body(requestParams.toString());
		 Response response = httpRequest.post("/users");
		 
		 
		 String body = response.getBody().asString();
		 int StatusCode = response.getStatusCode();	
		 Reporter.log(body);
}
  
  @Test
  public void PostRegister() throws IOException, ParseException {
	  
	  JSONParser json = new JSONParser();
	  FileReader reader = new FileReader(".\\Json\\Register.json");
	  Object obj = json.parse(reader);
	  
	  org.json.simple.JSONObject requestParams = (org.json.simple.JSONObject)obj;
	  
		 RestAssured.baseURI = "https://reqres.in/api"; 
		 RequestSpecification httpRequest = RestAssured.given(); 
		 httpRequest.headers("Content-Type", "application/json");
		 httpRequest.body(requestParams.toString());
		 Response response = httpRequest.post("/register");
		 
		 Reporter.log(RestAssured.baseURI + "/register");
		 String body = response.getBody().asString();
		 int StatusCode = response.getStatusCode();	
		 Reporter.log(String.valueOf(StatusCode));
		 Reporter.log(body);
		 Reporter.log("Body sent: " + requestParams.toString());
}
  
  @Test
  public void PostLogin() throws IOException, ParseException{
	  
	  Response test = apiCore.PostLogin(".\\Json\\Login.json","/login");
	  
	  Assert.assertEquals(200, test.statusCode());

}
  @BeforeSuite
  public void beforeSuite() {
	  
	  apiCore = new ApiCore();
	  
  }
  
  @BeforeMethod
  public void beforeMethod() {
  }

}
