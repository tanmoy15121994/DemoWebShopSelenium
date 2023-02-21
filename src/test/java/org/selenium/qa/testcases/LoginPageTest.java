package org.selenium.qa.testcases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.selenium.qa.baseclass.BaseClass;
import org.selenium.qa.pages.HomePage;
import org.selenium.qa.pages.LoginPage;
import org.selenium.qa.utilities.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseClass {
	
	
	LoginPage loginpage;
	HomePage homepage;
	
		
	public LoginPageTest() {
     }
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginpage = new LoginPage();
	}
	 
    @Test(priority=1)
    public void loginPageTitleTest() {
    String title = loginpage.validateLoginPageTitle();
    Assert.assertEquals(title, "Cogmento CRM");
    
    TestUtil.captureScreenshot(driver,"LoginPageTitle");
    
    log.info("Successfully validateLoginPageTitle");
    }
	
  @Test(priority=2)
	public void loginTest() {
	
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
//	Thread.sleep(3000);

    TestUtil.captureScreenshot(driver,"LoginSuccessfull");
    
    log.info("Login Successfully");
    
	}
  
  
	@AfterMethod
	public void teraDown() {
	driver.quit();
	}
}
