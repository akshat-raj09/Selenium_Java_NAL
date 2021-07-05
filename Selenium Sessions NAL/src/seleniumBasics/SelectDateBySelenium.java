package seleniumBasics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import seleniumBasics.JavaScriptExecutorConcept;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectDateBySelenium {
	
	// This method converts the month & year passed to it in String format to Integer Array (example: "July 2021" --> [7, 2021]).
	public static int[] stringToIntegerDate(String stringMonthYear) {
		
		int date[] = new int[2];
		
		// split the String month & year on the basis of space character (example: "July 2021" --> ["July", "2021"]).
		String[] d = stringMonthYear.split(" ");
		
		// convert String value of year to Integer value of year & store it into date Array.
		date[1] = Integer.parseInt(d[1]);
		
		Map<String, Integer> map = new HashMap<String, Integer>(12);
		
		map.put("January", 1);
		map.put("February", 2);
		map.put("March", 3);
		map.put("April", 4);
		map.put("May", 5);
		map.put("June", 6);
		map.put("July", 7);
		map.put("August", 8);
		map.put("September", 9);
		map.put("October", 10);
		map.put("November", 11);
		map.put("December", 12);
		
		// get Integer value of month on the basis of above HashMap.
		date[0] = map.get(d[0]);
		
		// return the Integer date Array (example: "July 2021" --> [7, 2021]).
		return date;
		
	}

	public static void main(String[] args) throws InterruptedException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty().
		WebDriverManager.chromedriver().setup();
		
		WebDriver chrome = new ChromeDriver();
		
		// maximize window & delete all cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// Dynamic wait added.
		chrome.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// open Air India URL.
		chrome.get("https://www.airindia.in/");
		Thread.sleep(1000);
		
		// variables declaration.
		WebElement calendarNxtButton = null;
		List<String> checkSameDateList = new ArrayList<String>();
		String lastDate = null, secondLastDate = null;
		String calendarDateString = null;
		int[] calendarDateInteger = new int [2];
		
		// date provided by the user is split into parts (day, month, year) & converted to Integer.
		String userDate = "15/03/2022";
		String[] userDateParts = userDate.split("/");
		int userDay = Integer.parseInt(userDateParts[0]);
		int userMonth = Integer.parseInt(userDateParts[1]);
		int userYear = Integer.parseInt(userDateParts[2]);
		
		
		// click on the date field to open the calendar.
		chrome.findElement(By.id("_depdateeu1")).click();
		Thread.sleep(1000);
		
		// get the month & year String of the left panel of the calendar.
		calendarDateString = chrome.findElement(By.xpath("//div[@id='ui-datepicker-div']//div[@class='ui-datepicker-group ui-datepicker-group-first']//descendant::div[@class='ui-datepicker-title']")).getText();
		//convert the calendar month & year String that we got in above step to Integer Array.
		calendarDateInteger = stringToIntegerDate(calendarDateString);
		// store the calendar month & year String into a ArrayList named checkSameDateList.
		checkSameDateList.add(calendarDateString);
		
		// iterate over the month & year of calendar till we reach on the correct month.
		while(userMonth != calendarDateInteger[0] || userYear != calendarDateInteger[1]) {
			
			calendarNxtButton = chrome.findElement(By.xpath("//a[@title='Next']"));
			calendarNxtButton.click();
			calendarDateString = chrome.findElement(By.xpath("//div[@id='ui-datepicker-div']//div[@class='ui-datepicker-group ui-datepicker-group-first']//descendant::div[@class='ui-datepicker-title']")).getText();
			calendarDateInteger = stringToIntegerDate(calendarDateString);
			
			// store the calendar month & year String into a ArrayList named checkSameDateList.
			checkSameDateList.add(calendarDateString);
			lastDate = checkSameDateList.get(checkSameDateList.size() - 1);
			secondLastDate = checkSameDateList.get(checkSameDateList.size() - 2);
			
			// Condition to check if the while loop runs infinitely then break it.
			if(lastDate.contains(secondLastDate))
				break;
			
		}
		
		// after reaching on the correct month & year, click on the date stored in the userDate variable using its xpath.
		if(userMonth == calendarDateInteger[0] || userYear != calendarDateInteger[1]) {
			chrome.findElement(By.xpath("//div[@id='ui-datepicker-div']//div[@class='ui-datepicker-group ui-datepicker-group-first']//descendant::td//a[contains(text()," + userDateParts[0] + ")]")).click();
		}
		else {
			chrome.findElement(By.xpath("//div[@id='ui-datepicker-div']//div[@class='ui-datepicker-group ui-datepicker-group-last']//descendant::td//a[contains(text()," + userDateParts[0] + ")]")).click();
		}
		
		
		
		// **************** Below is another method to select the date from calendar using JavascriptExecutor.****************
		
		/*
		
		WebElement dateFieldElement = chrome.findElement(By.id("_depdateeu1"));
		JavaScriptExecutorConcept.selectDateByJS(chrome, dateFieldElement, userDate);
		
		*/
		
		
		
		// **************** Below is another method to select the date from calendar using for loops.****************
		
		/*
		
		//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[1]/td[1]
		//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[1]/td[7]
		
		//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[2]/td[1]
		//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[2]/td[7]
				
		// ............
				
		//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[1]
		//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[7]
		
		
		Thread.sleep(2000);
		
		// divide the Xpath for the date element of calendar into beforeXpath & afterXpath Strings & then iterate over rows & columns of the calendar to find the date provided by the user.
		String beforeXpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[";
		String afterXpath = "]/td[";
		
		String userDate1 = "15/March/2022";
		String d[] = userDate1.split("/");
		String day1 = d[0], month1 = d[1], year1 = d[2];
		String dayVal = null;
		final int totalWeekDays = 7;
		boolean flag = false;
		
		// iterate over the rows & columns of the calendar & click on the date provided by the user.
		for(int rowNum=1; rowNum<=5; rowNum++) {
			
			for(int colNum=1; colNum<=totalWeekDays; colNum++) {
				
				try {
					dayVal = chrome.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).getText();
				}
				catch(Exception e) {
					System.out.println("Wrong Date");
					flag = false;
					break;
				}

				if(dayVal.equals(day1)) {
					chrome.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).click();
					flag = true;
					break;
				}
			}
			
			if(flag) {
				break;
			}
			
		}
		
		*/
		
		
		// close the browser after 3 seconds.
		Thread.sleep(3000);
		chrome.quit();
		
	}

}