package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager implements ITestListener{
	
	//Define test report saving path, report them, report style
	private ExtentSparkReporter sparkReporter;  
	
	//Define the report core manager
	private ExtentReports extent;
	
	//Define test report for each test node
	public static ExtentTest test;
	
	//report name for with dynamic test date
	private String repName; 
	
	//Invoked before running all the test methods belonging to the classes
	public void onStart(ITestContext context) 
	{

	        System.out.println("---------Configuration of test report started----------");
	        
	        String timeStamp=new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
			repName="Test-Report-"+timeStamp+".html";		

			sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
			
			sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
			sparkReporter.config().setReportName("Pet Store Users API");
			sparkReporter.config().setTheme(Theme.DARK);
			
	        extent=new ExtentReports();
			
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Pest Store Users API");
			extent.setSystemInfo("Tester", "Will Wang");
			extent.setSystemInfo("Environment", "QA");
	
	}
	
	//Invoked after having ran all the test methods belonging to the classes
	public void onFinish(ITestContext context) 
	{

		if(extent!=null) {
			extent.flush();
		}
	}
		
		
	//invoke this method before each test
	public void onTestStart(ITestResult result) 
	{
		
		test=extent.createTest(result.getTestName());
		
		test.log(Status.INFO, "Test is started !");
	}


	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.INFO, "This is the test result "+result.getStatus());
		test.log(Status.PASS, "Test succeeded !");
		
	}


	public void onTestFailure(ITestResult result) 
	{
		test.log(Status.INFO, "This is the test result "+result.getStatus());
		test.log(Status.FAIL, "Test failed !");
	}

	public void onTestSkipped(ITestResult result) 
	{
		test.log(Status.INFO, "Test result has 3 options: 1=success,2=fail,3=skipped ");
		test.log(Status.INFO, "This is the test result "+result.getStatus());
		test.log(Status.SKIP, "Test skipped !");

	}



	
	

}
