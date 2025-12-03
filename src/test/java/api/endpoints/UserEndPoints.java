package api.endpoints;
import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.response.Response;

import io.restassured.http.ContentType;


//User End Points.java
//Created for perform Create, Read, Update, Delete requests to the user APIs


public class UserEndPoints {
	
	
	public static Response createUser(User payload){
		
		Response res=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)   //show the expected api response type
		  .body(payload)
		.when()
		  .post(Routes.post_url);

		return res;
	}
	
	
    public static Response readUser(String userName){
		
    	//System.out.println("Final URL: " + Routes.get_url.replace("{username}", userName));
    	
		Response res=given()
		  .pathParam("username", userName)
		.when()
		  .get(Routes.get_url);

		return res;
	}

	
    public static Response updateUser(String userName,User payload){
		
		Response res=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)   //show the expected api response type
		  .pathParam("username", userName)
		  .body(payload)
		.when()
		  .put(Routes.update_url);

		return res;
	}
    
    public static Response deleteUser(String userName){
		
		Response res=given()
		  .pathParam("username", userName)
		.when()
		  .delete(Routes.delete_url);

		return res;
	}


}
