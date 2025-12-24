package api.test;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utilities.ReportManager;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.*;


@Listeners(ReportManager.class)
public class UserTest3 {
	
	@Test(priority=1)
	public void test1() 
	{
		ReportManager.test.info("Test 1 info log 1.");
		ReportManager.test.log(Status.INFO,"Test 1 info log 2.");
		Assert.assertTrue(true);
	}
	
	@Test(priority=2)
	public void test2() 
	{
		ReportManager.test.info("Test 2 info log 1.");
		Assert.assertTrue(false);
	}	
	
	@Test(priority=3)
	public void test3() 
	{
		ReportManager.test.info("Test 3 info log 1..");
		Assert.assertTrue(true);
	}
	
}
