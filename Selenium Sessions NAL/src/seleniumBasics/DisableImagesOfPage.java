package seleniumBasics;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DisableImagesOfPage {
	
	static WebDriver driver = null;
	static ChromeOptions chromeOptions = null;
	static FirefoxOptions firefoxOptions = null;

	public static void main(String[] args) throws InterruptedException {
		
		/*
		 
		WebDriverManager.firefoxdriver().setup();
		firefoxOptions = new FirefoxOptions();
		disableImages(firefoxOptions);
		driver = new FirefoxDriver(firefoxOptions);
		
		*/
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty()
		WebDriverManager.chromedriver().setup();
		
		// using ChromeOptions class to disable images on a webpage
		chromeOptions = new ChromeOptions();
		disableImages(chromeOptions);
		driver = new ChromeDriver(chromeOptions);

		// maximize window & delete all cookies
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		// dynamic wait added
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// open amazon.in without images
		driver.get("https://www.amazon.in/");
				
		// close the browser after 5 seconds
		Thread.sleep(5000);
		driver.quit();
		
	}
	
	public static void disableImages(ChromeOptions options) {
		
		HashMap<String, Object> images = new HashMap<String, Object>();
		images.put("images", 2);
		
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values", images);
		
		options.setExperimentalOption("prefs", prefs);

	}

	public static void disableImages(FirefoxOptions options) {
		
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("permissions.default.image", 2);
		
		options.setProfile(profile);
		
		options.setCapability(FirefoxDriver.PROFILE, profile);

	}

}
