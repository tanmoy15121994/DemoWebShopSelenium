package org.selenium.qa.baseclass;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.qa.utilities.TestUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport extends BaseClass {

	public static ExtentReports extent = new ExtentReports();
	public static ExtentSparkReporter spark;
	public static ExtentTest extenttest;
	public static ExtentTest parenttest;
	public static ExtentTest childtest;

	@BeforeSuite(alwaysRun = true)
	public void setReport() {
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "Extentreport/index.html");
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "Tanmoy");
		extent.setSystemInfo("User Name", "Tanmoy Mondal");
		extent.setSystemInfo("Environment", "QA");
	}

	@BeforeClass(alwaysRun = true)
	public void getTestClassName() {
		parenttest = extent.createTest(getClass().getName());
		System.out.println("Test scenario create in Extent report");
	}

	public static String captureScreenshot(WebDriver driver) throws IOException {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "./FailedScreenShots/" + " " + timestamp + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(scrFile, finalDestination);
		return destination;

	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			childtest.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
			childtest.fail(result.getThrowable());
			String screenshotPath = captureScreenshot(driver);
			childtest.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			childtest.log(Status.SKIP, "Test Case SKIPPED is" + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			childtest.log(Status.PASS, "Test Case PASSED is" + result.getName());
		}

	}

	@AfterSuite(alwaysRun = true)
	public void endExtentReport() {
		driver.quit();
		extent.flush();
		TestUtil.sendEmail(prop.getProperty("EmailFrom"), prop.getProperty("EmailPassword"),
				prop.getProperty("EmailTo"));

	}

}
