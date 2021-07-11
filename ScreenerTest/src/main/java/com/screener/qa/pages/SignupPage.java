package com.screener.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.screener.qa.base.TestBase;

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
		public String validateSignupPageTitle() {
			
			return driver.getTitle();
			
		}
		
		public String validateCreateAccountBtnText() {
			
			return createAccountBtn.getText().trim();
			
		}
		
		public void fillCreateAccountForm(String formEmail, String pwd) {
			
			email.sendKeys(formEmail);
			reEnterEmail.sendKeys(formEmail);
			password.sendKeys(pwd);
			
		}
		
		public boolean searchCompanyFieldEnabled() {
			
			return searchCompany.isEnabled();
		
		}

}
