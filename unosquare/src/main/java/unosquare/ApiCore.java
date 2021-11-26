package unosquare;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiCore {
	
	public Response PostLogin(String filePath, String url) throws IOException, ParseException {
		
		  JSONParser json = new JSONParser();
		  FileReader reader = new FileReader(filePath);
		  Object obj = json.parse(reader);
		  
		  org.json.simple.JSONObject requestParams = (org.json.simple.JSONObject)obj;
		  
			 RestAssured.baseURI = "https://reqres.in/api"; 
			 RequestSpecification httpRequest = RestAssured.given(); 
			 httpRequest.headers("Content-Type", "application/json");
			 httpRequest.body(requestParams.toString());
			 Response response = httpRequest.post(url);
			 
			 Reporter.log(RestAssured.baseURI + url);
			 Reporter.log("Body sent: " + requestParams.toString());
			 Reporter.log(String.valueOf(response.getStatusCode()));
			 Reporter.log(response.getBody().asString());
			 
			 return response; 
		
	}
}