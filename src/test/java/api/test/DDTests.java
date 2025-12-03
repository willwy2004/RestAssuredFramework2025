package api.test;
import org.testng.annotations.*;

import api.utilities.DataProviders;
import io.restassured.response.Response;

import org.testng.Assert;

import api.endpoints.UserEndPoints;
import api.payload.User;

public class DDTests {
	//this is Data-Driven Tests
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)  //indicate which class file and which method as data provider
	public void testPostuser(String userID,String userName,String fname, String lname,String useremail,String pwd,String ph) 
	{
		User userPayload=new User();
		
        userPayload.setId(Integer.parseInt(userID));  //generate an id at ramdom
		
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		
		Response res=UserEndPoints.createUser(userPayload);
		Assert.assertEquals(res.getStatusCode(), 200);		
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)  //indicate which class file and which method as data provider
	public void testDeleteUserByName(String userName) 
	{
		Response res=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);		
	}
	
	

}
