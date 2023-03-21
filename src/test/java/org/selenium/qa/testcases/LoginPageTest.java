package org.selenium.qa.testcases;

import org.apache.commons.mail.EmailException;
import org.selenium.qa.baseclass.ExtentReport;
import org.selenium.qa.pages.HomePage;
import org.selenium.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest extends ExtentReport {

	LoginPage loginpage;
	HomePage homepage;

	@BeforeClass
	public void setUp() {
		initialization();
		loginpage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		childtest = parenttest.createNode("loginPageTitleTest");
		String title = loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "Cogmento CRM");
		log.info("Successfully validateLoginPageTitle");
	}

	@Test(priority = 2)
	public void loginTest() {
		childtest = parenttest.createNode("loginTest");
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		log.info("Login Successfully");
	}

	@AfterClass
	public void teraDown() throws EmailException {
		driver.quit();
		
	}
}
