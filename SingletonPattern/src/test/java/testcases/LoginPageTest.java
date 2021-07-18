package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;



/********************************** Singleton Pattern ***********************************
 * 
 * In singleton pattern we need to initialize the webdriver instance just once.
 * If the driver is initialized once it will never come inside the if(driver == null) condition.
 * After quitting the browser in quit() the driver is again initialized to null.
 * 
 */


public class LoginPageTest {
	
	static LoginPage loginPage;	
	
	@BeforeMethod
	public void setup() {
		
		new TestBase();
		TestBase.initialization();
		loginPage= new LoginPage();
		
		TestBase.driver.get("https://www.screener.in/login/");
		
	}
	
	@Test(priority=1, description="verifying title of the page")
	public void loginPageTitleTest() {
		
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Login - Screener", "Title does not match");
		
	}
	
	@Test(priority=2, description="screener image logo test")
	public void screenerImageLogoTest() {
		
		boolean flag = loginPage.validateScreenerLogo();
		Assert.assertTrue(flag, "Screener logo on login page is not visible.");
				
	}
	
	
	@Test(priority=3, description="Logging into the acount")
	public void loginTest() throws InterruptedException {
		
		loginPage.login(TestBase.prop.getProperty("username"), TestBase.prop.getProperty("password"));
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		TestBase.quit();
		
	}

}