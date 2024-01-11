package api.endpoints;

import java.util.ResourceBundle;

import org.testng.ITestContext;

import api.payload.UserPOJO;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserEndpoints_Using_PropertiesFile {
	
	public static RequestSpecification httpRequest;
	
	//this method creted URL from .properties file
	public static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");  //load properties file routes.properties
		return routes;
	}
	public static Response createUser(UserPOJO bodyData)
	{
		String postURL=getURL().getString("postURL");
		RestAssured.baseURI=postURL;
		httpRequest = RestAssured.given();
		
		httpRequest.body(bodyData.toString());
		Response response = httpRequest.request(Method.POST);
		
		return response;
	}
	
	public static Response getUser(String id)
	{
		String getURL=getURL().getString("getURL");
		RestAssured.baseURI=getURL;
		httpRequest = RestAssured.given();
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.pathParam("userID", id);

		Response response = httpRequest.request(Method.GET,"/{userID}");
		
		return response;
	}
	
	public static Response updateUser(String id, UserPOJO bodyData)
	{
		String putURL=getURL().getString("putURL");

		RestAssured.baseURI=putURL;
		httpRequest = RestAssured.given();
		
		httpRequest.pathParam("id", id);
		
		httpRequest.body(bodyData.toString());
		Response response = httpRequest.request(Method.PUT,"/{id}");
		
		
		
		return response;
	}
	public static Response deleteUser(String id)
	{
		String deleteURL=getURL().getString("deleteURL");
		RestAssured.baseURI=deleteURL;
		httpRequest = RestAssured.given();
		
		httpRequest.pathParam("id", id);
		
		Response response = httpRequest.request(Method.DELETE,"/{id}");
		
		return response;
	}

}
