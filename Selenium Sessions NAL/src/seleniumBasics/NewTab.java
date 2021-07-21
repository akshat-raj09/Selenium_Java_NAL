package seleniumBasics;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTab {

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
		
		chrome.get("https://www.amazon.in/");
		
		// get the window handle of current window & store it in current tab variable
		String mainTab = chrome.getWindowHandle();	
		
		// this will open the link identified by the xpath in new tab
		String clickLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
		chrome.findElement(By.xpath("//a[contains(text(),'Amazon Pay') and @class='nav-a  ']")).sendKeys(clickLink);
		
		// Set tabs of type String will store the window handles of all the currently opened windows
		Set<String> tabs = chrome.getWindowHandles();	
		
		// iterate over all the tabs & switch to the tab other than the main tab
		for(String tab : tabs) {
			// switch to the new tab
			if(tab.equalsIgnoreCase(mainTab) == false) {
				Thread.sleep(1000);
				chrome.switchTo().window(tab);
				break;
				}

		}
		
		// this will close the new tab after 4 seconds
		Thread.sleep(4000);
		chrome.close();
		
		// switch to main tab after 1 second
		Thread.sleep(1000);
		chrome.switchTo().window(mainTab);
		
		// print the text of amazon pay link on main tab
		System.out.println(chrome.findElement(By.xpath("//a[contains(text(),'Amazon Pay') and @class='nav-a  ']")).getText());
		
		// open a new blank tab using JavaScriptExecutor. 
		// By opening a new by this method control of driver is not switched to new blank tab.
		JavascriptExecutor js = ((JavascriptExecutor)chrome);
		js.executeScript("window.open();");
		Thread.sleep(1000);
		chrome.switchTo().window(mainTab);
		Thread.sleep(2000);
		
		// Set newTabs of type String will store the window handles of all the currently opened windows
		Set<String> newTabs = chrome.getWindowHandles();	

		// iterate over all the tabs & switch to the tab other than the main tab
		for(String tab : newTabs) {
			// switch to the new tab
			if(tab.equalsIgnoreCase(mainTab) == false) {
				Thread.sleep(1000);
				chrome.switchTo().window(tab);
				break;
			}

		}
		
		// after waiting for 2 seconds close the new tab & switch control back to main tab
		Thread.sleep(2000);
		chrome.close();
		chrome.switchTo().window(mainTab);
		Thread.sleep(1000);
		
		// open google.com in a new tab using JavascriptExecutor
		// By opening a new tab by this method control of driver is not switched to new tab.
		js.executeScript("window.open('https://www.google.com','_blank');");
		Thread.sleep(1000);
		chrome.switchTo().window(mainTab);
		// Set newTab of type String will store the window handles of all the currently opened windows
		Set<String> newTab = chrome.getWindowHandles();	

		// iterate over all the tabs & switch to the tab other than the main tab
		for(String tab : newTab) {
			// switch to the new tab
			if(tab.equalsIgnoreCase(mainTab) == false) {
				Thread.sleep(1000);
				chrome.switchTo().window(tab);
				break;
			}

		}

		// after waiting for 2 seconds close the new tab & switch control back to main tab
		Thread.sleep(2000);
		chrome.close();
		chrome.switchTo().window(mainTab);
		
		// print the text of amazon pay link on main tab
		System.out.println(chrome.findElement(By.xpath("//a[contains(text(),'Amazon Pay') and @class='nav-a  ']")).getText());
		
		// close the browser after 3 seconds
		Thread.sleep(3000);
		chrome.quit();
				
	}

}
