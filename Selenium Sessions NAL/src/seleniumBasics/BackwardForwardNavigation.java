package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BackwardForwardNavigation {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
		// Maximize window & delete cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// Dynamic Wait Added.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open desired link.
		chrome.get("https://www.google.com/");
		
		Thread.sleep(2000);
		
		// Open amazon.in in same browser window.
		chrome.navigate().to("https://www.amazon.in/");
		
		Thread.sleep(2000);
		
		// move back to google.com
		chrome.navigate().back();
		
		Thread.sleep(2000);
		
		// move again to amazon.in
		chrome.navigate().forward();
		
		Thread.sleep(2000);
		
		// move back to google.com
		chrome.navigate().back();
		
		Thread.sleep(2000);
		
		// Refresh the current webpage
		chrome.navigate().refresh();
		
		
		Thread.sleep(5000);
		chrome.quit();
	}

}
