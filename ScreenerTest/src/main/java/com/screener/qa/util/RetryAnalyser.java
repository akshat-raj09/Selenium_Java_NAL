package com.screener.qa.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.screener.qa.base.TestBase;

public class RetryAnalyser extends TestBase implements IRetryAnalyzer {

	private static int counter = 0;

	@Override
	public boolean retry(ITestResult result) {

		if (result.isSuccess() == false) {						// Check if test not succeed
			if (counter < failedTestRetryLimit) {				// Check if counter is still < failedTestRetryLimit
				counter++;										// Increase the counter by 1
				result.setStatus(ITestResult.FAILURE);			// Mark test as failed
				return true;									// Tells TestNG to re-run the test
			} 
			else {
				result.setStatus(ITestResult.FAILURE);			// If failedTestRetryLimit reached, mark test as failed
			}
		} 
		else {
			result.setStatus(ITestResult.SUCCESS);				// If test passes, marks it as passed
		}
		return false;
		
	}

}
