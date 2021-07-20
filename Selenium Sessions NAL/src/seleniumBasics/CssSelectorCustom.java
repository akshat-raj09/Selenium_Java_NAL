package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CssSelectorCustom {

	public static void main(String[] args) throws InterruptedException {
		
		// using WebDriverManager to setup chrome driver instead of System.setProperty()
		WebDriverManager.chromedriver().setup();

		WebDriver chrome = new ChromeDriver();

		// maximize window & delete all cookies
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();

		// dynamic wait added
		chrome.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		chrome.get("https://guide.blazemeter.com/hc/en-us/articles/207420325-Getting-Started-Getting-Started");
		
		// By using id: html_tag#id, #id
		chrome.findElement(By.cssSelector("#query")).sendKeys("test");
		Thread.sleep(1000);
		chrome.findElement(By.cssSelector("#query")).clear();
		
		chrome.findElement(By.cssSelector("input#query")).sendKeys("test msg");
		Thread.sleep(1000);
		chrome.findElement(By.cssSelector("#query")).clear();
		
		
		// By using class: html_tag.c1.c2.c3.....cn, .classname, c1.c2.c3.....cn
		// By using html_tag##id.classname.classname....classname
		System.out.println(chrome.findElement(By.cssSelector("header.article-header.clearfix")).getText());
		System.out.println(chrome.findElement(By.cssSelector(".article-header.clearfix")).getText());
		
		// By using attributes html_tag[id='value'], html_tag[id='value'][type='value'].....
		chrome.findElement(By.cssSelector("input[id='query'][type='search']")).sendKeys("test search");
		Thread.sleep(1000);
		chrome.findElement(By.cssSelector("input[id='query'][type='search']")).clear();
		
		// By using parent_tag>child_tag
		System.out.println(chrome.findElement(By.cssSelector("li[class='201429665']>a")).getText());
		
		// using contains() in css selector
		chrome.findElement(By.cssSelector("input[id*='qu']")).sendKeys("test search");
		Thread.sleep(1000);
		chrome.findElement(By.cssSelector("input[id*='qu']")).clear();
		
		// using starts-with() in css selector
		chrome.findElement(By.cssSelector("input[id^='qu']")).sendKeys("test search");
		Thread.sleep(1000);
		chrome.findElement(By.cssSelector("input[id^='qu']")).clear();
		
		// using ends-with() in css selector
		chrome.findElement(By.cssSelector("input[id$='ry']")).sendKeys("Test 123");
		Thread.sleep(1000);
		chrome.findElement(By.cssSelector("input[id$='ry']")).clear();
		
		// first-of-type in css
		System.out.println(chrome.findElement(By.cssSelector("ul[id='categories']>li:first-of-type")).getText());
		
		// last-of-type in css
		System.out.println(chrome.findElement(By.cssSelector("ul[id='categories']>li:last-of-type")).getText());
		
		// nth-of-type() in css
		System.out.println(chrome.findElement(By.cssSelector("ul[id='categories']>li:nth-of-type(4)")).getText());
		System.out.println(chrome.findElement(By.cssSelector("ul[id='categories']>li:nth-of-type(n)")).getText());
		
		// sibling of element: this will return the immediate next sibling
		System.out.println(chrome.findElement(By.cssSelector("ul[id='categories']>li:first-of-type+li")).getText());
		System.out.println(chrome.findElement(By.cssSelector("a[id='go_to_blazemeter_button']+a")).getText());
		
		// not in css
		System.out.println(chrome.findElement(By.cssSelector("a#go_to_blazemeter_button:not(free_training_button)")).getText());
		
		
		// close the browser after 3 seconds
		Thread.sleep(3000);
		chrome.quit();
	}

}
