package com.screener.qa.testcases;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.screener.qa.base.TestBase;
import com.screener.qa.pages.HomePage;
import com.screener.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	//Initialize Log4j instance
    private static final Logger log =  LogManager.getLogger(LoginPageTest.class);
        
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() {
		
		super();
		
	}
	
	@BeforeMethod
	public void setup() {
		
		initialization();
		loginPage= new LoginPage();
		
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(Method method) {
		
		log.info("login page title test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		
		String title = loginPage.validateLoginPageTitle();
		test.log(Status.INFO, "Fetched Title of webpage");
		Assert.assertEquals(title, "Login - Screener", "Title does not match");
		
		log.info("Login page title test ended");
		
	}
	
	@Test(priority=2)
	public void screenerImageLogoTest(Method method) {
		
		log.info("Login page logo test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		
		boolean flag = loginPage.validateScreenerLogo();
		test.log(Status.INFO, "Fetched status of the screener logo on the webpage");
		Assert.assertTrue(flag, "Screener logo on login page is not visible.");
		
		log.info("login page logo test ended");
		
	}
	
	
	@Test(priority=3)
	public void loginTest(Method method) {
		
		test.log(Status.INFO, method.getName() + ": Test is starting");
		
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		log.info("logged into website");
		test.log(Status.INFO, "Loggedin to the website");
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		try {
			Thread.sleep(2000);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();
		log.info("Browser closed");
		
	}

}
