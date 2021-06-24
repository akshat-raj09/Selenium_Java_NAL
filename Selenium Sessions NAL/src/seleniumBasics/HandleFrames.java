package seleniumBasics;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleFrames {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
		// Maximize the browser window
		chrome.manage().window().maximize();
		
		chrome.get("https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe");
		
		try {
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}
		
		// Switched frame using frame index. We can also switch to the frame using frame name.
		chrome.switchTo().frame(0);
		
		// Now proceed on the switched frame
		chrome.findElement(By.xpath("//*[@id=\"css\"]")).click();
		
		try {
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}
		
		// Return back to the main page.
		chrome.switchTo().defaultContent();
		chrome.findElement(By.id("main-q")).sendKeys("Test search message");		
		
		// This code creates a List of type WebElement of all the iframe tags present in the main webpage.
		List<WebElement> iframes = chrome.findElements(By.tagName("iframe"));
		
		// Prints the number of iframe tags present in the main webpage.
		System.out.println("\nTotal number of iframe tags present in this webpage is : " + iframes.size());
		
		// Creates a ArrayList of type string to store id attribute of iframes.
		List<String> attributeIdOfIframe = new ArrayList<String>(5);
		
		// This code iterates over the iframes List (type : WebElement) & with each iteration this adds the id attribute of each iframe into the ArrayList (attributeIdOfIframe).
		for (WebElement iframe : iframes) {			
			attributeIdOfIframe.add(iframe.getAttribute("id"));
		}		
		System.out.println("\nID Attribute of the iframe tags are : " + attributeIdOfIframe);
		
		try {
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}
		chrome.quit();

	}

}
