package seleniumBasics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class OpenBrowersAndQuit {

	public static void main(String[] args) {
		
		// Using Google Chrome
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		chrome.get("https://www.google.com");
		
		// Get title of current page
		String title = chrome.getTitle();
		
		// Validation Point
		if(title.equals("Google"))
			System.out.println("Correct Title");
		else
			System.out.println("Incorrect Title");
		
		// Get URL of current webpage opened
		System.out.println("\n" + chrome.getCurrentUrl());
		
		// Get current webpage source
		System.out.println("\n\n" + chrome.getPageSource());
		
		// Stop execution of current thread for 5000 mil seconds		
		try {
			Thread.sleep(5000);
		}		
		catch(Exception e){
			System.out.println(e);			
		}
		
		// Close browser window
		chrome.quit();
		
		
		
		// Using Mozilla Firefox
		System.setProperty("webdriver.gecko.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\geckodriver.exe");
		WebDriver firefox = new FirefoxDriver();
		firefox.get("https://www.google.com");	
		
		try {
			Thread.sleep(5000);
		}		
		catch(Exception e){
			System.out.println(e);			
		}		
		firefox.quit();
		
		
		
		// Using Microsoft Edge
		System.setProperty("webdriver.edge.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\msedgedriver.exe");
		WebDriver edge = new EdgeDriver();
		edge.get("https://www.google.com");
		
		try {
			Thread.sleep(5000);
		}		
		catch(Exception e){
			System.out.println(e);			
		}		
		edge.quit();
		
		
		
		// Using Internet Explorer
		// Follow instructions on (https://www.lambdatest.com/blog/how-to-run-selenium-tests-using-ie-driver) to run Internet Explorer from Selenium.
		System.setProperty("webdriver.ie.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\IEDriverServer.exe");
		WebDriver ie = new InternetExplorerDriver();
		ie.get("https://www.google.com");
		
		try {
			Thread.sleep(5000);
		}		
		catch(Exception e){
			System.out.println(e);			
		}		
		ie.quit();
		
		
		
		// Using Apple Safari
		// Follow instructions on (https://www.lambdatest.com/blog/selenium-safaridriver-macos/) to run Safari from Selenium.
		// No need to set System property for Safari browser.
		WebDriver safari = new SafariDriver();
		safari.get("https://www.google.com");
		
		try {
			Thread.sleep(5000);
		}		
		catch(Exception e){
			System.out.println(e);			
		}		
		safari.quit();
	}

}
