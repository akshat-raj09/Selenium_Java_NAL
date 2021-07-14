package com.screener.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.screener.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT_TIMEOUT = 10;
	
	public static String TESTDATA_SHEET_PATH = "D:\\Data\\Study Material\\Java\\ScreenerTest\\src\\main\\java\\com\\screener\\qa\\testdata\\create_screen_queries.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	
	
	public static Object[][] getTestData(String sheetNameInsideExcelFile) throws InvalidFormatException {
		
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetNameInsideExcelFile);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		
		return data;
		
	}
	
	
	
	public static String takeScreenshotAtEndOfTest() throws IOException {
		
		String date = new SimpleDateFormat("dd-MM-yyyy_hh.mm.ss.SSS").format(new Date());
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + date + ".png";
		File destFile = new File(path);
		FileUtils.copyFile(scrFile, destFile);
		
		return path;
		
	}
	

}