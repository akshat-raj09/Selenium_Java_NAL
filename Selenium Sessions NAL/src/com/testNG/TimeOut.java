/*
 * 
 * 
 * Program to understand the concept of timeOut Attribute.
 * It puts an end to the execution of the test case if that method takes time beyond the timeOut duration & test case would be marked as failed.
 * 
 * 
 */


package com.testNG;

import org.testng.annotations.Test;

public class TimeOut {
	
	@Test(priority=1, groups="time out attribute test", timeOut=2000)
	public void infiniteLoopTest() {
		
		while(true)
			System.out.println("it is an infinite loop");
		
	}

}
