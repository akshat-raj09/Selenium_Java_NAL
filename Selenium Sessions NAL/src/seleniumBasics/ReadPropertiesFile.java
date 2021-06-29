package seleniumBasics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadPropertiesFile {
	
	// Create an instance of WebDriver class which would be used later.
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// Create an instance of properties class
		Properties prop = new Properties();
		// create an instance of FileInputStream class & pass location of config.properties to it.
		FileInputStream ip = new FileInputStream("D:\\Data\\Study Material\\Java\\Selenium Sessions NAL\\bin\\seleniumBasics\\config.properties");
		// it will read the properties file.
		prop.load(ip);
		
		// get the browser name & url that we want to open in the browser from config.properties file & store them in variables.
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		
		// check if the browser value that was fetched from config.properties file is same as chrome. If yes then launch the chrome browser.
		if(browser.equals("chrome")) {
			System.setProperty(prop.getProperty("driver_name"), prop.getProperty("driver_path"));
			driver = new ChromeDriver();
		}
		// check if the browser value that was fetched from config.properties file is same as firefox. If yes then launch the firefox browser.
		else if(browser.equals("firefox")) {
			System.setProperty(prop.getProperty("driver_name"), prop.getProperty("driver_path"));
			driver = new FirefoxDriver();
		}
		
		// Maximize window & delete cookies.
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		// Added dynamic wait.
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open the gmail login page.
		driver.get(url);
		
		// fill in the email field & click on the next button.
		driver.findElement(By.xpath(prop.getProperty("email_xpath"))).sendKeys(prop.getProperty("email"));
		driver.findElement(By.xpath(prop.getProperty("next_btn_xpath"))).click();		
		
		// quit browser after 5 seconds.
		Thread.sleep(5000);
		driver.quit();
		
	}

}
