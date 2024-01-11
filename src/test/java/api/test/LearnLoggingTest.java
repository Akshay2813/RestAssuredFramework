package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.UserPOJO;
import io.restassured.response.Response;

public class LearnLoggingTest {
	
	Faker faker;
	UserPOJO bodyData;
	static String Usrid;
	
	public Logger logger;
	@BeforeClass
	public void bodySetupdata()
	{
		faker = new Faker();
		bodyData = new UserPOJO();
		
		bodyData.setName(faker.name().fullName());
		bodyData.setJob(faker.job().position());
		logger=LogManager.getLogger(this.getClass());
	}

	@Test(priority=1)
	public void testCreateUser()
	{
		logger.info("=====CREATING USER========");
		Response response = UserEndpoints.createUser(bodyData);
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);
		
		logger.info("=====USER SUCESSFULLY CREATED========");

	
	}

}
