package com.screener.qa.Report.AllureReport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.screener.qa.base.TestBase;
import io.qameta.allure.Attachment;

public class TestNgAllureListener extends TestBase implements ITestListener {
	
	private static final Logger log =  LogManager.getLogger(TestNgAllureListener.class);
	
	
	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver1) {
		
		return ((TakesScreenshot) driver1).getScreenshotAs(OutputType.BYTES);
	
	}
	

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		
		return message;
	
	}
	
	
	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		
		return html;
	
	}
	
	

	@Override
	public void onTestStart(ITestResult result) {
		
		// before each test case start
		log.info("Test case: " + result.getMethod().getMethodName() + " is Started");
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		log.info("Test case: " + result.getMethod().getMethodName() + " is Passed");
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		log.info("Test case: " + result.getMethod().getMethodName() + " is Failed");
		log.info("The Error Message is: " + result.getThrowable());


		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog("Test Case: " + result.getMethod().getMethodName() + " is failed and screenshot taken");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		log.info("Test case: " + result.getMethod().getMethodName() + " is Skipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		log.info("Test Failed But is Within Success Percentage: " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
		log.info("Test Failed With Timeout: " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		// before the execution of test cases
		log.info("Test Cases are about to start");
		System.out.println("I am in onStart method " + context.getName());
		context.setAttribute("WebDriver", driver);
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		log.info("Test Cases execution has finished " + context.getName());
		
	}

}