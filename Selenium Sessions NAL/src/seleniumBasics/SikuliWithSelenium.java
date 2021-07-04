package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SikuliWithSelenium {

	public static void main(String[] args) throws InterruptedException, FindFailed {

		// We can also use a Maven project instead of a simple java project. If we use a Maven project then we need to add SiKuLix API jar files & selenium web driver jar files as dependency into the pom.xml file.
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		WebDriver chrome = new ChromeDriver();
		
		// maximize window & delete cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// dynamic wait added.
		chrome.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		// open google.com
		chrome.get("https://www.google.com/");
		
		// store the path of current project folder into path variable.
		String path = System.getProperty("user.dir") + "\\";
		
		// create an object of Screen class.
		Screen s = new Screen();
		
		// pass the image name that we want to identify into the constructor of the Pattern class. if we do not want to use the above path variable then we need to pass full file name to the pattern() constructor (example: "D:\\Data\\Study Material\\Java\\Selenium Sessions NAL\\google_apps.PNG").
		Pattern googleApps = new Pattern(path + "google_apps.PNG");
		// wait for 1 second for the specified pattern to be available.
		s.wait(googleApps, 1000);
		// click on the identified image. Sometimes if the click() doesnot work in one time then we have to use click() another time after the first click().
		s.click();
		
		
		// pass the image name that we want to identify into the constructor of the Pattern class. if we do not want to use the above path variable then we need to pass full file name to the pattern() constructor (example: "D:\\Data\\Study Material\\Java\\Selenium Sessions NAL\\google_feeling_lucky.PNG").
		Pattern googleFeelingLucky = new Pattern(path + "google_feeling_lucky.PNG");
		// wait for 1 second for the specified pattern to be available.
		s.wait(googleFeelingLucky, 1000);
		// click on the identified image. Sometimes if the click() doesnot work in one time then we have to use click() another time after the first click().
		s.click();
		
		Thread.sleep(5000);
		chrome.quit();
		
	}

}
