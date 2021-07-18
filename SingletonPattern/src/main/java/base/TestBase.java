package base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


/********************************** Singleton Pattern ***********************************
 * 
 * In singleton pattern we need to initialize the webdriver instance just once.
 * If the driver is initialized once it will never come inside the if(driver == null) condition.
 * After quitting the browser in quit() the driver is again initialized to null.
 * 
 */

public class TestBase {
	
	public static WebDriver driver = null;
	public static Properties prop;
	
	public TestBase(){
		
			try {
				prop = new Properties();
				String propertiesFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties";
				FileInputStream ip = new FileInputStream(propertiesFilePath);
				prop.load(ip);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	
	public static void initialization() {
			
		if(driver == null) {

			String browserName = prop.getProperty("browser");

			if(browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else if(browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}
		
	}
	
	public static void quit() {
		
		driver.quit();
		driver = null;
		
	}

}