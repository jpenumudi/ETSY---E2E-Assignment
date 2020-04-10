package com.etsy.Tests;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.etsy.Base.TestBase;
import com.etsy.Pages.HomePage;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends TestBase {
	
	HomePage hmpg;
	
	@BeforeMethod
	public void setup()
	{
		// CALL THE INITIALIZATION METHOD
		
		initialization(); // browser is invoked and url is launched. 
		
		// INITIALIZING THE HOMEPAGE CLASS;
		
		hmpg = new HomePage();	
	}
	
	// Verify title on the Home page
	
	@Test(priority=7)
	public void verifyHomePageTitle(Method method)
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		String title = hmpg.validateHomePageTitle();
		Assert.assertEquals(title, "Etsy - Shop for handmade, vintage, custom, and unique gifts for everyone");	
		System.out.println("HomePageTest: 1 - Validated Home page title");		
		Log.info("HomePageTest: 1 - Validated Home page title");
		test.log(LogStatus.INFO, "HomePageTest: 1 - Validated Home page title"); 
	}
	
	// Verify the presence of Signin option on home page	
	
	@Test(priority=8)
	public void verifySigninOptiononHomePage(Method method)
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		boolean present = hmpg.validateSignInOptionOnHomePage();
		Assert.assertTrue(present);
		System.out.println("HomePageTest: 2 - Validated the presence of signin option in home page");
		Log.info("HomePageTest: 2 - Validated the presence of signin option in home page");
		test.log(LogStatus.INFO, "HomePageTest: 2 - Validated the presence of signin option in home page"); 
	}
	
	// Verify search functionality --> when a keyword is entered --> items belonging to the search keyword should be displayed.
	
	@Test(priority=9)
	public void verifySearchFunctionality(Method method)
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		hmpg.validateSearchFunctionality(prop.getProperty("searchfor"));
		System.out.println("HomePageTest: 3 - Validated search for functionality");
		Log.info("HomePageTest: 3 - Validated search for functionality");
		test.log(LogStatus.INFO, "HomePageTest: 3 - Validated search for functionality"); 
	}  


}
