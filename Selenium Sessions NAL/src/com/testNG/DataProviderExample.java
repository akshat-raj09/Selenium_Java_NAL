package com.testNG;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DataProviderExample {
	
	WebDriver driver;
	public static String TESTDATA_SHEET_PATH = "D:\\Data\\Study Material\\Java\\ScreenerTest\\src\\main\\java\\com\\screener\\qa\\testdata\\create_screen_queries.xlsx";
	public static Workbook workbook;
	public static Sheet sheet;
	public static FileInputStream file;
	public static Object[][] data;
	
	// The setup() method will open chrome window & login into screener.in
	@BeforeMethod
	public void setup() throws InterruptedException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		// Maximize window, delete all cookies & added dynamic wait.
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// open URL & enter username & password & then click on login
		driver.get("https://www.screener.in/login/");
		Thread.sleep(1000);
		driver.findElement(By.id("id_username")).sendKeys("akshat@inboxkitten.com");
		driver.findElement(By.id("id_password")).sendKeys("test@123");
		driver.findElement(By.xpath("//button[@type='submit' and @class='button-primary']")).click();
		Thread.sleep(1000);
				
	}
	
	
	// This DataProvider method uses Apache POI API to fetch data from Microsoft excel sheet.
	// DataProvider executes first & retrieves all the data from the excel file.
	// Created a data provider annotation that will fetch the data from create_screen_queries.xlsx file (Sheet name: create_screen_queries).
	// It will store the data fetched from excel file into a 2D object array & returns that array at the end of the method.
	@DataProvider
	public Object[][] getQueryTestData() throws InvalidFormatException, EncryptedDocumentException, IOException{
		
		file = new FileInputStream(TESTDATA_SHEET_PATH);
		workbook = WorkbookFactory.create(file);
//		Instead of the above line we can also use the following line.
//		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheet("create_screen_queries");
//		We can also get the sheet by using the index.
//		Sheet sheet = workbook.getSheetAt(0);
		
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println(data[i][k] + "\n");
			}
		}
		
		return data;
		
	}
	
	
	// This test case will use the data provided by DataProvider method & then run the test case. 
	// The number of arguments in this test case method should be equal to the number of columns in the create_screen_queries.xlsx file.
	@Test(priority=1, dataProvider="getQueryTestData")
	public void fillQueryTextArea(String query) throws InterruptedException {
		
		driver.navigate().to("https://www.screener.in/screen/new/");
		Thread.sleep(1000);
		driver.findElement(By.id("query-builder")).sendKeys(query);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@type='submit' and @class='button-primary']")).click();
		
	}
	
	
	// After the test case is complete close the browser.
	@AfterMethod
	public void teadDown() {
		
		// close the browser after 2 seconds.
		try {
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.quit();
		
	}

}
