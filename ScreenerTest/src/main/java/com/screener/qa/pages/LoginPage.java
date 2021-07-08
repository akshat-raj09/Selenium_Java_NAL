package com.screener.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.screener.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	// Page Factory or Object Repository
	
	@FindBy(id="id_username")
	WebElement username;
	
	@FindBy(id="id_password")
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[@class='logo-holder']//child::img[@class='logo' and @alt='Screener Logo']")
	WebElement screenerLogo;
	
	// Initializing the page objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	public String validateLoginPageTitle() {
		
		return driver.getTitle();
		
	}
	
	public boolean validateScreenerLogo() {
		
		return screenerLogo.isDisplayed();
		
	}
	
	public HomePage login() {
		
		username.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		loginBtn.click();
		
		return new HomePage();
		
	}

}
