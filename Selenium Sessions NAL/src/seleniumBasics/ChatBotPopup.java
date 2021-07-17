package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * 
 * There is another way to handle chatbot popup.
 * After the website loads completely, mouse over to the chat bot window using Actions class.
 * After moving mouse to the chatbot window when the close button appears click it to close the popup. 
 * 
 */

public class ChatBotPopup {
	
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
		
		// open URL
		chrome.get("https://www.flipkart.com/");
		Thread.sleep(2000);
		
		// find email & mobile field & enter data into them
		chrome.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys("test@test.com");
		chrome.findElement(By.xpath("//input[@type='password' and @class='_2IX_2- _3mctLh VJZDxU']")).sendKeys("test@123");
		
		// now click on the close button of the popup
		Thread.sleep(1000);
		chrome.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		
		// close browser after 2 seconds
		Thread.sleep(2000);
		chrome.quit();
		
	}

}
