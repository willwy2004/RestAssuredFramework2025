package api.test;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.*;
import org.testng.Assert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Epic("Web app project") 
@Feature("User account management basic test") 
public class UserTests2 {
	
	//this test class is for properties file/for Jenkins project/CICD/Github repository
	//change for poll scm test
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
	
	
	@Tag("smoke")
	@Owner("Adam")
    @Story("Create User Test")
	@Description("User can be created")
    @Severity(SeverityLevel.CRITICAL)
	@Test(description = "Create User Test", priority=1)
	public void testPostUser() 
	{
		logger.info("-----------------Creating User---------------");
		
		Response res=UserEndPoints2.createUser(userPayload);
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(),200);
		
		logger.info("-----------------User is created---------------");
	}
	
	@Tag("regression")
	@Owner("Will")
    @Story("Get User by Name Test")
	@Description("User profile can be got by name")
    @Severity(SeverityLevel.CRITICAL)
	@Test(description = "Get User by Name Test",priority=2)
	public void testGetUserByName() 
	{
		logger.info("-----------------Reading User Info---------------");
		
		Response res=UserEndPoints2.readUser(this.userPayload.getUsername());
		
		res.then().log().all();
		
		Assert.assertEquals(res.statusCode(),200);
		
		logger.info("-----------------Reading User Info displayed---------------");
	}
	
	
	@Tag("regression")
	@Owner("Johnny")
    @Story("Update User by Name Test")
	@Description("User details can be updated")
    @Severity(SeverityLevel.CRITICAL)
	@Test(description = "Update User Test", priority=3)
	public void testUpdateUserByUsername() 
	{
		logger.info("-----------------Updating User Info---------------");
		//change
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());

		Response res=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		res.then().log().body();
		Assert.assertEquals(res.statusCode(),200);  //verify update successfully
		
		logger.info("-----------------User Info updated---------------");
		
		//check data after update
        Response resAfterUpdate=UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(resAfterUpdate.statusCode(),200);

	}
	
	@Tag("smoke")
	@Owner("Will")
    @Story("Delete User Test")
	@Description("User can be deleted")
    @Severity(SeverityLevel.CRITICAL)
	@Test(description = "Delete User Test", priority=4)
	public void testDeleteUserByUsername() 
	{
		logger.info("-----------------Deleting User---------------");
		
        Response res=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(res.statusCode(),200);
		
		logger.info("-----------------User deleted---------------");
	}

	
	
	
	
	
}
