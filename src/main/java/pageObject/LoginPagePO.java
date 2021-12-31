package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPagePO {

	WebDriver driver;
	public LoginPagePO(WebDriver driver) {
		this.driver=driver;
	}
	
	By userId= By.cssSelector("input[name='uid']");
	By password = By.cssSelector("input[type='password']");
	By login = By.cssSelector("input[value='LOGIN']");
	
	public WebElement getUserId() {
		return driver.findElement(userId);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getLogin() {
		return driver.findElement(login);
	}
	
}
