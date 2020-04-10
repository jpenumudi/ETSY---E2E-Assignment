package com.etsy.Tests;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.etsy.Base.TestBase;
import com.etsy.Pages.HomePage;
import com.etsy.Pages.SignIn;
import com.relevantcodes.extentreports.LogStatus;

public class SignInTest extends TestBase {
	
	// SigninPageTest class inherits the properties of TestBaseClass and uses those properties when called. 
	
	SignIn signin;
	HomePage homepg; 
	
	@BeforeMethod
	public void setup() 
	{
		// CALL THE INITIALIZATION METHOD
		
		initialization();  // browser is invoked and url is launched. 
		System.out.println("Browser is up and running AND Application is launched successfully");
		Log.info("Browser is up and running AND Application is launched successfully");
		
		// INITIALIZING THE SIGNIN PAGE
		
		signin = new SignIn();	// this object will let us access all the methods of sign in class. 
		
	}
	
	// Verify that Register button is present on the signin page
	
	@Test(priority=4)	
	public void verifyRegisterButtonIsPresent(Method method)
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		boolean result = signin.validateRegisterButton();
		Assert.assertTrue(result);	
		System.out.println("SigninPageTest: 1 - Validated the presence of register button on Signin Page");
		Log.info("SigninPageTest: 1 - Validated the presence of register button on Signin Page");
		test.log(LogStatus.INFO, "SigninPageTest: 1 - Validated the presence of register button on Signin Page"); 
	}  
	
	// Verify that when forgot password link is clicked --> reset your password page should be displayed. 
	
	@Test(priority=5)	
	public void verifyForgotPwdLink(Method method)	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		signin.validateForgotPwdLink();		
		System.out.println("SigninPageTest: 2 - Validated forgot password link");	
		Log.info("SigninPageTest: 2 - Validated forgot password link");
		test.log(LogStatus.INFO, "SigninPageTest: 2 - Validated forgot password link"); 
	}
	
	// When email and password are enter signup process should finish and home page should be displayed.  
	  	
	@Test(priority=6)
	public void verifyLoginFunctionality(Method method)
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		homepg = signin.login(prop.getProperty("email"), prop.getProperty("password"));		
		System.out.println("SigninPageTest: 3 - Logged into etsy.com site and navigated to the home page");	
		Log.info("SigninPageTest: 3 - Logged into etsy.com site and navigated to the home page");
		test.log(LogStatus.INFO, "SigninPageTest: 3 - Logged into etsy.com site and navigated to the home page"); 
	}

}
