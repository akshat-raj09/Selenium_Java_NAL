package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AuthenticationPopup {

	public static void main(String[] args) throws InterruptedException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty()
		WebDriverManager.chromedriver().setup();

		WebDriver chrome = new ChromeDriver();

		// maximize window & delete all cookies
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();

		// dynamic wait added
		chrome.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// The format to handle authentication popup is as follows :-
		// http://username:password@URL
		chrome.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Thread.sleep(3000);
		
		// print the text present on the webpage
		String textOnWebpage = chrome.findElement(By.xpath("//p[contains(text(),'Congratulations')]")).getText();
		System.out.println("\n" + textOnWebpage);
		
		
		// close the browser after 2 seconds
		Thread.sleep(2000);
		chrome.quit();
		
		
	}

}
