package com.screener.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.screener.qa.base.TestBase;
import com.screener.qa.pages.SignupPage;

public class SignupPageTest extends TestBase {
	
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
		
		String title = signupPage.validateSignupPageTitle();
		Assert.assertEquals(title, "Register - Screener", "Register page Title does not match");
		
	}
	
	@Test(priority=2)
	public void fillCreateAccountFormTest() {
		
		signupPage.fillCreateAccountForm(prop.getProperty("email"), prop.getProperty("password"));
		
	}
	
	
	@Test(priority=3)
	public void validateCreateAccountBtnTextTest() {
		
		signupPage.validateCreateAccountBtnText();
		
	}
	
	@Test(priority=4)
	public void searchCompanyFieldEnabledTest() {
		
		Assert.assertTrue(signupPage.searchCompanyFieldEnabled());
		
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
		
	}


}
