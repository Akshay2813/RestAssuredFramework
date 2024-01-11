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



public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;  //used for look and field
	public ExtentReports extent;  //specify common information
	public ExtentTest test;  //creating entries for pass  failure skipping TC
	
	String repName;
	public void onStart(ITestContext testContext)
	{
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName="Test-Report-"+timeStamp+".html";  // to get unique name for reach report file
		
		//Specify location
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+repName);
		
		//specify Title
		sparkReporter.config().setDocumentTitle("RestAssuredFrameworkProjectReportDocumentTilrle");
		//specify name of report
		sparkReporter.config().setReportName("RestAssuredFrameworkProjectReport--ReportName");
		//specify theme
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application_SystemInfo", "user_API_system_Info");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Enviornment","QA");
		extent.setSystemInfo("User", "Akshay Shelke");
		
	}
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		
	}
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	


}
