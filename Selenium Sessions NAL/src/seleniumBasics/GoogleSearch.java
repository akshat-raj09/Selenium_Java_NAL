package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearch {

	public static void main(String[] args) throws InterruptedException {
		
		// Using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		WebDriver chrome = new ChromeDriver();
		
		// maximize window & delete all cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// Added dynamic wait.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// open google.com
		chrome.get("https://www.google.com/");
		
		// find the google search text field & type 'test' into it.
		chrome.findElement(By.xpath("//input[@name='q']")).sendKeys("test");
		
		Thread.sleep(2000);
		
		// create a list of type WebElement & store in it all <div> that are under <li>.
		List<WebElement> list = chrome.findElements(By.xpath("//ul[@role='listbox']//li//descendant::div[@class='wM6W7d']"));
		
		// print total number of search results that are displayed after typing 'test' into the search box.
		System.out.println("Total number of search results are : " + list.size());
		
		// This for loop iterates over all the elements of the list of type WebElement and finds the WebElement whose text is equal to 'test speed' & then clicks it.
		for(int i=0; i<list.size(); i++) {
			
			System.out.println(list.get(i).getText());
			
			if(list.get(i).getText().contains("test speed")) {
				list.get(i).click();
				break;
			}
		}
		
		
		// This for each loop also performs the same function as the above for loop.
		/*
		for(WebElement element : list) {
			
			System.out.println(element.getText());
			
			if(element.getText().contains("test speed")) {
				element.click();
				break;
			}
			
		}
		*/
		
		Thread.sleep(5000);
		chrome.quit();
		
	}

}
