package api.endpoints;

import org.testng.ITestContext;

import api.payload.UserPOJO;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserEndpoints {
	
	public static RequestSpecification httpRequest;
	
	public static Response createUser(UserPOJO bodyData)
	{
		RestAssured.baseURI=Routes.postURL;
		httpRequest = RestAssured.given();
		
		httpRequest.body(bodyData.toString());
		Response response = httpRequest.request(Method.POST);
		
		return response;
	}
	
	public static Response getUser(String id)
	{
		RestAssured.baseURI=Routes.getURL;
		httpRequest = RestAssured.given();
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.pathParam("userID", id);

		Response response = httpRequest.request(Method.GET,"/{userID}");
		
		return response;
	}
	
	public static Response updateUser(String id)
	{
		RestAssured.baseURI=Routes.putURL;
		httpRequest = RestAssured.given();
		
		httpRequest.pathParam("id", id);
		
		Response response = httpRequest.request(Method.PUT,"/{id}");
		
		
		
		return response;
	}
	public static Response deleteUser(String id)
	{
		RestAssured.baseURI=Routes.deleteURL;
		httpRequest = RestAssured.given();
		
		httpRequest.pathParam("id", id);
		
		Response response = httpRequest.request(Method.DELETE,"/{id}");
		
		return response;
	}

}
