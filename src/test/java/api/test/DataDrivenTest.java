package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.UserPOJO;
import api.utilities.DataProviderClass;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority = 1, dataProvider = "userData", dataProviderClass = DataProviderClass.class)
	public void testCreateUser(String name, String job) {
		UserPOJO bodyData = new UserPOJO();

		bodyData.setName(name);
		bodyData.setJob(job);
		Response response = UserEndpoints.createUser(bodyData);

		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);

	}

	@Test(priority = 2, dataProvider = "userNames", dataProviderClass = DataProviderClass.class)
	public void testCreateUser(String name) {
		UserPOJO bodyData = new UserPOJO();

		bodyData.setName(name);
		bodyData.setJob("Leader");
		Response response = UserEndpoints.createUser(bodyData);
		response.then().log().all();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);

	}

}
