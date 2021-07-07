/*
 * 
 *  Program to understand that how test cases can be grouped together. 
 *  So that test cases of different groups appear together in HTML Report.
 *  For example : Tests of Signin page can be placed in one group, Tests of Home page can be placed in one group etc. 
 * 
 */

package com.testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNgGroups {
	
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
		
		System.out.println("Title of google page is : " + chrome.getTitle());
		
	}
	
	@Test(priority=2, groups="Google Search Visual Elements Tests")
	public void googleLogoTest() {
		
		System.out.println("Is the google logo displayed : " + chrome.findElement(By.xpath("//img[@class='lnXdpd' and @alt='Google']")).isDisplayed());
	
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
