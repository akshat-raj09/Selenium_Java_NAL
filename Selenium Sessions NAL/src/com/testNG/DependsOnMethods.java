/*
 * 
 * Program to understand how to execute the test cases if a test case depends on another test case for execution.
 * For example : In the below program if loginTest() fails then homePageTest() & searchPageTest() would not be evaluated as they both depend upon loginTest() for execution.
 * And after loginTest() execution, registerPageTest() would be executed.
 * 
 */


package com.testNG;

import org.testng.annotations.Test;

public class DependsOnMethods {
	
	@Test(priority=1, groups="Login Related Test")
	public void loginTest() {
		
		int x = 1/0;
		System.out.println("Login into the app.");
		
	}
	
	@Test(priority=2, groups="Login Related Test", dependsOnMethods="loginTest")
	public void homePageTest() {
		
		System.out.println("Goto home page after login");
		
	}
	
	@Test(priority=3, groups="Login Related Test", dependsOnMethods="loginTest")
	public void searchPageTest() {
		
		System.out.println("Goto search page after login");
		
	}
	
	@Test(priority=4, groups="Register Page Related Test")
	public void registerPageTest() {
		
		System.out.println("Register a new user");
		
	}

}
