package end2endProject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

public class Listeners extends BaseConfiguration implements ITestListener {

	public static Logger log = LogManager.getLogger(Base.class.getClass());
	ExtentTest test ;
	ExtentReports extent = reporterConfig();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		log.info(">>>> Listener - Test Case Started - "+result.getMethod().getMethodName());
		String testCaseName=result.getMethod().getMethodName();

		test = extent.createTest(testCaseName, testCaseName+" executing..");
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		extentTest.get().pass("Test Case is Passed!! ");
	}

	public void onTestFailure(ITestResult result) {
		
		String testCase = result.getMethod().getMethodName();
		WebDriver driver = null;
		
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			
		}
		
		try {
			
			extentTest.get().addScreenCaptureFromPath(takeScreenshot(testCase, driver), result.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	
	
}
