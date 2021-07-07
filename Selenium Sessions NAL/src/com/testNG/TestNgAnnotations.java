package com.testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNgAnnotations {
	
	// Pre-condition Annotations starting with @Before
	@BeforeSuite
	public void setup() {
		System.out.println("1. @BeforeSuite - Set system property for chrome");
	}
	
	
	@BeforeTest
	public void launchBrowser() {
		System.out.println("2. @BeforeTest - launch chrome browser");
	}
	
	@BeforeClass
	public void login() {
		System.out.println("3. @BeforeClass - login to the app");
	}
	
	@BeforeMethod
	public void enterUrl() {
		System.out.println("4. @BeforeMethod - enter URL");
	}
	
	// test cases starting with @Test
	// Test cases will be executed in alphabetical order of the test method name, if priority is not defined.
	// For every test case TestNG will create a pair of [....@BeforeMethod  --->  @Test  --->  @AfterMethod....]
	
	@Test(priority=1)
	public void googleTitleTest() {
		System.out.println("5. @Test - google title test");
	}
	
	@Test(priority=2)
	public void googleLogoTest() {
		System.out.println("5. @Test - google logo test");
	}
	
	@Test(priority=3)
	public void searchTest() {
		System.out.println("5. @Test - google search test");
	}
	
	// Post conditions starting with @After
	
	@AfterMethod
	public void logout() {
		System.out.println("6. @AfterMethod - logout from the app");
	}
	
	
	@AfterClass
	public void deleteAllCookies() {
		System.out.println("7. @AfterClass - delete all cookies");
	}
	
	
	@AfterTest
	public void closeBrowser() {
		System.out.println("8. @AfterTest - close the browser");
	}
	
	
	@AfterSuite
	public void generateTestReport() {
		System.out.println("9. @AfterSuite - generate the test report");
	}

}
