package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImplicitWaitWithDeletingCookiesAndMaximizeWindow {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
		// Maximize the browser window.
		chrome.manage().window().maximize();
		
		// Delete all cookies.
		chrome.manage().deleteAllCookies();
		
		// This is a dynamic wait. This is the page load timeout. if the page does not load within the specified time then it will throw WebDriverException. 
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		// This is a dynamic wait. Even after the page is loaded completely some elements still might not be loaded. So, this will wait for the specified time till all the elements are loaded.
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		chrome.get("https://www.amazon.in/");
		
		// Quit the browser after some wait.
		Thread.sleep(4000);
		chrome.quit();

	}

}
