package seleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectDropdown {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");		
		WebDriver chrome = new ChromeDriver();
		
		// Maximizes the opened window		
		chrome.manage().window().maximize();
		
		chrome.get("https://www.testandquiz.com/selenium/testing.html");
		
		// Prints the text of the hyperlink "This is a link"	
		System.out.println("\n" + chrome.findElement(By.linkText("This is a link")).getText() + "\n");
		
		chrome.findElement(By.id("fname")).sendKeys("Akshat");
		chrome.findElement(By.id("idOfButton")).click();
		chrome.findElement(By.xpath("//*[@id=\"male\"]")).click();
		chrome.findElement(By.className("Automation")).click();
		
		// Selecting a value from dropdown
		Select dropdown = new Select(chrome.findElement(By.id("testingDropdown")));
		dropdown.selectByVisibleText("Database Testing");
		
		try {
			Thread.sleep(6000);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}		
		chrome.quit();
	}

}
