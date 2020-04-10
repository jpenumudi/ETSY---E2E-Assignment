package com.etsy.Tests;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.etsy.Base.TestBase;
import com.etsy.Pages.CraftSupplies;
import com.relevantcodes.extentreports.LogStatus;

public class CraftSuppliesTest extends TestBase {
	
	// CraftSuppliesTest class inherits the properties of TestBaseClass and uses those properties when called. 
	
	CraftSupplies craftsup;

	@BeforeMethod
	public void setup()
	{
		// CALL THE INITIALIZATION METHOD
		
		initialization(); // browser is invoked and url is launched. 
		
		// INITIALIZING THE CRAFTSUPPLIES CLASS;
		
		 craftsup = new CraftSupplies();		
	}
	
	//When show more button is clicked more options should be displayed. 
	
	@Test(priority=14)
	public void verifyShowMoreOption(Method method) throws InterruptedException
	{
		Thread.sleep(2000);
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		craftsup.validateShowMoreOption();
		System.out.println("CraftSuppliesTest: 1 - Validated Show more link");
		Log.info("CraftSuppliesTest: 1 - Validated Show more link");
		test.log(LogStatus.INFO, "CraftSuppliesTest: 1 - Validated Show more link"); 
	}
	
	// When free shipping check box is clicked, free shipping items should be displayed. 
	
	@Test(priority=15)
	public void verifyCheckBox(Method method) throws InterruptedException
	{
		Thread.sleep(2000);
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		craftsup.validateCheckBox();
		System.out.println("CraftSuppliesTest: 2 - Validated check box");
		Log.info("CraftSuppliesTest: 2 - Validated check box");
		test.log(LogStatus.INFO, "CraftSuppliesTest: 2 - Validated check box"); 
	}
	
	//  When a Handmade radio button is clicked --> handmade products should be displayed. 
	
	@Test(priority=16)
	public void verifyRadioButton(Method method) throws InterruptedException
	{
		Thread.sleep(2000);
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		craftsup.validateRadioButton();
		System.out.println("CraftSuppliesTest: 3 - Validated Radio Button");
		Log.info("CraftSuppliesTest: 3 - Validated Radio Button");
		test.log(LogStatus.INFO, "CraftSuppliesTest: 3 - Validated Radio Button"); 
	}
	
	//Under Shop Location --> Enter location in the text box --> Items from india should be displayed. 
	
	@Test(priority=17)
	public void verifyLocation(Method method)
	{
		test = report.startTest(method.getName());
		System.out.println("Name of the testcase is: " +method.getName());
		Log.info("Name of the testcase is: " +method.getName());
		test.log(LogStatus.INFO, "Name of the testcase is: " +method.getName());
		craftsup.validateTextBox(prop.getProperty("location"));
		System.out.println("CraftSuppliesTest: 4 - Validating Custom location field");
		Log.info("CraftSuppliesTest: 4 - Validation Custom location field");
		test.log(LogStatus.INFO, "CraftSuppliesTest: 4 - Validation Custom location field"); 
	}


	

}
