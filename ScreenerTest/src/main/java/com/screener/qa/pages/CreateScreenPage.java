package com.screener.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.screener.qa.base.TestBase;

import io.qameta.allure.Step;

public class CreateScreenPage extends TestBase {
	
	// Page Factory or object repository
	
	@FindBy(xpath="//button[@class='account']")
	@CacheLookup
	WebElement userNameLabel;
	
	@FindBy(xpath="//button[@type='submit' and @class='button-primary']")
	@CacheLookup
	WebElement runQueryBtn;
	
	@FindBy(xpath="//textarea[@name='query']")
	@CacheLookup
	WebElement queryField;
		
	// Initializing the page objects
		
	public CreateScreenPage() {
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	
	@Step("get title of webpage step....")
	public String validateCreateScreenPageTitle() {
		return driver.getTitle();
	}
	
	@Step("get username label step....")
	public String validateUserNameLabel() {
		return userNameLabel.getText().trim();
	}
	
	@Step("creating new screen step by using query: {0}")
	public void validateCreateNewScreen(String query) {
		queryField.sendKeys(query);
		runQueryBtn.click();
	}
		

}
