package com.screener.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.screener.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT_TIMEOUT = 10;
	
	public static void takeScreenshot() throws IOException {
		
		// take screenshot & store it as file format (TakesScreenshot is an interface).
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		// now copy file to the desired location.
		FileUtils.copyFile(src, new File("D:\\Data\\Study Material\\Java\\screenshot.jpg"));
	
	}

}
