package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;


/********************************** Singleton Pattern ***********************************
 * 
 * In singleton pattern we need to initialize the webdriver instance just once.
 * If the driver is initialized once it will never come inside the if(driver == null) condition.
 * After quitting the browser in quit() the driver is again initialized to null.
 * 
 */


public class TestUtil {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT_TIMEOUT = 10;
	
	public static String takeScreenshot() throws IOException {
		
		String date = new SimpleDateFormat("dd-MM-yyyy_hh.mm.ss.SSS").format(new Date());
		
		File scrFile = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + date + ".png";
		File destFile = new File(path);
		FileUtils.copyFile(scrFile, destFile);
		
		return path;
		
	}

}
