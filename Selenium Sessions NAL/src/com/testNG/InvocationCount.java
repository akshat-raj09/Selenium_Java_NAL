/*
 * 
 * 
 * Program to understand how to run a test case multiple times using invocationCount attribute without rewriting the code.
 * 
 * 
 */

package com.testNG;

import org.testng.annotations.Test;

public class InvocationCount {
	
	@Test(invocationCount=10)
	public void sum() {
		
		int a = 10, b = 20;
		int c = a+b;
		
		System.out.println("Sum of A+B is : " + c);
		
	}

}
