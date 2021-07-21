package com.testNG;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCaseWithDifferentNetworkBandwidth {
	
	static WebDriver driver = null;
	
	@BeforeMethod
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	@DataProvider(name="networkBandwidth")
	public Object[][] networkConditions(){
		
		// the format is {download_speed, upload_speed}
		return new Object[][] {
				
				{5000, 5000},
				{10000, 7000},
				{15000, 9000},
				{23000, 11000},
				{100000, 20000},
				{0, 0}
				
		};
		
	}
	
	@Test(dataProvider="networkBandwidth")
	public void test(int download, int upload) throws IOException {
		
		if(download > 0 && upload > 0) {
			
			CommandExecutor executor = ((RemoteWebDriver)driver).getCommandExecutor();
			
			Map map = new HashMap();
			map.put("offline", false);
			map.put("latency", 5);
			map.put("download_throughput", download);
			map.put("upload_throughput", upload);
			
			Response response = executor.execute(new Command(((RemoteWebDriver)driver).getSessionId(), "setNetworkConditions",
					ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));
			
		}
		
		driver.get("https://www.facebook.com/");
		
	}
	

}
