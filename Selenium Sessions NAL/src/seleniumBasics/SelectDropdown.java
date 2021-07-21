package seleniumBasics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectDropdown {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");		
		WebDriver chrome = new ChromeDriver();
		
		// Maximizes the opened window		
		chrome.manage().window().maximize();
		
		// open the URL
		chrome.get("https://www.testandquiz.com/selenium/testing.html");
		
		// creating an object of Select class then using that object to selecting value from a dropdown
		Select dropdown = new Select(chrome.findElement(By.id("testingDropdown")));
		
		// print true if this is a multi select dropdown
		System.out.println("This is a multi select dropdown: " + dropdown.isMultiple());
		
		// select by visible text
		dropdown.selectByVisibleText("Database Testing");
		Thread.sleep(1500);
				
		// select by index: this a bit risky if the options are added/deleted then the index will change.
		dropdown.selectByIndex(1);
		Thread.sleep(1500);
		
		// select by value
		dropdown.selectByValue("Manual");
		Thread.sleep(1500);
		
		// select value from a dropdown without using Select Class
		List<WebElement> options = dropdown.getOptions();
		for(WebElement option : options) {
			if(option.getText().equalsIgnoreCase("Database Testing")) {
				option.click();
				break;
			}
			
		}
		
		// close the browser after 4 seconds
		Thread.sleep(4000);		
		chrome.quit();
	}

}
