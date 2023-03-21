package org.selenium.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends org.selenium.qa.baseclass.BaseClass {

	// pageFatcory

	@FindBy(xpath = "//input[@name='email']")
	WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password;

	@FindBy(xpath = "//div[contains(@class, 'submit button')]")
	WebElement loginButton;

	@FindBy(xpath = "//a[contains(@href,'password')]")
	WebElement forgotPassword;

	By button = By.xpath("//div[contains(@class, 'submit button')]");

	// Initializing the Page Object
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public HomePage login(String uname, String pass) {

		username.sendKeys(uname);
		password.sendKeys(pass);

		loginButton.click();

		return new HomePage();
	}

}
