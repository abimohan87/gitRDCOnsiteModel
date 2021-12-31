package end2endProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseConfiguration {

	public WebDriver driver;
	public String currBrowser;
	public String url;
	Properties prop;
	ExtentReports extent;
	
	
	public WebDriver initBrowser() throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\main\\resources\\data.properties");

		prop.load(fis);
		currBrowser = prop.getProperty("browser");

		if (currBrowser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (currBrowser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (currBrowser.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver", "src\\main\\resources\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		return driver;
		
	}
	
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ss = (TakesScreenshot) driver;
		File sourceFile = ss.getScreenshotAs(OutputType.FILE);
		
		String destFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(sourceFile, new File(destFile));
		
		System.out.println(">>>> Screenshot taken");
		return destFile;
		
	}
	
	public ExtentReports reporterConfig() {
		
		String path = System.getProperty("user.dir")+"\\reports\\testReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Bank System Test");
		reporter.config().setDocumentTitle("Bank Automation Report");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Team Name", "Status Squad");
		extent.setSystemInfo("Release", "12.21");
	
		return extent;
	}
}
