package seleniumBasics;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutorConcept {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver(); 
		
		// Maximize window & delete all cookies.
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies(); 
		
		// Added dynamic wait.
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// open facebook.com
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("naveenk");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("test@1234");
		System.out.println(driver.findElement(By.xpath("//button[contains(@id,'u_0_d_') and @class='_42ft _4jy0 _6lth _4jy6 _4jy1 selected _51sy']")).getText());
		
		// Get the reference of the WebElement on which we want to execute our javascript code.
		WebElement loginBtn = driver.findElement(By.xpath("//button[contains(@id,'u_0_d_') and @class='_42ft _4jy0 _6lth _4jy6 _4jy1 selected _51sy']"));
		
		// highlight the WebElement using JavascriptExecutor.
		flash(loginBtn, driver); 
		
		// draw a border around the WebElement.
		drawBorder(loginBtn, driver);
		
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// now copy the screenshot to desired location using copyFile method.
		FileUtils.copyFile(src, new File("D:\\Data\\Study Material\\Java\\element.png"));
		
		// generate a javascript alert using JavascriptExecutor.
		generateAlert(driver, "There is an issue with Login button on Login Page");
		Thread.sleep(2000);
		Alert jsAlert = driver.switchTo().alert();
		jsAlert.accept();
		
		// click on the  WebElement by using JavascriptExecutor.
		Thread.sleep(2000);
		clickElementByJS(loginBtn, driver);
		
		// navigate back to the facebook homepage.
		Thread.sleep(3000);
		driver.navigate().back();
		
		// refresh the page : By using selenium.
		Thread.sleep(3000);
		driver.navigate().refresh(); 
		
		// refresh the page : By using JavascriptExecutor.
		Thread.sleep(3000);
		refreshBrowserByJS(driver);
		
		// get the tile of the page by using JavascriptExecutor.
		System.out.println("\n" + getTitleByJS(driver));
		
		// get all the text of the page by using JavascriptExecutor.
		System.out.println("\n\n" + getPageInnerText(driver));
		
		// scroll down the webpage till the specified WebElement using JavascriptExecutor.	
		WebElement oculusLink = driver.findElement(By.xpath("//a[contains(text(),'Oculus')]"));
		scrollIntoView(oculusLink, driver);
		
		// Wait for 4 seconds & then quit the browser.
		Thread.sleep(4000);
		driver.quit();
		
	}
	
	
	// This method will highlight the WebElement using JavascriptExecutor.
	public static void flash(WebElement element, WebDriver driver) {
		
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String bgcolor  = element.getCssValue("backgroundColor");
        
        for (int i = 0; i <  50; i++) {
            changeColor("rgb(0,200,0)", element,driver);
            changeColor(bgcolor, element,driver);
        }
        
    }
	
	
	// This method is used inside of flash().
    public static void changeColor(String color, WebElement element, WebDriver driver) {
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '"+color+"'",  element);

        try {
            Thread.sleep(20);
        }  
        catch (Exception e) {
        	System.out.println(e);
        }
        
     }
	
	
    // This method will draw a border around the WebElement.
    public static void drawBorder(WebElement element, WebDriver driver){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].style.border='6px solid red'", element);
    	
    }
    
    
    // This method will generate a javascript alert using JavascriptExecutor.
    public static void generateAlert(WebDriver driver, String message){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("alert('"+message+"')");

    }
    
    
    // This method will click on the WebElement using JavascriptExecutor.
    public static void clickElementByJS(WebElement element, WebDriver driver){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].click();", element);
    	
    }
    
	
    // This method will refresh the webpage using JavascriptExecutor.
    public static void refreshBrowserByJS(WebDriver driver){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("history.go(0)");
    	
    }
    
    
    // This method will return the tile of the webpage by using JavascriptExecutor.
    public static String getTitleByJS(WebDriver driver){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	String title = js.executeScript("return document.title;").toString();
    	return title;
    	
    }
    
    
    // This method will return all the text of the webpage by using JavascriptExecutor.
    public static String getPageInnerText(WebDriver driver){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	String pageText = js.executeScript("return document.documentElement.innerText;").toString();
    	return pageText;
    	
    }
    
    
    // This method will scroll down the page till the end by using JavascriptExecutor.
    public static void scrollPageDown(WebDriver driver){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    	
    }
    
    
    // This method will scroll down the webpage till the specified WebElement using JavascriptExecutor.
    public static void scrollIntoView(WebElement element, WebDriver driver){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].scrollIntoView(true);", element);
    	
    }
    
    
    // This method will select the specified date from the date picker using JavascriptExecutor.
    public static void selectDateByJS(WebDriver driver, WebElement element, String dateValue){
    	
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].setAttribute('value','" + dateValue + "');", element);
    	
    }
    
    
}