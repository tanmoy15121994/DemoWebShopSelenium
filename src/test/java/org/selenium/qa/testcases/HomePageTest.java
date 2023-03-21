package org.selenium.qa.testcases;

import org.apache.commons.mail.EmailException;
import org.selenium.qa.baseclass.ExtentReport;
import org.selenium.qa.pages.HomePage;
import org.selenium.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends ExtentReport {
	LoginPage loginpage;
	HomePage homepage;

	public HomePageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 0)
	public void verifyHomePageTitle() {
		childtest = parenttest.createNode("verifyHomePageTitle");
		String homePageTitle = homepage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "Cogmento CRMA", "Home Page Title is Invalid");
        log.info("Successfully ValidateHomePageTitle");

	}

	@Test(priority = 1)
	public void verifyUserName() {
		childtest = parenttest.createNode("verifyUserName");
		Assert.assertTrue(homepage.verifyPageUsername());
        log.info("Successfully ValidateUserName");
	}

	@AfterClass
	public void teraDown() throws EmailException {
		driver.quit();

	}
}
