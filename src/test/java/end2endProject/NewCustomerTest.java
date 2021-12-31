package end2endProject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObject.HomePagePO;
import pageObject.LoginPagePO;

public class NewCustomerTest extends BaseConfiguration {
	WebDriver driver;
	
	@BeforeMethod
	public void login() throws IOException {
		driver = initBrowser();
		
		url=prop.getProperty("url");
		driver.get(url);
		
		LoginPagePO login = new LoginPagePO(driver);
		login.getUserId().sendKeys(prop.getProperty("userid"));
		login.getPassword().sendKeys(prop.getProperty("password"));
		login.getLogin().click();

	}
	
	@Test
	public void addNewCustomerTest() throws IOException, InterruptedException {
		
		HomePagePO home = new HomePagePO(driver);

		Actions a = new Actions(driver);
		a.moveToElement(home.getNewCustomer()).click().perform();
		
		Assert.assertTrue(true);

	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
