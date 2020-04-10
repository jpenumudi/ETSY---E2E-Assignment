package com.etsy.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.etsy.Base.TestBase;

public class TestUtil extends TestBase {
	
	public static long page_load_timeout = 60;
	public static long implicit_wait = 30;
	

// EMAIL METHOD -- simple email --> no attachments or screenshots
	
	public static void sendEmail(String msg) throws EmailException {
						
		Email simpleemail = new SimpleEmail();
						
		simpleemail.setHostName("smtp.gmail.com");
		simpleemail.setSmtpPort(465);
		simpleemail.setAuthenticator(new DefaultAuthenticator(prop.getProperty("username"), prop.getProperty("emailpwd")));
		//System.out.println(prop.getProperty("username"));
		//System.out.println(prop.getProperty("emailpwd"));
		simpleemail.setSSLOnConnect(true);
		simpleemail.setFrom(prop.getProperty("emailfrom"));  // sender's email
		//System.out.println(prop.getProperty("gmailaccount"));				
		simpleemail.setSubject("Selenium Test Report");
		simpleemail.setMsg(msg);
		simpleemail.addTo(prop.getProperty("emailto")); // recipient's email	
		//System.out.println(prop.getProperty("emailto"));
		simpleemail.send();
		//System.out.println("Message Sent");
				
		} 
	

// EMAIL 2 -- this method is used to send an email with attachement, screenshot after all the tests are executed in the suite.

		
	public static void sendDetailedEmail(String msg) throws EmailException {
		
				
		System.out.println("Emailing the test results");
		
		EmailAttachment attachment = new EmailAttachment();
		
		//attachment.setPath("test-output\\emailable-report.html"); // to attach the emailable report from test-output folder
		attachment.setPath("C:\\Users\\17327\\workspace\\POMProjects\\POMProject4_Reports\\ExtentReports\\EtsyExtentReport.html"); // to attach the extent report 
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Automation Test Report");
		attachment.setName("Etsy.html");
				
		MultiPartEmail email = new MultiPartEmail();		
			
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(prop.getProperty("username"), prop.getProperty("emailpwd")));
		//System.out.println(prop.getProperty("username"));
		//System.out.println(prop.getProperty("emailpwd"));
		email.setSSLOnConnect(true);
		email.setFrom(prop.getProperty("emailto"));  // sender's email
		//System.out.println(prop.getProperty("gmailaccount"));				
		email.setSubject("Automation Test Report");
		email.setMsg(msg);
		email.addTo(prop.getProperty("emailfrom")); // recipient's email	
		//System.out.println(prop.getProperty("emailto"));
		//email.attach(attachment);	
		email.send();
		System.out.println("Email Sent");
						
		} 
	
	
// SCREENSHOT - 1 --> Captures screenshot and returns the screenshot path  --> for taking screenshots at the end of the test. 
	 	
	public static String pullScreenshotPath()  {

		String destination=null;
	
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
					destination = System.getProperty("user.dir")+"\\"+ "Screenshots"+"\\"+ System.currentTimeMillis()+".png";
					FileUtils.copyFile(scrFile, new File(destination));
			}
			catch(Exception e) {
				e.printStackTrace();
			}

		return destination;
	}  
								
// SCREENSHOT - 2 
	
	public static String getScreenshot(WebDriver driver, String ScreenshotName) throws IOException{
		
		//System.out.println("Screenshot name: " + ScreenshotName);
		
		String dateName = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);	
		String destination = System.getProperty("user.dir")+"./Screenshots/" + ScreenshotName + dateName + ".png";
		
		//String destination =  "./Screenshots/" +ScreenshotName + ".png"; // the file has been saved in the .png format. 
		//String destination =   System.getProperty("user.dir")+"\\"+ "Screenshots" +"\\" +ScreenshotName + ".png"; 		
		//System.out.println("The value of user.dir is: " + System.getProperty("user.dir"));
		//System.out.println("Destination is: " + destination);
		
		File finalDestination = new File(destination);
						
		try {
			FileUtils.copyFile(source, finalDestination);
			}catch (IOException e) 
			{
				System.out.println("Capture Failed" + e.getMessage());
			}				
				return destination;
			}	

// APPENDING SCREENSHOTS IN THE EXTENT REPORT 
	
// Gives a base64 image which is used to append the screenshots in the extent report.

	public static String getBase64Image(String screenshotpath) {
		String base64 = null;
		try {
			InputStream is= new FileInputStream(screenshotpath);
			byte[] imageBytes = IOUtils.toByteArray(is);
			base64 = Base64.getEncoder().encodeToString(imageBytes);
		}
		catch (Exception e) {

		}
		return base64;
	}  
		
// EXPLICIT WAIT FOR CLICK ACTION (ON ANY ELEMENT)
	
		public static WebDriver waitForElementClick(WebDriver driver, WebElement element, int timeout)
		{
			new WebDriverWait(driver, timeout).
			until(ExpectedConditions.elementToBeClickable(element));			
			return driver;
		}
		
// EXPLICIT WAIT FOR SENDING DATA TO ANY ELEMENT. 
		
		public static WebDriver waitForSendingKeys(WebDriver driver, WebElement element, int timeout)
		{
			new WebDriverWait(driver, timeout).
			until(ExpectedConditions.visibilityOf(element));			
			return driver;
		}

// ELEMENT DISPLAYED OR NOT 
		
		public static void waitForDisplayElement(WebElement element)
		{
			boolean elementDisplayed = element.isDisplayed();
			if(elementDisplayed)
			{
				System.out.println("Element is Displayed");
			}
			else
			{
				System.out.println("Element is not Displayed");
			}
		}
		
//ELEMENT ENABLED OR NOT 
		
		public static void enableElement(WebElement element)
		{
			boolean elementEnabled = element.isEnabled();
			if(elementEnabled)
			{
				System.out.println("Element is Enabled in Page");
			}
			else
			{
				System.out.println("Element is not Enabled in Page");
			}
		}

}
