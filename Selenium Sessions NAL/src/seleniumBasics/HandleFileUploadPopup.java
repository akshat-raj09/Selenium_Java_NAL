package seleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleFileUploadPopup {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
		// Maximize the browser window
		chrome.manage().window().maximize();
		
		chrome.get("https://html.com/input-type-file/");
		
		// Find the file upload button's id/xpath/name etc. & then use sendKeys() to append file location to it.
		WebElement fileUpload = chrome.findElement(By.id("fileupload"));
		fileUpload.sendKeys("D:\\Data\\Study Material\\Java\\a.bat");
		
		// After appending the file location you can click on the file submit if you want to using selenium.
		
		try {
			Thread.sleep(6000);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}
		chrome.quit();
		
	}

}
