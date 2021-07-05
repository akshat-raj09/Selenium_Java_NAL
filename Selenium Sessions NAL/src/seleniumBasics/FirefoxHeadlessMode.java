package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxHeadlessMode {

	public static void main(String[] args) throws InterruptedException {
		
		// using WebDriverManager to setup firefox driver instead of System.setProperty().
		WebDriverManager.firefoxdriver().setup();
		
		// set firefox binary & add command line arguments for headless mode.
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		firefoxBinary.addCommandLineOptions("--headless");
		
		// create instance of FirefoxOptions & pass object of FirefoxBinary to it.
		FirefoxOptions fo = new FirefoxOptions();
		fo.setBinary(firefoxBinary);
		
		// now create an instance of FirefoxDriver & pass FirefoxOptions instance to it.
		WebDriver firefox = new FirefoxDriver(fo);
		
		// added dynamic wait.
		firefox.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		firefox.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// open google & print its title.
		firefox.get("https://www.google.com/");
		System.out.println(firefox.getTitle());
				
		// goto youtube & print its title.
		firefox.navigate().to("https://www.youtube.com/");
		System.out.println(firefox.getTitle());
		
		// close the browser after 4 seconds.
		Thread.sleep(4000);
		firefox.quit();
		
	}

}
