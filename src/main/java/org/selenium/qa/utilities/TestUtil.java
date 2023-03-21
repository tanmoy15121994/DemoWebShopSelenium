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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.qa.baseclass.BaseClass;

public class TestUtil extends BaseClass {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

//Explicit Wait 
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void elementToBeClickable(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}

	public void elementToBeSelectable(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeSelected(findBy));
	}

	public void alertIsPresent(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
	}

//	JavaScriptExecutor

	public void JavaScriptExecuter(String code) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(code);
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
				String filename = System.getProperty("user.dir") + File.separator + "Extentreport"+ File.separator + "index.html";
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename);
				multipart.addBodyPart(messageBodyPart);
				// Send the complete message parts
				mimeMessage.setContent(multipart);
				// sending mail
				Transport.send(mimeMessage);
				log.info("Mail Send Successfully");
			}   catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
