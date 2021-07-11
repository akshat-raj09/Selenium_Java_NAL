package com.screener.qa.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.screener.qa.base.TestBase;
import com.screener.qa.pages.CreateScreenPage;
import com.screener.qa.pages.HomePage;
import com.screener.qa.pages.LoginPage;
import com.screener.qa.pages.ScreensPage;
import com.screener.qa.util.TestUtil;

public class CreateScreenPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ScreensPage screensPage;
	CreateScreenPage createScreenPage;
	
	public CreateScreenPageTest() {
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
		createScreenPage = screensPage.clickOnCreateScreenLink();
	}
	
	@Test(priority=1)
	public void validateScreensPageTitleTest() {
		
		Assert.assertEquals(screensPage.validateScreensPageTitle(), "Stock Screener India - Screener", "Screens Page title does not match");
		
	}
	
	@Test(priority=2)
	public void validateUserNameLabelTest() {
		
		Assert.assertEquals(screensPage.validateUserNameLabel(), "AKSHAT", "Username on screens page doesnot match");
		
	}
	
	@DataProvider
	public Object[][] getQueryTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData("create_screen_queries");
		return data;
	}
	
	@Test(priority=3, dataProvider="getQueryTestData")
	public void validateCreateNewScreenTest(String query) {
		createScreenPage.validateCreateNewScreen(query);	
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
