package api.utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;



public class AllureReportStepHelper {

		@Step("Response body is added as attachment !")
		public static void addResponseBody(Response res) {	
			 if (res == null) return;
			  String body = res.asPrettyString(); 
			  Allure.addAttachment("Response body", "application/json", body, ".json");
		}
			

	    
	    @Attachment(value = "Screenshot", type = "image/png")
	    public static byte[] attachScreenshot(String imgPath) throws IOException {
	    	byte[] screenshot = Files.readAllBytes(Paths.get(imgPath));
	        return screenshot;
	    }

}
