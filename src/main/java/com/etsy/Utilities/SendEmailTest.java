package com.etsy.Utilities;

//import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;*/
 
class SendEmail {
 
    public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");      
        properties.put("mail.smtp.starttls.enable", "true");       
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
        
        // properties.put("mail.smtp.port", "465");
        // properties.put("mail.smtp.socketFactory.port", port);
       //  properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 
        // creates a new session with an authenticator
        
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
} 
public class SendEmailTest {
	public static void main(String[] args) {
		
		// SMTP info
       // String host = "smtp.gmail.com";
		String host = "smtp.mail.yahoo.com";
        String port = "485";
        
        
        String mailFrom = "jpenumudi@yahoo.com";
        String password = "Jyoth!vjabsr";
 
        // message info
        String mailTo = "vpenumudi@yahoo.com";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";
 
        // attachments
        String[] attachFiles = new String[1];
        attachFiles[0] = "C:\\Users\\17327\\workspace\\E2EPOMProjectWithTestNG\\E2EPOMTestNGProject\\ExtentReports\\EtsyExtentReport.html";
 
        try {
            SendEmail.sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
             System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
		
	} 
 
}



//********************************************************************************************************************************************

/*

public static void sendEmailWithAttachment(String msg) {

// Recipient's email ID needs to be mentioned.
String to = prop.getProperty("emailto");

// Sender's email ID needs to be mentioned
String from = prop.getProperty("gmailaccount");

// Assuming you are sending email from through gmails smtp
String host = "smtp.gmail.com";

// Get system properties
Properties properties = System.getProperties();

// Setup mail server
properties.put("mail.smtp.host", host);
properties.put("mail.smtp.port", "465");
properties.put("mail.smtp.ssl.enable", "true");
properties.put("mail.smtp.auth", "true");

// Get the Session object.// and pass 
Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

    protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication(prop.getProperty("gmailaccount"), prop.getProperty("emailpwd"));

    }

});
//session.setDebug(true);
try {
    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(session);

    // Set From: header field of the header.
    message.setFrom(new InternetAddress(from));

    // Set To: header field of the header.
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

    // Set Subject: header field
    message.setSubject("This is the Subject Line!");

    Multipart multipart = new MimeMultipart();

    MimeBodyPart attachmentPart = new MimeBodyPart();

    MimeBodyPart textPart = new MimeBodyPart();

    try {

        File f =new File("C:\\Users\\17327\\workspace\\E2EPOMProjectWithTestNG\\E2EPOMTestNGProject\\Screenshots\\verifyCheckBox2020_04_08.png");

        attachmentPart.attachFile(f);
        textPart.setText(msg);
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

    } catch (IOException e) {

        e.printStackTrace();

    }

    message.setContent(multipart);

    System.out.println("sending...");
    // Send message
    Transport.send(message);
    System.out.println("Sent message successfully....");
} catch (MessagingException mex) {
    mex.printStackTrace();
}
}
 */

//***********************************************************************************************************************************************



/*public static void sendDetailedEmail(String msg) throws EmailException {
	
	
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
	email.setFrom(prop.getProperty("emailfrom"));  // sender's email
	//System.out.println(prop.getProperty("gmailaccount"));				
	email.setSubject("Automation Test Report");
	email.setMsg(msg);
	email.addTo(prop.getProperty("emailto")); // recipient's email	
	//System.out.println(prop.getProperty("emailto"));
	email.attach(attachment);	
	email.send();
	System.out.println("Email Sent");
					
	} */