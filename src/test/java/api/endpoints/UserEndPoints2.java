package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.response.Response;

import io.restassured.http.ContentType;


//this class is reading out the api url from property file
//Created for perform Create, Read, Update, Delete requests to the user APIs


public class UserEndPoints2 {
	
	//method created for getting url from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");  //load property file
		
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		
		
		
		Response res=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)   //show the expected api response type
		  .body(payload)
		.when()
		  .post(post_url);

		return res;
	}
	
	
    public static Response readUser(String userName)
    {

    	String get_url=getURL().getString("get_url");
    	
		Response res=given()
		  .pathParam("username", userName)
		.when()
		  .get(Routes.get_url);

		return res;
	}

	
    public static Response updateUser(String userName,User payload)
    {
		
    	String update_url=getURL().getString("update_url");
    	
		Response res=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)   //show the expected api response type
		  .pathParam("username", userName)
		  .body(payload)
		.when()
		  .put(Routes.update_url);

		return res;
	}
    
    public static Response deleteUser(String userName)
    {
    	String delete_url=getURL().getString("delete_url");
    	
		Response res=given()
		  .pathParam("username", userName)
		.when()
		  .delete(delete_url);

		return res;
	}


}
