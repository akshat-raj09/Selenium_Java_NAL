package seleniumBasics;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleJavascriptAlerts {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
		// Maximize the browser window
		chrome.manage().window().maximize();
		
		chrome.get("https://mail.rediff.com/cgi-bin/login.cgi");
		
		// Find sign in button & click it.
		chrome.findElement(By.name("proceed")).click();
		
		try {
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}
		
		// Switch to the alert window
		Alert jsAlert = chrome.switchTo().alert();
		
		//Print alert's text message
		System.out.println(jsAlert.getText());
		
		// Validate alert's text message
		if(jsAlert.getText().equals("Please enter a valid user name"))
			System.out.println("\nCorrect message\n");
		else
			System.out.println("\nIncorrect message\n");
		
		// Click on OK button of the alert box
		jsAlert.accept();
		
		// Click on cancel button of the alert box
		jsAlert.dismiss();
			
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}
		chrome.quit();

	}

}
