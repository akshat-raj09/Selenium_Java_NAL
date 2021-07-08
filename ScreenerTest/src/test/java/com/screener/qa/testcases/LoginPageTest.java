package com.screener.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.screener.qa.base.TestBase;
import com.screener.qa.pages.HomePage;
import com.screener.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
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
	public void loginPageTitleTest() {
		
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Login - Screener", "Title does not match");
		
	}
	
	@Test(priority=2)
	public void screenerImageLogoTest() {
		
		boolean flag = loginPage.validateScreenerLogo();
		Assert.assertTrue(flag, "Screener logo on login page is not visible.");
		
	}
	
	
	@Test(priority=3)
	public void loginTest() {
		
		homePage = loginPage.login();
		
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
