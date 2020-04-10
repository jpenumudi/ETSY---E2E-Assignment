package com.etsy.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.etsy.Base.TestBase;
import com.etsy.Utilities.TestUtil;

public class Register extends TestBase {

	// CREATE PAGE FACTORY OR OBJECT REPOSITORY BY USING @FINDBY ANNOTATION
	
			@FindBy(xpath="//*[@id='gnav-header-inner']/div[4]/nav/ul/li[1]/button")
			WebElement Signin;			
			
			@FindBy(xpath="//*[@id='join-neu-form']/div[1]/div/div[1]/div/button")
			WebElement Register;
			
			//@FindBy(xpath = "//h1[@id='join-neu-overlay-title']")
			@FindBy(xpath = "//h1[@name='join-neu-overlay']") // incomplete xpath --> to fail the test case
			//@FindBy(id="join-neu-overlay-title") // incomplete id ==> to fail the test case
			WebElement heading;
			
			@FindBy(xpath="//input[@id='join_neu_email_field']")
			WebElement email;
			
			@FindBy(xpath="//input[@id='join_neu_first_name_field']")
			WebElement firstname;
			
			@FindBy(xpath="//input[@id='join_neu_password_field']")
			WebElement password;
			
			@FindBy(xpath="//button[@name='submit_attempt']")
			WebElement RegBtn;
			
			@FindBy(linkText = "Terms of Use")
			WebElement termsofuselink;  
							
			
		//INITIALIZING THE PAGE OBJECTS
			
			public Register() // CONSTRUCTOR CLASS	
			{
				PageFactory.initElements(driver, this); // initElements method is used to initialize the page factory. 
				//this.driver = driver;								
			}
			
			
		//ACTIONS PERFORMED ON THE ELEMENTS DEFINED ABOVE. 
			
			// VALIDATE THE HEADING ON REGISTER PAGE  -- IS THIS CORRECT 
			
				public String validateHeading() 
				{		
					String headingtext =" ";
					Signin.click();
					Register.click();
					try{
					headingtext = heading.getText();
					}catch (Exception e){
						System.out.println(":: ::" +e.getMessage());
						Log.error(":: ::"+e.getMessage());
					}  
					return headingtext;
					 
				}
				
				
			// VALIDATE THE TERMS TO USE LINK (SHOULD NAVIGATE TO TERMS TO LINK PAGE)
				
				public void ValidateTermsLink()
				{
					Signin.click();
					Register.click();
					termsofuselink.click();
					return;	
				}
				
			// Validate Create account functionality --> when email, firstname, password are entered and register button is clicked 
			// --> account should be created 
				
				public HomePage createaccount(String emailadd, String fname, String pwd)
				{
					Signin.click();
					Register.click();
					TestUtil.waitForSendingKeys(driver, email, 20);
					email.sendKeys(emailadd);
					firstname.sendKeys(fname);
					password.sendKeys(pwd); 
					RegBtn.click();	
					
					return new HomePage();
				
				}	


}
