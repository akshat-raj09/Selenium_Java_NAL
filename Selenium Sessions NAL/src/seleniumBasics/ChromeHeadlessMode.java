package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeHeadlessMode {

	public static void main(String[] args) throws InterruptedException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		// Create an instance of ChromeOptions class.
		ChromeOptions options = new ChromeOptions();
		
		// Add arguments for window size & launch mode (headless).
		// Always provide window-size because if we donot provide this then chrome would be launched in default window-size i.e. size of mobile window & some websites which are not compatible with mobile version would not be accessible properly & might throw exception for some elements.
		options.addArguments("window-size=1400,800");
		options.addArguments("headless");
		
		// now create an instance of ChromeDriver & pass options to it.
		WebDriver chrome = new ChromeDriver(options);
		
		// Added dynamic wait.
		chrome.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// open google & print its title.
		chrome.get("https://www.google.com/");
		System.out.println(chrome.getTitle());
		
		// goto youtube & print its title.
		chrome.navigate().to("https://www.youtube.com/");
		System.out.println(chrome.getTitle());
		
		// quit the browser after 4 seconds.
		Thread.sleep(3000);
		chrome.quit();
		
	}

}
