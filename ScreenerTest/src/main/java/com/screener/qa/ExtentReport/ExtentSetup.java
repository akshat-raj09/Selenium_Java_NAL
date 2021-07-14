package com.screener.qa.ExtentReport;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.screener.qa.base.TestBase;

public class ExtentSetup extends TestBase {
	
	public static ExtentReports setupExtentReport() {
		
		String date = new SimpleDateFormat("dd-MM-yyyy_hh.mm.ss.SSS").format(new Date());
		String extentReportPath = System.getProperty("user.dir") + "\\test-output\\Extent_Report_" + date +".html";
		
		sparkReporter = new ExtentSparkReporter(extentReportPath);
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		sparkReporter.config().setDocumentTitle("Screener.in Test");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Screener.in Automation Test");
		
		extent.setSystemInfo("Executed in Environment: ", "QA");
		extent.setSystemInfo("Executed on Browser: ", prop.getProperty("browser"));
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Executed By User: ", System.getProperty("user.name"));
		
		return extent;
		
	}

}
