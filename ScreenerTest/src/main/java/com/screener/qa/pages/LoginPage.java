package com.screener.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.screener.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	// Page Factory or Object Repository
	
	@FindBy(id="id_username")
	@CacheLookup
	WebElement username;
	
	@FindBy(id="id_password")
	@CacheLookup
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	@CacheLookup
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
	
	public HomePage login(String un, String pwd) {
		
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage();
		
	}

}
