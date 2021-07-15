package com.screener.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.screener.qa.base.TestBase;

import io.qameta.allure.Step;

public class SignupPage extends TestBase {
	
	// Page Factory or Object Repository

	@FindBy(id="id_email")
	@CacheLookup
	WebElement email;

	@FindBy(id="id_email2")
	@CacheLookup
	WebElement reEnterEmail;

	@FindBy(id="id_password")
	@CacheLookup
	WebElement password;

	@FindBy(xpath="//button[@type='submit']")
	@CacheLookup
	WebElement createAccountBtn;

	@FindBy(xpath="//div[@id='desktop-search']//descendant::input[@class='u-full-width']")
	WebElement searchCompany;

	// Initializing the page objects
	public SignupPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	
	@Step("get title of webpage step....")
	public String validateSignupPageTitle() {

		return driver.getTitle();

	}

	@Step("get create account button text step....")
	public String validateCreateAccountBtnText() {

		return createAccountBtn.getText().trim();

	}

	@Step("fill the create account form step....")
	public void fillCreateAccountForm(String formEmail, String pwd) {

		email.sendKeys(formEmail);
		reEnterEmail.sendKeys(formEmail);
		password.sendKeys(pwd);

	}

	@Step("get status of search company input field step....")
	public boolean searchCompanyFieldEnabled() {

		return searchCompany.isEnabled();

	}

}
