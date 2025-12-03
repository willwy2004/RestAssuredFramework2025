package api.test;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.annotations.*;
import org.testng.Assert;
import io.restassured.matcher.RestAssuredMatchers;
import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class UserTests {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() 
	{
		
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());  //generate an id at ramdom
		
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//initialise the logger before test class 
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser() 
	{
		logger.info("-----------------Creating User---------------");
		
		Response res=UserEndPoints.createUser(userPayload);
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(),200);
		
		logger.info("-----------------User is created---------------");
	}
	
	
	@Test(priority=2)
	public void testGetUserByName() 
	{
		logger.info("-----------------Reading User Info---------------");
		
		Response res=UserEndPoints.readUser(this.userPayload.getUsername());
		
		res.then().log().all();
		
		Assert.assertEquals(res.statusCode(),200);
		
		logger.info("-----------------Reading User Info displayed---------------");
	}
	
	@Test(priority=3)
	public void testUpdateUserByUsername() 
	{
		logger.info("-----------------Updating User Info---------------");
		//change
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());

		Response res=UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		res.then().log().body();
		Assert.assertEquals(res.statusCode(),200);  //verify update successfully
		
		logger.info("-----------------User Info updated---------------");
		
		//check data after update
        Response resAfterUpdate=UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(resAfterUpdate.statusCode(),200);

	}
	
	
	@Test(priority=4)
	public void testDeleteUserByUsername() 
	{
		logger.info("-----------------Deleting User---------------");
		
        Response res=UserEndPoints.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(res.statusCode(),200);
		
		logger.info("-----------------User deleted---------------");
	}

	
	
	
	
	
}
