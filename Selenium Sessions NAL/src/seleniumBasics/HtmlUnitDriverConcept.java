package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitDriverConcept {

	public static void main(String[] args) throws InterruptedException {
		
		// Html Unit Driver does not come with selenium (after version 3.x). So we need to download it separately & add it to build path.
		// Html Unit Driver is also known as ghost driver or headless browser.
		// Advantages : No browser is launched so execution of test cases is very fast.
		// Disadvantages : Html Unit Driver is not suitable for webpages having very complex DOM structure & also not suitable for Actions class implementation.
		// create instance of HtmlUnitDriver instead of ChromeDriver rest everything is same.
		WebDriver htmlDriver = new HtmlUnitDriver();
		
		// Added dynamic wait.
		htmlDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		htmlDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// open google.com		
		htmlDriver.get("https://www.google.com/");
		System.out.println("Title of google is : " + htmlDriver.getTitle());
		
		Thread.sleep(1000);
		
		// move to amazon.in
		htmlDriver.navigate().to("https://www.prsindia.org/");
		System.out.println("Title of PRS is : " + htmlDriver.getTitle());
		
		Thread.sleep(1000);
		
		htmlDriver.navigate().back();
		System.out.println("Moved back & title of the current page is : " + htmlDriver.getTitle());
		
		Thread.sleep(1000);
		
		htmlDriver.navigate().forward();
		System.out.println("Moved forward & title of current page is : " + htmlDriver.getTitle());
		
		Thread.sleep(1000);
		
		htmlDriver.navigate().refresh();
		System.out.println("After refresh title of page is : " + htmlDriver.getTitle());
		
		// quit browser after 2 seconds.
		Thread.sleep(2000);
		htmlDriver.quit();
		
	}

}
