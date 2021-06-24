package seleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicLocators {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
		// Maximizes the window
		chrome.manage().window().maximize();
		
		// Opens login page of yahoo
		chrome.get("https://login.yahoo.com/");
		
		// Locate element by using id
		WebElement username = chrome.findElement(By.id("login-username"));
		username.sendKeys("test@yahoo.com");
		
		// Locate element by using name
		WebElement username1 = chrome.findElement(By.name("username"));
		username1.sendKeys("test@yahoo.com");
		
		// Locate element by using xpath
		// Using absolute xpath is not recommended eg: /html/body/div[1]/div[2]/div[1]/div[2]/div[2]/form/div[1]/div[3]/input
		// Always try and use relative xpath eg: //*[@id="login-username"] (So, even if webpage's allignment changes your ode will work).
		WebElement username2 = chrome.findElement(By.xpath("//*[@id=\"login-username\"]"));
		username2.sendKeys("test@yahoo.com");
		
		
		// Locate element by using classname
		chrome.findElement(By.className("phone-no")).sendKeys("test@yahoo.com");
		
		// Locate element by using cssSelector
		chrome.findElement(By.cssSelector("#login-username")).sendKeys("test@yahoo.com");
		
		// Locate links by using linkText
		chrome.findElement(By.linkText("Forgot username?")).click();
		
		// Locate links by using partialLinkText
		chrome.findElement(By.partialLinkText("Forgot ")).click();		
		
		try {
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) {
			System.out.println(e);
		}
		chrome.quit();

	}

}
