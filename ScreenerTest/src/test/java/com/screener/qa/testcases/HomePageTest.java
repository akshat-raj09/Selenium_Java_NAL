package com.screener.qa.testcases;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.screener.qa.base.TestBase;
import com.screener.qa.pages.HomePage;
import com.screener.qa.pages.LoginPage;
import com.screener.qa.pages.ScreensPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import com.screener.qa.Report.AllureReport.TestNgAllureListener;

@Listeners({TestNgAllureListener.class})

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
	
	@Test(priority=1, description="Verifying Home page title.")
	@Description("verifying home page title test")
	@Severity(SeverityLevel.NORMAL)
	@Story("Story Name: To check home page title")
	public void validateHomePageTitleTest(Method method) {
		
		log.info("Home page title test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		test.log(Status.INFO, "Fetched Title of webpage");
		
		Assert.assertEquals(homePage.validateHomePageTitle(), "Dashboard - Screener1234", "Home Page title does not match");
		
		log.info("Home page title test ended");
		
	}
	
	@Test(priority=2, description="Verifying username label on home page.")
	@Description("verifying username label test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Story Name: To validate username label on home page")
	public void validateUserNameLabelTest(Method method) {
		
		log.info("Home page validate username label test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		test.log(Status.INFO, "Fetched Username Label");
		
		Assert.assertEquals(homePage.validateUserNameLabel(), "AKSHAT", "Username on home page doesnot match");
		
		log.info("Home page validate username label test ended");
		
	}
	
	@Test(priority=3, description="Verifying click on screens link on home page.")
	@Description("verifying click on screens link test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Story Name: To click on screens link on home page")
	public void clickOnScreensLinkTest(Method method) {
		
		log.info("Home page click on screens link test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		test.log(Status.INFO, "clicked on screens link");
		
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
