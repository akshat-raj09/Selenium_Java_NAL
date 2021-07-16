package com.screener.qa.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.screener.qa.util.TestUtil;
import com.screener.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
	
	public static final int failedTestRetryLimit = 2;
	
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	//Initialize Log4j instance
    private static final Logger log =  LogManager.getLogger(TestBase.class);
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase(){
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("D:\\Data\\Study Material\\Java\\ScreenerTest\\src\\main\\java\\com\\screener\\qa\\config\\config.properties");
			prop.load(ip);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void initialization() {
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		log.info("Browser started");
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		// Now register object of WebEventLister with object of EventFiringWebDriver.
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		log.info("Maximized window & deleted all cookies");
		
		driver.get(prop.getProperty("url"));
		log.info("Opened URL");
		
	}

}
