package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpecialLocators {
	
	static WebDriver chrome = null;
	static WebElement email = null;
	static WebElement pwd = null;

	public static void main(String[] args) throws InterruptedException {
		
		// Using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();

		chrome = new ChromeDriver();

		// maximize window & delete all cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();

		// Added dynamic wait.
		chrome.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		chrome.get("https://www.facebook.com/");
		
		// using ByAll - It will find the element using any one of the locators. Execution will start from left to right.
		// it will wait till the implicitly wait timeout for each locator type.
		email = chrome.findElement(new ByAll(By.name("email"), By.id("email"), By.xpath("//input[@id='email']")));
		email.sendKeys("test email");
		Thread.sleep(1500);
		email.clear();
		
		// using ByIdOrName
		pwd = chrome.findElement(new ByIdOrName("pass"));
		pwd.sendKeys("testpassword");
		Thread.sleep(1500);
		pwd.clear();
		
		// now navigate to gmail login page
		chrome.navigate().to("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		
		// using ByChained - This locator will only work on parent child hierarchy
		email = chrome.findElement(new ByChained(By.cssSelector(".aXBtI.Wic03c"),	// this is the parent of By.className()
				By.className("Xb9hP"),												// this is the parent of By.id()
				By.id("identifierId")));											// this is the child of By.className() & grandchild of By.cssSelector()
		email.sendKeys("test email");
		Thread.sleep(1500);
		email.clear();
		
		// close the browser after 3 seconds
		Thread.sleep(3000);
		chrome.quit();
		
	}

}
