package seleniumBasics;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleWindowPopup {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
		// Maximize window & delete cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// Added dynamic waits.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// open the webpage.
		chrome.get("https://www.seleniumeasy.com/test/window-popup-modal-demo.html");
		
		Thread.sleep(3000);
		
		// click on the popup link.
		chrome.findElement(By.xpath("//a[contains(text(),'Follow On Twitter')]")).click();
		
		// get window handles of all open windows & store it in a set of type string.
		Set<String> windowHandles = chrome.getWindowHandles();
		
		// get the iterator for windowHandles instance. Initially the iterator instance will point to the location just above the first window handle.
		Iterator<String> it = windowHandles.iterator();
		
		// print parent window id.
		String parentWindow = it.next();
		System.out.println("Parent window id is : " + parentWindow);
		// print child window id.
		String childWindow = it.next();
		System.out.println("Child window id is : " + childWindow);
		
		// switch control to child window.
		chrome.switchTo().window(childWindow);
		// get title of child window.
		System.out.println("Title of child window is : " + chrome.getTitle());
		// maximize the child window.
		chrome.manage().window().maximize();
		Thread.sleep(4000);
		// close the child window. We will not use quit() because it will close both parent & child window.
		chrome.close();
		
		// now switch control back to parent window.
		chrome.switchTo().window(parentWindow);
		// print title of parent window.
		System.out.println("Title of parent window is : " + chrome.getTitle());
		
		Thread.sleep(5000);
		chrome.quit();
		
	}

}
