package api.test;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints_Using_PropertiesFile;
import api.payload.UserPOJO;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class UserTest_UsingPropertiesFile {
	
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

	@Test(priority=1)
	public void testCreateUser()
	{
		Response response = UserEndpoints_Using_PropertiesFile.createUser(bodyData);
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);
	
	}
	
	@Test(priority=2)
	public void testGETUser()
	{
		Response response = UserEndpoints_Using_PropertiesFile.getUser("2");
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		bodyData.setName(faker.name().fullName());
		bodyData.setJob(faker.job().position());
		Response response = UserEndpoints_Using_PropertiesFile.updateUser("2",bodyData);
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		
		Response response = UserEndpoints_Using_PropertiesFile.deleteUser("2");
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(204, statusCode);
	}
	
	
}
