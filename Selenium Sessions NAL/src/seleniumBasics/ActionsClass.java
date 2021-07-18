package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsClass {

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
		
		// open amazon.in
		chrome.get("https://www.amazon.in/");
		
		// Create an instance of Actions class & using that instance move mouse pointer to the desired web element.
		// Whenever using the Action class instance to perform any action we have to always use .build().perform() methods at the last.
		Actions action = new Actions(chrome);
		action.moveToElement(chrome.findElement(By.id("nav-link-accountList"))).build().perform();
		
		// Added some wait so that the element becomes visible on which we have to perform action.
		Thread.sleep(1000);

		// Now click on the link which was made visible by the mouser hover action.
		chrome.findElement(By.linkText("Your Account")).click();
		Thread.sleep(3000);
		
		// print width & height of the current window
		System.out.println("Window Width: " + chrome.manage().window().getSize().getWidth());
		System.out.println("Window Height: " + chrome.manage().window().getSize().getHeight());
		
		// create a dimension variable of desired (width, height)
		Dimension d = new Dimension(1050,708);
        //Resize current window to the set dimension
        chrome.manage().window().setSize(d);
        Thread.sleep(1000);
		
		// open jquery URL
		chrome.navigate().to("https://jqueryui.com/droppable/");
		
		// Switch control to the frame.
		chrome.switchTo().frame(0);
		Thread.sleep(1000);
		
		// Now using this instance first click & hold the element then move mouse to the element where we want to drop it & then release the element & finally add .build().perform() methods.
		action.clickAndHold(chrome.findElement(By.xpath("//*[@id=\"draggable\"]")))
		.moveToElement(chrome.findElement(By.xpath("//*[@id=\"droppable\"]")))
		.release()
		.build()
		.perform();

		// Switch control back to the main webpage.
		chrome.switchTo().defaultContent();
		Thread.sleep(2000);
		
		// maximize window
		chrome.manage().window().maximize();
		
		// open URL
		chrome.navigate().to("http://demo.guru99.com/test/simple_context_menu.html");
		Thread.sleep(2000);
		
		// store locators of double click button & right click button
		WebElement doubleClickBtn = chrome.findElement(By.xpath("//button[contains(text(),'Double-Click')]"));
		WebElement rightClickBtn = chrome.findElement(By.xpath("//span[contains(text(),'right click')]"));
		
		// double click on double click button
		action.doubleClick(doubleClickBtn).build().perform();
		Thread.sleep(1000);
		
		// accept the double click button alert
		Alert doubleClickAlert = chrome.switchTo().alert();
		Thread.sleep(1000);
		doubleClickAlert.accept();
		Thread.sleep(1000);
		
		// right click on right click button & click on quit option then accept the quit alert
		action.contextClick(rightClickBtn).build().perform();
		Thread.sleep(2000);		
		chrome.findElement(By.xpath("//span[contains(text(),'Quit')]")).click();		
		Alert rightClickAlert = chrome.switchTo().alert();
		Thread.sleep(1000);
		rightClickAlert.accept();
		
		// close the browser after 3 seconds
		Thread.sleep(3000);
		chrome.quit();
		
	}

}
