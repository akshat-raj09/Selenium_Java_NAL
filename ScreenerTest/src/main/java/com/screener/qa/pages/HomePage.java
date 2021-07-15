package com.screener.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.screener.qa.base.TestBase;

import io.qameta.allure.Step;

public class HomePage extends TestBase {
	
	// Page Factory or object repository
	
	@FindBy(xpath="//button[@class='account']")
	@CacheLookup
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Screens')]")
	@CacheLookup
	WebElement screensLink;
	
	// Initializing the page objects
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	
	@Step("get title of webpage step....")
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	@Step("get username label step....")
	public String validateUserNameLabel() {
		return userNameLabel.getText().trim();
	}
	
	@Step("click on screens link step....")
	public ScreensPage clickOnScreensLink() {
		screensLink.click();
		return new ScreensPage();
	}

}