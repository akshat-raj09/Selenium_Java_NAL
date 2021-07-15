package com.screener.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.screener.qa.base.TestBase;

import io.qameta.allure.Step;

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
	
	@Step("get title of webpage step....")
	public String validateScreensPageTitle() {
		return driver.getTitle();
	}
	
	@Step("get username label step....")
	public String validateUserNameLabel() {
		return userNameLabel.getText().trim();
	}
	
	@Step("get create screen link text step....")
	public String validateCreateScreenLinkText() {		
		return createScreenLinkText.getText();
	}
	
	@Step("clicking on create screen link step....")
	public CreateScreenPage clickOnCreateScreenLink() {
		createScreenLinkText.click();
		return new CreateScreenPage();
	}

}
