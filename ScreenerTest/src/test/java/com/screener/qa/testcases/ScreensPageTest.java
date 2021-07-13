package com.screener.qa.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.screener.qa.base.TestBase;
import com.screener.qa.pages.CreateScreenPage;
import com.screener.qa.pages.HomePage;
import com.screener.qa.pages.LoginPage;
import com.screener.qa.pages.ScreensPage;

public class ScreensPageTest extends TestBase {
	
	//Initialize Log4j instance
    private static final Logger log =  LogManager.getLogger(ScreensPageTest.class);
	
	LoginPage loginPage;
	HomePage homePage;
	ScreensPage screensPage;
	CreateScreenPage createScreenPage;
	
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
		
		log.info("Screens page title test started");
		Assert.assertEquals(screensPage.validateScreensPageTitle(), "Explore stock screens - Screener", "Screens Page title does not match");
		log.info("Screens page title test ended");
		
	}
	
	@Test(priority=2)
	public void validateUserNameLabelTest() {
		
		log.info("Screens page username label test started");
		Assert.assertEquals(screensPage.validateUserNameLabel(), "AKSHAT", "Username on screens page doesnot match");
		log.info("Screens page username label test ended");
		
	}
	
	@Test(priority=3)
	public void validatecreateScreenLinkTextTest() {
		
		log.info("Screens page create screen link text test started");
		Assert.assertEquals(screensPage.validateCreateScreenLinkText(), "CREATE NEW SCREEN", "Create new screen link text doesnot match");
		log.info("Screens page create screen link text test ended");
		
	}
	
	@Test(priority=4)
	public void clickOnCreateScreenLinkTest() {
		
		log.info("click on create screen link test started");
		createScreenPage = screensPage.clickOnCreateScreenLink();
		log.info("click on create screen link test ended");
		
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
		log.info("Browser closed");
	}

}