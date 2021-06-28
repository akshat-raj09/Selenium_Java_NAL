package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {
	
	public static void onClick(WebDriver driver, WebElement locator, int timeout) {
		
		new WebDriverWait(driver, timeout)
		.ignoring(StaleElementReferenceException.class)
		.until(ExpectedConditions.elementToBeClickable(locator));
		
		locator.click();
		
	}

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");
		WebDriver chrome = new ChromeDriver();
		
		// Maximize window & delete cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// Added page load timeout.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		// Open gmail login page in browser.
		chrome.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		
		onClick(chrome, chrome.findElement(By.xpath("//button[text()='Forgot email?']")), 20);
		
		/*
		 * First create an object of WebDriverWait & then use that object to ignore StaleElementReferenceException until expected condition (there are many expected conditions for details visit : https://www.guru99.com/implicit-explicit-waits-selenium.html).
		 
		WebDriverWait wait = new WebDriverWait(chrome, 20);
		WebElement link = wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Forgot email?']")));
		link.click();
		
		*/
		
		Thread.sleep(5000);
		chrome.quit();
	}

}
