package org.selenium.qa.utilities;

import java.io.File;
import java.time.Duration;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.qa.baseclass.BaseClass;

public class TestUtil extends BaseClass {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

// Switch to Frame
	public static void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

	public static void switchToFrame(WebDriver driver, By locator) {
		driver.switchTo().frame(driver.findElement(locator));
	}

// Refresh Page
	public static void doRefresh(WebDriver driver) {
		driver.navigate().refresh();
	}

// FindElement
	public static WebElement findElement(WebDriver driver, By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (NoSuchElementException e) {
			log.info("Element identified by '" + locator + "' was not found on the page.");
		}
		return element;
	}

// SendKeys
	public static void sendKeys(WebDriver driver, By locator, String text) {
		try {
			WebElement element = driver.findElement(locator);
			element.sendKeys(text);
		} catch (NoSuchElementException e) {
			log.info("Element identified by '" + locator + "' was not found on the page.");
		}
	}

// Click
	public static void click(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		element.click();
	}

//Explicit Wait 

	public static WebElement waitForElementToAppear(WebDriver driver, By locator, Duration timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (NoSuchElementException e) {
			log.info("Element identified by '" + locator + "' did not appear on the page within " + timeoutInSeconds
					+ " seconds.");
			return null;
		}
	}

	public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, Duration timeout) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			return element;
		} catch (TimeoutException e) {
			log.info("Element identified by" + locator + " was not clickable within " + timeout + " seconds.");
		}
		return element;
	}

	public static void waitUntilElementIsSelectable(WebDriver driver, By locator, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.elementToBeSelected(locator));
		} catch (TimeoutException e) {
			System.out.println("Element is not selectable or selected within " + timeout + " seconds.");
		}
	}

	public static boolean isAlertPresent(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			log.info("No alert present on the page.");
			return false;
		}
	}

//	JavaScriptExecutor
	public static void executeJavaScript(WebDriver driver, WebElement element, String code) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(code, element);
		} catch (Exception e) {
			log.info("Error executing JavaScript: " + e.getMessage());
		}
	}

	public static void scrollToBottom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollToElement(WebDriver driver, By locator, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
		} catch (TimeoutException e) {
			System.out.println(
					"Element with locator " + locator.toString() + " is not present within " + timeout + " seconds.");
		}
	}
//ThreadSleep Wait	
	public static void threadSleep(long miliSeconds) {
		try {
			Thread.sleep(miliSeconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			log.info(e);
		}
	}

// Send Email
	public static void sendEmail(String FromEmail, String password, String toEmail) {
		if (FromEmail.equals("") || password.equals("") || toEmail.equals("")) {
			log.info("Please check Email details in Properties file");
		} else {
			// Properties prop;
			Session session;
			MimeMessage mimeMessage;
			String HOSTNAME = "smtp.office365.com";
			String STARTTLS_PORT = "587";
			boolean STARTTLS = true;
			boolean AUTH = true;
			String EmailSubject = "Automation Test Execution Report";
			String EmailBody = "Please find the attachment of Automation Test Execution Report";
			try {
				// prop = new Properties();
				prop.put("mail.smtp.host", HOSTNAME);
				// Setting STARTTLS_PORT
				prop.put("mail.smtp.port", STARTTLS_PORT);
				// AUTH enabled
				prop.put("mail.smtp.auth", AUTH);
				// STARTTLS enabled
				prop.put("mail.smtp.starttls.enable", STARTTLS);
				// Authenticating
				Authenticator auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(FromEmail, password);
					}
				};
				// creating session
				session = Session.getInstance(prop, auth);
				// Create Mime Message
				mimeMessage = new MimeMessage(session);
				// from address should exist in the domain
				mimeMessage.setFrom(new InternetAddress(FromEmail));
				mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(toEmail));
				mimeMessage.setSubject(EmailSubject);
				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart();
				// setting text message body
				messageBodyPart.setText(EmailBody);
				// Create a multipart message
				Multipart multipart = new MimeMultipart();
				// Set text message part
				multipart.addBodyPart(messageBodyPart);
				// Part two is attachment
				messageBodyPart = new MimeBodyPart();
				String filename = System.getProperty("user.dir") + File.separator + "Extentreport" + File.separator
						+ "index.html";
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename);
				multipart.addBodyPart(messageBodyPart);
				// Send the complete message parts
				mimeMessage.setContent(multipart);
				// sending mail
				Transport.send(mimeMessage);
				log.info("Mail Send Successfully");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
