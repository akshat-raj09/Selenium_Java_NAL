package com.screener.qa.Report.ExtentReport;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import com.screener.qa.base.TestBase;
import com.screener.qa.util.TestUtil;

public class TestNgExtentListener extends TestBase implements ITestListener {

	private static final Logger log =  LogManager.getLogger(TestNgExtentListener.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		
		// before each test case start
		log.info("Test case: " + result.getMethod().getMethodName() + " is Started");
		test = extent.createTest(result.getMethod().getMethodName());
		
	}
	

	@Override
	public void onTestSuccess(ITestResult result) {
		
		log.info("Test case: " + result.getMethod().getMethodName() + " is Passed");
		test.log(Status.PASS, "Test Case: " + result.getMethod().getMethodName() + " is Passed.");
		
	}
	

	@Override
	public void onTestFailure(ITestResult result) {
		
		log.info("Test case: " + result.getMethod().getMethodName() + " is Failed");
		log.info("The Error Message is: " + result.getThrowable());
		test.log(Status.FAIL, "Test Case: " + result.getMethod().getMethodName() + " is Failed.");
		test.log(Status.FAIL, result.getThrowable());
		String path = null;
		try {
			path = TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(path, "Failed Test Screenshot");
		
	}
	

	@Override
	public void onTestSkipped(ITestResult result) {
		
		log.info("Test case: " + result.getMethod().getMethodName() + " is Skipped");
		test.log(Status.SKIP, "Test Case: " + result.getMethod().getMethodName() + " is Skipped.");
		
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
		
		// before test execution starts : call setupExtentReport()
		log.info("Test Cases are about to start");
		extent = ExtentSetup.setupExtentReport();
		
	}
	

	@Override
	public void onFinish(ITestContext context) {
		
		// close extent
		extent.flush();
		
	}
	

}
