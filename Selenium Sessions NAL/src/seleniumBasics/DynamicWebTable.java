package seleniumBasics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicWebTable {

	public static void main(String[] args) throws InterruptedException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		WebDriver chrome = new ChromeDriver();
		
		// maximize window &delete all cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// Added dynamic wait.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// open URL.
		chrome.get("http://demo.guru99.com/test/web-table-element.php");
		
		Thread.sleep(2000);
		
		// Method 1 - using for loop.------------->>>>>>>>
		//*[@id="leftcontainer"]/table/tbody/tr[1]/td[1]/a
		//*[@id="leftcontainer"]/table/tbody/tr[2]/td[1]/a
		//*[@id="leftcontainer"]/table/tbody/tr[3]/td[1]/a
		//*[@id="leftcontainer"]/table/tbody/tr[4]/td[1]/a
		//*[@id="leftcontainer"]/table/tbody/tr[26]/td[1]/a
		
		// break the xpath in 2 parts & store in different variables except for the part that is changing.
		String beforeXpath = "//*[@id=\"leftcontainer\"]/table/tbody/tr[";
		String afterXpath = "]/td[1]/a";
		
		List<String> names = new ArrayList<String>();
		List<String> percentChanges = new ArrayList<String>();
		String changeValue = null, name = "", percentChange = "";
		
		// count the total number of rows in the table & then iterate over it.
		for(int i=1; i<=26; i++) {
			
			// find the text of the element by using the broken xpath & store it in names list & percentChanges list.
			name = chrome.findElement(By.xpath(beforeXpath + i + afterXpath)).getText();
			names.add(name);
			percentChange = chrome.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[" + i + "]/td[5]/font")).getText();
			percentChanges.add(percentChange);
			
			// if the current value of name is equals to 'HDIL' then find its corresponding percent change value & store it in changeValue variable.
			if(name.contains("HDIL"))
				changeValue = chrome.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[" + i + "]/td[5]/font")).getText();
		}
		
		// print the company names & % change column values of the table
		for(int i=0; i<names.size(); i++)
			System.out.println(names.get(i) + "        " + percentChanges.get(i));
		
		// print the % change column value for the 'HDIL' row (if present).
		System.out.println("\nPercentage change in value of HDIL is : " + changeValue);
		
		
		// Method 2 - Using custom xpath.----------->>>>>>>>>>
		// get the text of the % change column for the 'HDIL' row.
		String percentChange1 = chrome.findElement(By.xpath("//a[contains(text(),'HDIL')]//parent::td//following-sibling::td//font[contains(text(),'+   2.9')]")).getText();
		System.out.println("The percentage change value for HDIL is : " + percentChange1);
		
		// close the browser after 5 seconds.		
		Thread.sleep(5000);
		chrome.quit();
		
	}

}
