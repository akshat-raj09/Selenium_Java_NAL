/*
 * 
 * 
 * Program to demonstrate how to Validate/Assert a test case using Assert Class.
 * Assert.assertEquals(Actual_Value, Expected_Value, "Message to display if the Actual_Value & Expected_Value doesnot match");
 * In case the Actual_Value & Expected_Value doesnot match this will throw an exception & test case will be marked as failed.
 * 
 * 
 */

package com.testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssertionOfTestCase {
	
WebDriver chrome;
	
	@BeforeMethod
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		
		chrome = new ChromeDriver();
		
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		chrome.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		chrome.get("https://www.google.com/");
		
	}
	
	@Test(priority=1, groups="Google Search Visual Elements Tests")
	public void googleTitleTest() {
		
		String title = chrome.getTitle();
		System.out.println("Title of google page is : " + title);
		Assert.assertEquals(title, "Google123", "Google page title does not match.");
		
	}
	
	@Test(priority=2, groups="Google Search Visual Elements Tests")
	public void googleLogoTest() {
		
		boolean b = chrome.findElement(By.xpath("//img[@class='lnXdpd' and @alt='Google']")).isDisplayed();
		System.out.println("Is the google logo visible on the home page : " + b);
		Assert.assertTrue(b);
	
	}
	
	@Test(priority=3, groups="Google Search Page Signin Button Tests")
	public void searchTest() {
		
		chrome.findElement(By.xpath("//a[text()='Sign in']")).click();
		System.out.println("Sign In button clicked");
	
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		
		Thread.sleep(3000);
		chrome.quit();
		System.out.println("Browser closed");
	
	}

}
