package seleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomateGmailSignup {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");		
		WebDriver chrome = new ChromeDriver();
		
		// Maximizes the opened window		
		chrome.manage().window().maximize();
		
		chrome.get("https://accounts.google.com/signup/v2/webcreateaccount?flowName=GlifWebSignIn&flowEntry=SignUp");
		
		chrome.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys("Akash");
		chrome.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys("Kumar");
		chrome.findElement(By.id("username")).sendKeys("akashkumarakashkumar00009");
		chrome.findElement(By.name("Passwd")).sendKeys("@Akashkumar00009");
		chrome.findElement(By.name("ConfirmPasswd")).sendKeys("@Akashkumar00009");		
		chrome.findElement(By.cssSelector(".VfPpkd-muHVFf-bMcfAe")).click();	
		
		// Using absolute xpath (although it is not a good practice). Alternatively we can use this xpath also "//span[contains(text(), 'Next')]".		
		chrome.findElement(By.xpath("//*[@id=\"accountDetailsNext\"]/div/button/span")).click();
		
		try {
			Thread.sleep(5000);
		}
		catch(Exception e) {
			System.out.println(e);
		}			
		// Now navigating to the next page where phone number is entered.
		chrome.findElement(By.id("phoneNumberId")).sendKeys("8989898900");
		
		// Using absolute xpath (although it is not a good practice). Alternatively we can use this xpath also "//span[contains(text(), 'Next')]".
		chrome.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div[2]/div/div[1]/div/div/button/span")).click();
		
		try {
			Thread.sleep(5000);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		chrome.quit();
	}

}
