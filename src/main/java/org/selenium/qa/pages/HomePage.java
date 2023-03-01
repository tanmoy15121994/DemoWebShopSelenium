package org.selenium.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends org.selenium.qa.baseclass.BaseClass {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='header item']")
	WebElement pageLabel;

	@FindBy(xpath = "//a[contains(@href,'/contacts')]")
	WebElement contacts;

	@FindBy(xpath = "//a[contains(@href,'/companies')]")
	WebElement companies;

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean verifyPageUsername() {
		return pageLabel.isDisplayed();
	}

	public ContactsPage clickContacts() {
		contacts.click();
		return new ContactsPage();
	}

}
