package seleniumBasics;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BootstrapButtonDropdown {

	public static void main(String[] args) throws InterruptedException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		WebDriver chrome = new ChromeDriver();
		
		// maximize window & delete all cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// dynamic wait added.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// open URL.
		chrome.get("https://mojoaxel.github.io/bootstrap-select-country/index.html");
		
		// find the select country button & click it so that all the countries options are visible.
		chrome.findElement(By.xpath("//button[@title='Germany']")).click();
		
		// create a list of type WebElement & store in it the value of all <a> tags inside of <li> tags.
		List<WebElement> countries = chrome.findElements(By.xpath("//button[@title='Germany']//following-sibling::div//ul//li//a"));
		String name = null;
		
		// print the total size of the list of countries.
		System.out.println("Size of the country list is : " + countries.size());
		
		// iterate over all the countries <a> tag.
		for(WebElement country : countries) {
			
			name = country.getText();
			
			// if country name is equal to 'India' then click on it.
			if(name.equals("India")) {
				country.click();
				break;
			}
		}
		
		// this for loop also performs the same function as the above one.
		/*
		for(int i=0; i<countries.size(); i++) {
			
			name = countries.get(i).getText();
			
			if(name.equals("India")) {
				countries.get(i).click();
				break;
			}
		}
		*/
		
		// quit the browser after 5 seconds.
		Thread.sleep(5000);
		chrome.quit();
		
	}

}
