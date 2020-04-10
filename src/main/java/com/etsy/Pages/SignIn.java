package com.etsy.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.etsy.Base.TestBase;
import com.etsy.Utilities.TestUtil;

public class SignIn extends TestBase {

	// CREATE PAGE FACTORY OR OBJECT REPOSITORY BY USING @FINDBY ANNOTATION
	
	@FindBy(xpath="//*[@id='gnav-header-inner']/div[4]/nav/ul/li[1]/button")
	WebElement Signin;
	
	@FindBy(xpath = "//input[@id='join_neu_email_field']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='join_neu_password_field']")
	WebElement password;
	
	@FindBy(xpath="//span[@class='checkbox-label']")
	WebElement checkbox;
	
	@FindBy(xpath="//button[@name='submit_attempt']")
	WebElement signinbutton;
	
	@FindBy(xpath="//form[1]/div[1]/div[1]/div[1]/div[1]/button[1]")
	WebElement RegisterButton;
	
	@FindBy(linkText="Forgot your password?")
	WebElement forgotpwdlink;
	
	
	// INITIALIZING THE PAGE OBJECTS
	
	public SignIn() // CONSTRUCTOR CLASS	
	{
		PageFactory.initElements(driver, this); // initElements method is used to initialize the page factory. 
		// this = current class object 	
	}
	
	// ACTIONS PERFORMED ON THE ELEMENTS DEFINED ABOVE. 
	
	// Validate the presence of register button on the sign in page
	
	public Boolean validateRegisterButton()
	{
		Signin.click();		
		return RegisterButton.isDisplayed();
	}
	
	// VALIDATE FORGOT YOUR PASSWORD LINK --> when this link is clicked --> landing page is reset your password
	
	public void validateForgotPwdLink()
	{
		Signin.click();
		forgotpwdlink.click();	
		
		return;
	}
	
	// Validate the login functionality --> when email and password are entered and signin button is clicked --> homepage should be displayed. 
			
	public HomePage login(String emailadd, String pwd)
	{
		Signin.click();		
		TestUtil.waitForSendingKeys(driver, email, 20);
		email.sendKeys(emailadd);
		password.sendKeys(pwd);
		checkbox.click();
		signinbutton.click();  
					
		return new HomePage();
	}


}
