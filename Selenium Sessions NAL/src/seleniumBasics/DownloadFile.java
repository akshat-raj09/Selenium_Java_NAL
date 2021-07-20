package seleniumBasics;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


/***************************** FILE DOWNLOAD CONFIGURATION FOR MOZILLA FIREFOX *******************************
  
  		FirefoxProfile profile = new FirefoxProfile();
  		profile.setPreference("browser.download.dir", folder.getAbsolutePath());
  		profile.setPreference("browser.download.folderList", 2);
  		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg, application/pdf, application/octet-stream");
  		profile.setPreference("pdfjs.disabled", true);
  		driver = new FirefoxDriver(profile);
  
 */


public class DownloadFile {
	
	static WebDriver driver = null;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		// create a folder inside seleniumBasics package
		// the folder name is given using random UUID
		String filePath = System.getProperty("user.dir") + "\\src\\seleniumBasics\\" + UUID.randomUUID().toString();
		File folder = new File(filePath);
		folder.mkdir();
		
		ChromeOptions options = new ChromeOptions();
		
		// this HashMap instance is used to store the settings for chrome browser like : downloads folder location etc.
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", folder.getAbsolutePath());
		
		options.setExperimentalOption("prefs", prefs);
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		// open chrome browser using the above defined settings
		driver = new ChromeDriver(cap);
		
		// maximize browser & delete all cookies & added dynamic wait
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// open URL
		driver.get("https://file-examples.com/index.php/text-files-and-archives-download/");
		
		// find the file download button & click on it
		driver.findElement(By.xpath("//a[@download='zip_2MB.zip']")).click();
		
		// wait for 10 seconds so that the file can be downloaded completely (File Size: 2 Mb)
		Thread.sleep(10000);
		
		// get the list of downloaded files 
		File listOfFiles[] = folder.listFiles();
		
		// check if the file was downloaded or not
		Assert.assertTrue(listOfFiles.length > 0);
		
		// iterate over all the downloaded files & check if size of each file is > 0
		for(File file : listOfFiles) {
			Assert.assertTrue(file.length() > 0);
		}
		
		// close the browser after 2 seconds
		Thread.sleep(2000);
		driver.quit();
		
		// wait for 5 seconds before deleting the files & folder
		Thread.sleep(5000);
		
		// iterate over all the downloaded files & delete each file one by one
		for(File file : listOfFiles) {
			file.delete();
		}
		
		// finally after deleting all the files, delete the folder
		folder.delete();
		
	}

}
