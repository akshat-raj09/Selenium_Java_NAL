package com.screener.qa.testCasesRunnableJAR;

import org.testng.TestNG;

import com.screener.qa.Report.AllureReport.TestNgAllureListener;
import com.screener.qa.Report.ExtentReport.TestNgExtentListener;
import com.screener.qa.testcases.CreateScreenPageTest;
import com.screener.qa.testcases.HomePageTest;
import com.screener.qa.testcases.LoginPageTest;
import com.screener.qa.testcases.ScreensPageTest;
import com.screener.qa.testcases.SignupPageTest;
import com.screener.qa.util.AnnotationTransformer;


/********************** TestRunnerJAR Class: To create runnable JAR file of test cases ***************************
 
 1. Instead of testng.xml we can also use TestRunnerJAR.java to run all the test cases from a single file.
 
 2. After creating this TestRunnerJAR class do the following:-
 -- Add Maven JAR Plugin in <plugins> tag inside pom.xml
 -- Right click on project & select Run as --> run configurations --> Main Class Field: see that TestRunnerJAR is selected,
 then click on search button & again select TestRunnerJAR file
 -- Now restart Eclipse
 -- Select the project (ScreenerTest) from left panel & click on Project dropdown menu select clean & again restart Eclipse
 
 
 3. To create a runnable JAR file of all the test cases do the following steps:-
 -- Right click on project & select export --> Java --> Runnable JAR File --> Launch Configuration field: TestRunnerJAR 
 -- Library Handling: extract required libraries into generated JAR --> Finish button
 
 
 4. To execute the JAR file :- make sure that the latest JRE is installed in your system to run the JAR file
 -- run from GUI: double click on jar file & run it 
 -- run from command line: goto the location where the runnable jar file is located from command line & type java -jar filename.jar
 
 */


public class TestRunnerJAR {
	
	static TestNG testNg;

	public static void main(String[] args) {
		
		// creating the objects of all the listeners classes
		TestNgExtentListener extentListener = new TestNgExtentListener();
		TestNgAllureListener allureListener = new TestNgAllureListener();
		AnnotationTransformer annotationTransformer = new AnnotationTransformer();
		
		// create object of TestNG
		testNg = new TestNG();
		
		// now add the listeners
		testNg.addListener(extentListener);
		testNg.addListener(allureListener);
		testNg.addListener(annotationTransformer);
		
		// select the classes that you want to run
		testNg.setTestClasses(new Class[] {
				LoginPageTest.class,
				HomePageTest.class,
				ScreensPageTest.class,
				SignupPageTest.class,
				CreateScreenPageTest.class
		});
		
		testNg.run();
		
	}

}
