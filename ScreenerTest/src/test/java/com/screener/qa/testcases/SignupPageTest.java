package com.screener.qa.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.screener.qa.base.TestBase;
import com.screener.qa.pages.SignupPage;

public class SignupPageTest extends TestBase {
	
	//Initialize Log4j instance
    private static final Logger log =  LogManager.getLogger(SignupPageTest.class);
	
	SignupPage signupPage;
	
	public SignupPageTest() {
		
		super();
		
	}
	
	@BeforeMethod
	public void setup() {
		
		initialization();
		driver.navigate().to(prop.getProperty("register_url"));
		signupPage = new SignupPage();
		
	}
	
	@Test(priority=1)
	public void signupPageTitleTest() {
		
		log.info("Signup page title test started");
		String title = signupPage.validateSignupPageTitle();
		Assert.assertEquals(title, "Register - Screener", "Register page Title does not match");
		log.info("Signup page title test ended");
		
	}
	
	@Test(priority=2)
	public void fillCreateAccountFormTest() {
		
		log.info("Signup page fill create account form test started");
		signupPage.fillCreateAccountForm(prop.getProperty("email"), prop.getProperty("password"));
		log.info("Signup page fill create account form test ended");
		
	}
	
	
	@Test(priority=3)
	public void validateCreateAccountBtnTextTest() {
		
		log.info("Signup page create account button text test started");
		signupPage.validateCreateAccountBtnText();
		log.info("Signup page create account button text test ended");
		
	}
	
	@Test(priority=4)
	public void searchCompanyFieldEnabledTest() {
		
		log.info("Signup page search company field enabled test started");
		Assert.assertTrue(signupPage.searchCompanyFieldEnabled());
		log.info("Signup page search company field enabled test ended");
		
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
