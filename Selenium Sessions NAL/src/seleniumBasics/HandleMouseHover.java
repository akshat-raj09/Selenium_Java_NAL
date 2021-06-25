package seleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HandleMouseHover {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome  = new ChromeDriver();
		
		// Maximize the browser window
		chrome.manage().window().maximize();
		
		chrome.get("https://www.amazon.in/");
		
		// Added some waiting period so that the page can load completely.
		Thread.sleep(2000);
		
		// Create an instance of Actions class & using that instance move mouse pointer to the desired web element.
		// Whenever using the Action class instance to perform any action we have to always use .build().perform() methods at the last.
		Actions action = new Actions(chrome);
		action.moveToElement(chrome.findElement(By.id("nav-link-accountList"))).build().perform();
		
		// Added some wait so that the element becomes visible on which we have to perform action.
		Thread.sleep(3000);
		
		// Now click on the link which was made visible by the mouser hover action.
		chrome.findElement(By.linkText("Your Account")).click();
		
		// Quit the browser after some waiting.
		Thread.sleep(5000);
		chrome.quit();

	}

}
