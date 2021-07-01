package seleniumBasics;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementVisibilityTest {

	public static void main(String[] args) throws InterruptedException {
		
		// using WebDriverManager to setup chrome diver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		WebDriver chrome = new ChromeDriver();
		
		// maximize window & delete all cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// dynamic wait added.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// open URL.
		chrome.get("https://getbootstrap.com/docs/5.0/components/buttons/");
		
		// isDisplayed() - It is suitable for all elements. It is used to verify the presence of WebElement.
		boolean b1 = chrome.findElement(By.xpath("//button[@class='btn btn-lg btn-primary' and text()='Primary button']")).isDisplayed();
		System.out.println("Is the button displayed : " + b1);
		
		// isEnabled() - it is mostly used to check if a button is enabled or not.
		boolean b2 = chrome.findElement(By.xpath("//button[@class='btn btn-lg btn-primary' and text()='Primary button']")).isEnabled();
		System.out.println("Is the button enabled : " + b2);
		
		// move to a different URL.
		Thread.sleep(3000);
		chrome.navigate().to("https://getbootstrap.com/docs/5.0/forms/checks-radios/");
		
		// isSelected() - It is only applicable for checkboxes, radiobuttons and dropdowns. It checks if these elements are selected or not.
		boolean b3 = chrome.findElement(By.xpath("//input[@id='flexCheckDefault']")).isSelected();
		System.out.println("Is the checkbox selected : " + b3);
		boolean b4 = chrome.findElement(By.xpath("//input[@id='flexCheckChecked']")).isSelected();
		System.out.println("Is another checkbox selected : " + b4);
		
		// close the browser after 5 seconds.
		Thread.sleep(5000);
		chrome.quit();
		
	}

}
