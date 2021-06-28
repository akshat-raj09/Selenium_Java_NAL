package seleniumBasics;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class FluentWaitConcept {

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
		
		// We are declaring a fluent wait with the timeout of 30 seconds and the frequency is set to 5 seconds by ignoring NoSuchElementException.
		// This means that it will check for the element on the web page at every 5 seconds for the maximum time of 30 seconds. If the element is located within this time frame it will perform the operations else it will throw an NoSuchElementException.
		Wait<WebDriver> wait = new FluentWait<WebDriver>(chrome)
		.withTimeout(Duration.ofSeconds(30))
		.pollingEvery(Duration.ofSeconds(5))
		.ignoring(NoSuchElementException.class);

		WebElement forgorEmailLink = wait.until(new Function<WebDriver, WebElement>() {
			//We have created a new function to identify the Web Element on the page.
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//button[text()='Forgot email?']"));
				}
		});
		
		// click on the forgot email link.
		forgorEmailLink.click();
		
		// Close the browser after 5 seconds
		Thread.sleep(5000);
		chrome.quit();
		
	}

}
