package com.screener.qa.testcases;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import com.screener.qa.base.TestBase;
import com.screener.qa.pages.HomePage;
import com.screener.qa.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import com.screener.qa.Report.AllureReport.TestNgAllureListener;

@Listeners({TestNgAllureListener.class})

public class LoginPageTest extends TestBase{
	
	//Initialize Log4j instance
    private static final Logger log =  LogManager.getLogger(LoginPageTest.class);
        
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() {
		
		super();
		
	}
	
	@BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", prop.getProperty("browser"))
                        .put("Browser Version", "91.0.4472.124")
                        .put("URL", prop.getProperty("url"))
                        .put("OS", prop.getProperty("browser"))
                        .put("Executed By User", System.getProperty("user.name"))
                        .build(), System.getProperty("user.dir") + "\\allure-results\\");
    }
	
	
	@BeforeMethod
	public void setup() {
		
		initialization();
		loginPage= new LoginPage();
		
	}
	
	@Test(priority=1, description="Verifying login page title.")
	@Description("verifying login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Story("Story Name: To check login page title")
	public void loginPageTitleTest(Method method) {
		
		log.info("login page title test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		
		String title = loginPage.validateLoginPageTitle();
		test.log(Status.INFO, "Fetched Title of webpage");
		Assert.assertEquals(title, "Login - Screener1234", "Title does not match");
		
		log.info("Login page title test ended");
		
	}
	
	@Test(priority=2, description="Verifying screener logo on Login page.")
	@Description("verifying screener logo test")
	@Severity(SeverityLevel.NORMAL)
	@Story("Story Name: To check screener logo on login page")
	public void screenerImageLogoTest(Method method) {
		
		log.info("Login page logo test started");
		test.log(Status.INFO, method.getName() + ": Test is starting");
		
		boolean flag = loginPage.validateScreenerLogo();
		test.log(Status.INFO, "Fetched status of the screener logo on the webpage");
		Assert.assertTrue(flag, "Screener logo on login page is not visible.");
		
		log.info("login page logo test ended");
		
	}
	
	
	@Test(priority=3, description="Logging in by filling correct username & password")
	@Description("logging into account")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Story Name: To check account login function")
	public void loginTest(Method method) {
		
		test.log(Status.INFO, method.getName() + ": Test is starting");
		
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		log.info("logged into website");
		test.log(Status.INFO, "Loggedin to the website");
		
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
