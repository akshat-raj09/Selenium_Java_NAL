/*
 * 
 * 
 * This program demonstrates how to handle exception in TestNG using ExpectedExceptions Attribute.
 * If ExpectedExceptions attribute is passed in a test case & if that test case throws an exception then it will not fail that test case & instead it will let it run completely.
 * 
 * 
 */


package com.testNG;

import org.testng.annotations.Test;

public class ExpectedExceptions {
	
	@Test(priority=1, groups="testing exception handling of TestNG", expectedExceptions=NumberFormatException.class)
	public void exceptionTest() {
		
		String x = "100A";
		int a = Integer.parseInt(x);
		System.out.println("Test case executes successfully even after throwing an exception");
		
	}

}
