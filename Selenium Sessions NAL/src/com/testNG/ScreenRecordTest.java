package com.testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenRecordTest {
	
	WebDriver driver;

	@BeforeMethod
	public void setup(){
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		
		// maximize window & delete all cookies
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		// dynamic wait added
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	
	@Test
	public void navigationTest() throws Exception{
		
		ScreenRecorderUtil.startRecording("navigationTest");

		driver.get("https://www.google.com");
		Thread.sleep(1000);
		driver.navigate().to("https://www.facebook.com");
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().forward();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().forward();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().forward();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().forward();
		Thread.sleep(1000);

		ScreenRecorderUtil.stopRecording();
		
	}


	@AfterMethod
	public void tearDown() throws InterruptedException{
		
		Thread.sleep(2000);
		driver.quit();
		
	}

	
}
