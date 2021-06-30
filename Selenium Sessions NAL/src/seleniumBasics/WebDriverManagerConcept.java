package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerConcept {

	public static void main(String[] args) throws InterruptedException {
		
		// WebDriverManager in Selenium, is a class that allows us to download and set the browser driver binaries without us, as developers, having to put them in automation scripts manually. So that we don't have to use System.setProperty().
		// for documentation visit this link : https://github.com/bonigarcia/webdrivermanager
		WebDriverManager.chromedriver().setup();
		
		WebDriver chrome = new ChromeDriver();
		
		// maximize browser window & delete all cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// added dynamic wait.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// open google.com
		chrome.get("https://www.google.com/");
		
		// quit the browser after 5 seconds.
		Thread.sleep(5000);
		chrome.quit();
		
	}

}
