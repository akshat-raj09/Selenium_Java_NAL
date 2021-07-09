package com.screener.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.screener.qa.base.TestBase;

public class HomePage extends TestBase {
	
	// Page Factory or object repository
	
	@FindBy(xpath="//button[@class='account']")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Screens')]")
	WebElement screensLink;
	
	// Initializing the page objects
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	public String validateUserNameLabel() {
		return userNameLabel.getText().trim();
	}
	
	public ScreensPage clickOnScreensLink() {
		screensLink.click();
		return new ScreensPage();
	}

}