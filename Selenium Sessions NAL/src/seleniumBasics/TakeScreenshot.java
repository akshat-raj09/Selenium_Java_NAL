package seleniumBasics;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TakeScreenshot {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
		// Maximize window & delete cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// Dynamic wait added.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// open google.com
		chrome.get("https://www.google.com/");
		
		// take screenshot & store it as file format (TakesScreenshot is an interface).
		File src = ((TakesScreenshot)chrome).getScreenshotAs(OutputType.FILE);
		
		// now copy file to the desired location.
		FileUtils.copyFile(src, new File("D:\\Data\\Study Material\\Java\\screenshot.jpg"));
		
		
		Thread.sleep(5000);
		chrome.quit();
	}

}
