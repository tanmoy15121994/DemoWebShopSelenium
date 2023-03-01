package org.selenium.qa.testcases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.selenium.qa.baseclass.BaseClass;
import org.selenium.qa.pages.ContactsPage;
import org.selenium.qa.pages.HomePage;
import org.selenium.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseClass {
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactspage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginpage = new LoginPage();
		contactspage = new ContactsPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 0)
	public void verifyHomePageTitle() {
		String homePageTitle = homepage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "Cogmento CRM", "Home Page Title is Invalid");
	}

	@Test(priority = 1)
	public void verifyUserName() {
		Assert.assertTrue(homepage.verifyPageUsername());
	}

	@Test(priority = 2)
	public void verifyContactsPage() {
		contactspage = homepage.clickContacts();
	}

	@AfterMethod
	public void teraDown() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(8000));
		driver.quit();
	}

}
