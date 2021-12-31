package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePagePO {

	WebDriver driver;
	public HomePagePO(WebDriver driver) {
		this.driver=driver;
	}
	
	By homeHeading = By.cssSelector("div#site-name");
	By newCustomer = By.cssSelector("li a[href='addcustomerpage.php']");
	
	public WebElement getHeading() {
		return driver.findElement(homeHeading);
		
	}
	
	public WebElement getNewCustomer() {
		return driver.findElement(newCustomer);
	}
}
