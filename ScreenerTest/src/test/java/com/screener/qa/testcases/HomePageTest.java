package com.screener.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.screener.qa.base.TestBase;
import com.screener.qa.pages.HomePage;
import com.screener.qa.pages.LoginPage;
import com.screener.qa.pages.ScreensPage;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ScreensPage screensPage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		screensPage = new ScreensPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void validateHomePageTitleTest() {
		
		Assert.assertEquals(homePage.validateHomePageTitle(), "Dashboard - Screener", "Home Page title does not match");
		
	}
	
	@Test(priority=2)
	public void validateUserNameLabelTest() {
		
		Assert.assertEquals(homePage.validateUserNameLabel(), "AKSHAT", "Username on home page doesnot match");
		
	}
	
	@Test(priority=3)
	public void clickOnScreensLinkTest() {
		
		screensPage = homePage.clickOnScreensLink();
		
	}
	
	@AfterMethod
	public void tearDown() {
		try {
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.quit();
	}

}
