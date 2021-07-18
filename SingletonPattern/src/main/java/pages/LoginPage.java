package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;


/********************************** Singleton Pattern ***********************************
 * 
 * In singleton pattern we need to initialize the webdriver instance just once.
 * If the driver is initialized once it will never come inside the if(driver == null) condition.
 * After quitting the browser in quit() the driver is again initialized to null.
 * 
 */


public class LoginPage {
	
	// Page Factory or Object Repository
	
	@FindBy(id="id_username")
	@CacheLookup
	WebElement username;
		
	@FindBy(id="id_password")
	@CacheLookup
	WebElement password;
		
	@FindBy(xpath="//button[@type='submit']")
	@CacheLookup
	WebElement loginBtn;
		
	@FindBy(xpath="//a[@class='logo-holder']//child::img[@class='logo' and @alt='Screener Logo']")
	WebElement screenerLogo;
		
	// Initializing the page objects
	public LoginPage() {
		PageFactory.initElements(TestBase.driver, this);
	}
		
	// Actions
	public String validateLoginPageTitle() {
			
		return TestBase.driver.getTitle();
			
		}
	
	public boolean validateScreenerLogo() {
			
		return screenerLogo.isDisplayed();
			
	}
	
	public void login(String un, String pwd) throws InterruptedException {
			
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		Thread.sleep(1000);
			
	}

}
