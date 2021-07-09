package com.screener.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.screener.qa.base.TestBase;
import com.screener.qa.pages.HomePage;
import com.screener.qa.pages.LoginPage;
import com.screener.qa.pages.ScreensPage;

public class ScreensPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ScreensPage screensPage;
	
	public ScreensPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		screensPage = new ScreensPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		screensPage = homePage.clickOnScreensLink();
	}
	
	@Test(priority=1)
	public void validateScreensPageTitleTest() {
		
		Assert.assertEquals(screensPage.validateScreensPageTitle(), "Explore stock screens - Screener", "Screens Page title does not match");
		
	}
	
	@Test(priority=2)
	public void validateUserNameLabelTest() {
		
		Assert.assertEquals(screensPage.validateUserNameLabel(), "AKSHAT", "Username on screens page doesnot match");
		
	}
	
	@Test(priority=3)
	public void validatecreateScreenLinkTextTest() {
		
		Assert.assertEquals(screensPage.validatecreateScreenLinkText(), "CREATE NEW SCREEN", "Create new screen link text doesnot match");
		
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