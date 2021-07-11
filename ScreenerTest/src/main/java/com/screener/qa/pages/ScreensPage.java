package com.screener.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.screener.qa.base.TestBase;

public class ScreensPage extends TestBase {
	
	// Page Factory or object repository
	
	@FindBy(xpath="//button[@class='account']")
	@CacheLookup
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[@class='button button-primary']")
	@CacheLookup
	WebElement createScreenLinkText;
	
	// Initializing the page objects
	
	public ScreensPage() {
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	
	public String validateScreensPageTitle() {
		return driver.getTitle();
	}
	
	public String validateUserNameLabel() {
		return userNameLabel.getText().trim();
	}
	
	public String validateCreateScreenLinkText() {		
		return createScreenLinkText.getText();
	}
	
	public CreateScreenPage clickOnCreateScreenLink() {
		createScreenLinkText.click();
		return new CreateScreenPage();
	}

}
