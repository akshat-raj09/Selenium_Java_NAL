package seleniumBasics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadExcelData {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException, IOException {
		
		FileInputStream file = new FileInputStream("D:\\Data\\Study Material\\Java\\ScreenerTest\\src\\main\\java\\com\\screener\\qa\\testdata\\create_screen_queries.xlsx");
		Workbook workbook = new XSSFWorkbook(file);
//		Instead of the above line we can also use the following line.
//		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet("create_screen_queries");
//		We can also get the sheet by using the index.
//		Sheet sheet = workbook.getSheetAt(0);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println(data[i][k] + "\n");
			}
		}
	
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver chrome = new ChromeDriver();
		
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		chrome.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		chrome.get("https://www.screener.in/login/");
		Thread.sleep(1000);
		chrome.findElement(By.id("id_username")).sendKeys("akshat@inboxkitten.com");
		chrome.findElement(By.id("id_password")).sendKeys("test@123");
		chrome.findElement(By.xpath("//button[@type='submit' and @class='button-primary']")).click();
		Thread.sleep(1000);
		
		chrome.navigate().to("https://www.screener.in/screen/new/");
		Thread.sleep(1000);
		chrome.findElement(By.id("query-builder")).sendKeys(data[0][0].toString());
		chrome.findElement(By.xpath("//button[@type='submit' and @class='button-primary']")).click();
		
		
		Thread.sleep(4000);
		chrome.quit();
	
	}

}
