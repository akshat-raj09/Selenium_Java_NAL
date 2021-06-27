package seleniumBasics;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CustomXpathConcept {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Data\\Study Material\\Java\\selenium browser drivers\\chromedriver.exe");		
		WebDriver chrome = new ChromeDriver();
		
		// Maximize window & delete cookies.
		chrome.manage().window().maximize();
		chrome.manage().deleteAllCookies();
		
		// Dynamic Wait Added.
		chrome.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Open desired link.
		chrome.get("http://demo.guru99.com/test/selenium-xpath.html");
		
		// The format of Xpath is like : //<tag_name>[@attribute_of_that_tag='value_of_that_attribute']
		chrome.findElement(By.xpath("//input[@name='uid']")).sendKeys("test msg");
		
		// Using contains(). Template: //<tag_name>[contains(@attribute_of_that_tag, 'value/partial_value_of_that_attribute')]
		chrome.findElement(By.xpath("//input[contains(@name,'password')]")).sendKeys("test msg");
		
		// Handling dynamic element using contains().
		chrome.findElement(By.xpath("//input[contains(@name,'passw')]")).sendKeys("test msg");
		
		// Handling dynamic element using starts-with(). Template: //<tag_name>[starts-with(@attribute_of_that_tag, 'start_value_of_that_attribute')]
		chrome.findElement(By.xpath("//input[starts-with(@name, 'passw')]")).sendKeys("test msg");
		
		// Handling dynamic element using ends-with(). Template: //<tag_name>[ends-with(@attribute_of_that_tag, 'end_value_of_that_attribute')]
		// This ends-with() is not working because ends-with() is supported in xpath 2.0 & most browsers support xpath 1.0 only.
		// Alternative way is : //input[@name[substring(.,string-length(.) - string-length('sword') + 1) = 'sword']]		
		chrome.findElement(By.xpath("//input[ends-with(@name, 'sword')]")).sendKeys("test msg");
		
		// For links using contains() : //<tag_name>[contains(text(),'text_of_that_link')]
		chrome.findElement(By.xpath("//a[contains(text(), 'Accounting')]")).click();
		
		// For links without using contains().
		chrome.findElement(By.xpath("//a[text()='CCNA']")).click();
				
		// For links without using contains(). Also we can use starts-with() or ends-with() with other attributes of <a> tag.
		chrome.findElement(By.xpath("//a[@href='/introduction-ccna.html']")).click();
		
		// Using 'and', 'or' in xpath for applying two or more attributes of a tag.
		chrome.findElement(By.xpath("//a[@href='/introduction-ccna.html' and @title='CCNA']")).click();
		chrome.findElement(By.xpath("//a[@href='/introduction-ccna.html' or @title='CCNA']")).click();
		
		// Xpath Axes ------>>>> Generally used for dynamic elements or for the elements which doesnot have any usable unique property.
				
		// following : Selects everything in the document after the closing tag of the current node.
		// For this example use this URL (https://getbootstrap.com/docs/5.0/components/buttons/)
		chrome.findElement(By.xpath("//button[@class='btn btn-primary']//following::input[1]")).click();
		
		// preceding : selects all nodes that come before the current node in the document, except ancestor, attribute nodes, and namespace nodes. Both the below examples will point to same element.
		// For this example use this URL (https://getbootstrap.com/docs/5.0/components/dropdowns/)
		chrome.findElement(By.xpath("//button[@id='dropdownMenuButton2']//preceding::button[@id='dropdownMenuButton1']")).click();
		chrome.findElement(By.xpath("//button[@id='dropdownMenuButton2']//preceding::button[31]")).click();
		
		// child : Selects all children elements of current node.
		// For this example use this URL (https://getbootstrap.com/docs/5.0/components/dropdowns/)
		chrome.findElement(By.xpath("//div[@class='dropdown']//child::ul[@class='dropdown-menu']//child::li//child::a[text()='Another action']")).click();
		
		// parent : Selects the parent of the current node.
		// For this example use this URL (https://getbootstrap.com/docs/5.0/components/dropdowns/)
		chrome.findElement(By.xpath("//a[@class='dropdown-item' and text()='Something else here']//parent::li//parent::ul[@class='dropdown-menu show']//preceding-sibling::button[@id='dropdownMenuButton1']")).click();
		
		// self : It indicated the current node itself.
		// For this example use this URL (https://getbootstrap.com/docs/4.0/components/collapse/)
		chrome.findElement(By.xpath("//*[@id='search-input']//self::input[1]")).sendKeys("test search");
		
		// ancestor : ancestor selects all the ancestor nodes (parent, grandparent etc.) of the current node.
		// For this example use this URL (https://getbootstrap.com/docs/4.0/components/forms/)
		chrome.findElement(By.xpath("//input[@id='inlineFormInputName']//ancestor::form[1]//child::div[@class='form-row align-items-center']//child::div[@class='col-sm-3 my-1']//child::input[@id='inlineFormInputName']")).sendKeys("test");
		
		// descendant :The descendant axis selects all descendants (children, grandchildren, etc) of the current node.
		// For this example use this URL (https://getbootstrap.com/docs/4.0/components/forms/)
		chrome.findElement(By.xpath("//div[@class='highlight']//following-sibling::div[@class='bd-example']//child::form[@class='form-inline']//descendant::input[@id='inlineFormInputName2']")).sendKeys("test");
		
		// following-sibling : selects all siblings after the current node.
		// For this example use this URL (https://getbootstrap.com/docs/4.0/components/forms/)
		String labelText = chrome.findElement(By.xpath("//div[@class='highlight']//following-sibling::div[@class='bd-example']//child::form[@class='form-inline']//input[@id='inlineFormInputName2']//following-sibling::label[text()='Username']")).getText();
		
		// preceding-sibling : selects all siblings before the current node.
		// For this example use this URL (https://getbootstrap.com/docs/4.0/components/forms/)
		String labelText1 = chrome.findElement(By.xpath("//div[@class='highlight']//following-sibling::div[@class='bd-example']//child::form[@class='form-inline']//input[@id='inlineFormInputName2']//preceding-sibling::label[text()='Name']")).getText();
		
		
		Thread.sleep(5000);
		chrome.quit();
		
	}

}