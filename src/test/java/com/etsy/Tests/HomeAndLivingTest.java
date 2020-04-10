package com.etsy.Tests;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.etsy.Base.TestBase;
import com.etsy.Pages.HomeAndLiving;
import com.relevantcodes.extentreports.LogStatus;

public class HomeAndLivingTest extends TestBase {

	HomeAndLiving hmnlvng ;
	
	@BeforeMethod
	public void setup()
	{
		// CALL THE INITIALIZATION METHOD
		
		initialization();  // browser is invoked and url is launched. 
		
		// initializing the Home & Living page
		
		hmnlvng = new HomeAndLiving();	
	} 
	
	// Verify the Title of Home & Living page
	
	@Test(priority=10)
	public void verifyHomeNLivingTitle(Method method)
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		String title = hmnlvng.validateHomeNLvngTitle();
		Assert.assertEquals(title, "Home & Living | Etsy");
		System.out.println("HomeNLivingTest: 1 - Validated HomeNLiving Title");
		Log.info("HomeNLivingTest: 1 - Validated HomeNLiving Title");
		test.log(LogStatus.INFO, "HomeNLivingTest: 1 - Validated HomeNLiving Title"); 
	} 
	
	// Verify the presence of Signin option on Home & Living page	
	
	@Test(priority=11)
	public void verifySigninOptionIsPresent(Method method)
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		boolean present = hmnlvng.validateSignInOptionOnHomeAndLivingPage();
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		Assert.assertTrue(present);
		System.out.println("HomeNLivingTest: 2 - Validated the presence of signin option");
		Log.info("HomeNLivingTest: 2 - Validated the presence of signin option");
		test.log(LogStatus.INFO, "HomeNLivingTest: 2 - Validated the presence of signin option"); 
	}
	
	// Verify Sort By functionality --> when an option is selected under sort by --> a page for that option should be displayed. 
	
	@Test(priority=12)
	public void verifySortByFunctionality(Method method) throws InterruptedException
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		Thread.sleep(2000);
		hmnlvng.checkSortByList(prop.getProperty("list")); 
		System.out.println("HomeNLivingTest: 3 - Validated Sort By dropdown");
		Log.info("HomeNLivingTest: 3 - Validated Sort By dropdown");
		test.log(LogStatus.INFO, "HomeNLivingTest: 3 - Validated Sort By dropdown"); 
	}  
	
	// Verify url after selecting top reviews under sort by
	
	@Test(priority=13)
	public void verifyHmNLivingUrl(Method method) throws InterruptedException
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		String url = hmnlvng.validateTopReviewUrl();
		Assert.assertEquals(url, "https://www.etsy.com/c/home-and-living?explicit=1&order=highest_reviews");	
		System.out.println("HomeNLivingTest: 4 - Validated Top reviews url");
		Log.info("HomeNLivingTest: 4 - Validated Top reviews url");
		test.log(LogStatus.INFO, "HomeNLivingTest: 4 - Validated Top reviews url"); 
	} 	
}
