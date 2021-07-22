package seleniumBasics;


import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.github.bonigarcia.wdm.WebDriverManager;



/***************************** Twilio SMS OTP API Maven Dependency *******************************
 
  <dependency>
    <groupId>com.twilio.sdk</groupId>
    <artifactId>twilio</artifactId>
    <version>8.17.0</version>
  </dependency>
 
 */


public class AutomateOTP {
	
	
	public static final String ACCOUNT_SID = "AC5e3bf5ce4d4a52d72dc3b7c904d0126b";
	public static final String AUTH_TOKEN = "db537945e75796252a9eafe362ff267b";
	
	static WebDriver driver = null;
	
	static String mobile = "2676425173";

	public static void main(String[] args) throws InterruptedException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty()
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		
		// maximize window & delete all cookies
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		// dynamic wait added
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// open amazon.in create account page
		String url = "https://www.amazon.in/ap/register?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3F_encoding%3DUTF8%26ref_%3Dnav_newcust&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&";
		driver.get(url);
		
		// fill your name field
		driver.findElement(By.id("ap_customer_name")).sendKeys("testuser");
		
		// click on country code dropdown & select USA
		driver.findElement(By.id("auth-country-picker-container")).click();
		driver.findElement(By.xpath("//ul[@role='application']//li//a[contains(text(),'United States +1')]")).click();
		
		// fill mobile number field
		driver.findElement(By.id("ap_phone_number")).sendKeys(mobile);
		
		// fill password field
		driver.findElement(By.id("ap_password")).sendKeys("TestAutomation@123");
		
		// click on continue button & wait for some time so that OTP can be received on the number
		driver.findElement(By.id("auth-continue")).click();
		Thread.sleep(20000);

		// get the OTP using Twilio APIs:-
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		// get sms body of latest sms & print the sms body
		String smsBody = getMessage();
		System.out.println(smsBody);
		
		// get the OTP out of sms body using regular expressions
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
		
		// print the OTP received
		System.out.println(OTPNumber);
		
		// fill the OTP into the OTP verification field
		driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);
		
		// close the browser after 3 seconds
		Thread.sleep(3000);
		driver.quit();

	}
	

	public static String getMessage() {
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+12676425173")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}
	

	private static Stream<Message> getMessages() {
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}
		

}

