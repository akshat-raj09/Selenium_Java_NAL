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
	public void signupPageTitleTest(Method method) {
		
		log.info("Signup page title test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		test.log(Status.INFO, "Fetched title of the webpage");
		
		String title = signupPage.validateSignupPageTitle();
		Assert.assertEquals(title, "Register - Screener", "Register page Title does not match");
		
		log.info("Signup page title test ended");
		
	}
	
	@Test(priority=2)
	public void fillCreateAccountFormTest(Method method) {
		
		log.info("Signup page fill create account form test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		test.log(Status.INFO, "filled email & password on the signup webpage");
		
		signupPage.fillCreateAccountForm(prop.getProperty("email"), prop.getProperty("password"));
		
		log.info("Signup page fill create account form test ended");
		
	}
	
	
	@Test(priority=3)
	public void validateCreateAccountBtnTextTest(Method method) {
		
		log.info("Signup page create account button text test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		test.log(Status.INFO, "Fetched create account button text");
		
		signupPage.validateCreateAccountBtnText();
		
		log.info("Signup page create account button text test ended");
		
	}
	
	@Test(priority=4)
	public void searchCompanyFieldEnabledTest(Method method) {
		
		log.info("Signup page search company field enabled test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		test.log(Status.INFO, "checking if the company search field is enabled or not");
		
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
