package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropElement {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
//		Maximize the browser window. When we maximize the window the below code throws move target out of bounds error.
//		chrome.manage().window().maximize();
		
		// Delete all cookies.
		chrome.manage().deleteAllCookies();
		
		// This is a dynamic wait. This is the page load timeout. if the page does not load within the specified time then it will throw WebDriverException. 
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		// This is a dynamic wait. Even after the page is loaded completely some elements still might not be loaded. So, this will wait for the specified time till all the elements are loaded.
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		chrome.get("https://jqueryui.com/droppable/");
		
		// Switch control to the frame.
		chrome.switchTo().frame(0);
		
		// Create instance of Actions class
		Actions action = new Actions(chrome);
		
		// Now using this instance first click & hold the element then move mouse to the element where we want to drop it & then release the element & finally add .build().perform() methods.
		action.clickAndHold(chrome.findElement(By.xpath("//*[@id=\"draggable\"]")))
		.moveToElement(chrome.findElement(By.xpath("//*[@id=\"droppable\"]")))
		.release()
		.build()
		.perform();
		
		// Switch back to the main webpage.
		chrome.switchTo().defaultContent();
		
		// Quit the browser after some wait.
		Thread.sleep(4000);
		chrome.quit();

	}

}
