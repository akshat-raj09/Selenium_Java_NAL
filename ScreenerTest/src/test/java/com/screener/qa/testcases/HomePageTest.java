package com.screener.qa.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.screener.qa.base.TestBase;
import com.screener.qa.pages.HomePage;
import com.screener.qa.pages.LoginPage;
import com.screener.qa.pages.ScreensPage;

public class HomePageTest extends TestBase {
	
	//Initialize Log4j instance
    private static final Logger log =  LogManager.getLogger(HomePageTest.class);
    
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
		
		log.info("Home page title test started");
		Assert.assertEquals(homePage.validateHomePageTitle(), "Dashboard - Screener", "Home Page title does not match");
		log.info("Home page title test ended");
		
	}
	
	@Test(priority=2)
	public void validateUserNameLabelTest() {
		
		log.info("Home page validate username label test started");
		Assert.assertEquals(homePage.validateUserNameLabel(), "AKSHAT", "Username on home page doesnot match");
		log.info("Home page validate username label test ended");
		
	}
	
	@Test(priority=3)
	public void clickOnScreensLinkTest() {
		
		log.info("Home page click on screens link test started");
		screensPage = homePage.clickOnScreensLink();
		log.info("Home page click on screens link test started");
		
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
