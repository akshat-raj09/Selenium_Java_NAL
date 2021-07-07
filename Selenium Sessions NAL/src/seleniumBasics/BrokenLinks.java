/*
 * 
 * 
 * Program to find out all the broken links on the webpage.
 * 
 * 
 */

package seleniumBasics;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {
	
	static WebDriver chrome = null;

	public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		chrome = new ChromeDriver();
		
		// maximize window & delete all cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// added dynamic wait.
		chrome.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// open URL.
		chrome.get("https://www.facebook.com/");
		Thread.sleep(2000);
		
		// variables declaration.
		String responseMsgFromWebpage = null;
		int responseCodeFromWebpage = 0;
		List<String> hrefList = new ArrayList<String>();
		
		// add all the <a> & <img> tags into linksList of type WebElement.
		List<WebElement> linksList = chrome.findElements(By.tagName("a"));
		linksList.addAll(chrome.findElements(By.tagName("img")));
		
		// iterate over the linksList & store all the href attributes into hrefList of type String.
		for(WebElement link : linksList) {
			
			if(link.getAttribute("href") != null && (! link.getAttribute("href").contains("javascript"))) {				
				hrefList.add(link.getAttribute("href"));
				System.out.println(link.getAttribute("href"));
			}
			
			
		}
		
		// print the size of both the lists.
		System.out.println("Total Number of links found are : " + linksList.size());
		System.out.println("Total Number of Active links found are : " + hrefList.size());
		
		// iterate over the hrefList & check if all the links are valid or not & print the status of the links.
		for(String link : hrefList) {
			
			HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
			connection.connect();
			responseMsgFromWebpage = connection.getResponseMessage();
			responseCodeFromWebpage = connection.getResponseCode();
			System.out.println(link + " ----------> " + responseMsgFromWebpage + " (" + responseCodeFromWebpage + ")");
			connection.disconnect();
			
		}
		
		
		// close the browser after 4 seconds.
		Thread.sleep(4000);
		chrome.quit();
		
	}

}
