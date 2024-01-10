package api.test;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.UserPOJO;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	UserPOJO bodyData;
	static String Usrid;
	
	
	@BeforeClass
	public void bodySetupdata()
	{
		faker = new Faker();
		bodyData = new UserPOJO();
		
		bodyData.setName(faker.name().fullName());
		bodyData.setJob(faker.job().position());
	}

	@Test
	public void testCreateUser()
	{
		Response response = UserEndpoints.createUser(bodyData);
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);
	
	}
	
	@Test
	public void testGETUser()
	{
		Response response = UserEndpoints.getUser("2");
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}
	
	//@Test
	public void testUpdateUser()
	{
		Response response = UserEndpoints.updateUser("2");
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}
	
	
}
