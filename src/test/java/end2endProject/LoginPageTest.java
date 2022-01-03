package end2endProject;

import java.io.IOException;
import java.util.logging.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import pageObject.HomePagePO;
import pageObject.LoginPagePO;

public class LoginPageTest extends BaseConfiguration {

	public static Logger log = org.apache.logging.log4j.LogManager.getLogger(Base.class.getClass());
	WebDriver driver;
	
	@BeforeMethod
	public void login() throws IOException {

		log.info(">>>> We are in the test case : login()");
		driver = initBrowser();
		log.info(">>>> Driver Initialized");
		url = prop.getProperty("url");
		log.info(">>>> Url details retrived from property file");
		driver.get(url);
		log.info(">>>> URL opened in the browser");
		
		log.info("Made changes 2");
		log.info("Made changes 3");
		log.info("Made changes 4");
	}

	@Test
	public void successLoginTest() throws IOException {

		// call the pageobject class to get the elements
		LoginPagePO pageLogin = new LoginPagePO(driver);
		HomePagePO home = new HomePagePO(driver);
		
		String uname = prop.getProperty("userid");
		String pass = prop.getProperty("password");
		
		pageLogin.getUserId().sendKeys(uname);
		log.info(">>>> Userid entered");
		pageLogin.getPassword().sendKeys(pass);
		log.info(">>>> Password entered");
		pageLogin.getLogin().click();
		log.info(">>>> Login button clicked");

		Assert.assertFalse(home.getHeading().isDisplayed());
		
		
	}

	/*@DataProvider
	public Object[][] testData() throws IOException {

		Object[][] testData = new Object[2][2];

		testData[0][0] = (String) prop.getProperty("userid");
		testData[0][1] = (String) prop.getProperty("password");

		testData[1][0] = (String) prop.getProperty("userid");
		testData[1][1] = (String) prop.getProperty("wrongPassword");

		return testData;

	}*/

	@AfterMethod
	public void tearDown() {
		driver.close();
		log.info(">>>> Browser closed");
	}
}
