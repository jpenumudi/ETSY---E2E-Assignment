package com.etsy.Tests;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.etsy.Base.TestBase;
import com.etsy.Pages.HomePage;
import com.etsy.Pages.Register;
import com.relevantcodes.extentreports.LogStatus;

public class RegisterTest extends TestBase {
	
	Register regstr;
	HomePage homepg; 
	
	@BeforeMethod  
	public void setup()
	{
		// CALL THE INITIALIZATION METHOD				
		initialization(); 
		System.out.println("Browser is up and running AND Application is launched successfully");
		Log.info("Browser is up and running AND Application is launched successfully");
		regstr = new Register(); // this object (regstr) will let us access all the methods of Register class. 
	}
	
	
// Verifying the heading on the Register page 
		
	@Test (priority=1, description="Verify that the heading on the Register page is Create your account and assert that the actual heading and"
			+ "and expected heading are same")
	public void verifyHeadingOnRegisterPage(Method method)
	{
		//Thread.sleep(2000); 
		test = report.startTest(method.getName()); 
		System.out.println("Name of the testcase is: " +method.getName()); 
		Log.info("Name of the testcase is: " +method.getName()); 
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName()); 
		String Heading = regstr.validateHeading(); 
		System.out.println("Heading found is: " +Heading);
		Log.info("Heading found is:" + Heading );
		test.log(LogStatus.INFO, "Heading found is: " + Heading); // creates the log in extent report. 
		Assert.assertEquals(Heading, "Create your account", "Please check your actual and expected text -- "); 
		
		System.out.println("RegisterPageTest: 1- Validated heading on the register page");
		Log.info("Check --RegisterPageTest: 1- Validated heading on the register page"); 
		test.log(LogStatus.INFO, "RegisterPageTest: 1- Validated heading on the register page"); 
		
	}
			
	
// When terms of use link is clicked --> Terms of link page should be displayed. 
	
	@Test(priority=2, description="Check the terms of use link navigates to terms of link page")	
	public void verifyTermsOfUseLink(Method method)
	{	
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName()); 
		regstr.ValidateTermsLink();
		System.out.println("RegisterPageTest: 2 - Validated Terms of Use link");
		Log.info("RegisterPageTest: 2 - Validated Terms of Use link");
		test.log(LogStatus.INFO, "RegisterPageTest: 2 - Validated Terms of Use link"); 
	} 
	

// when email, firstname, password are entered and register button is clicked --> account should be created 
// and then the landing page would be homepage. 

	@Test(priority=3, description="Confirm that an account is created when the required fields are entered and register button clicked")
	public void verifyCreateAccountFunctionality(Method method)
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		homepg = regstr.createaccount(prop.getProperty("email"), prop.getProperty("firstname"), prop.getProperty("password"));
		System.out.println("RegisterPageTest: 3 - Created an account successfully");
		Log.info("RegisterPageTest: 3 - Created an account successfully");
		test.log(LogStatus.INFO, "RegisterPageTest: 3 - Created an account successfully"); 
	}  
	
}
