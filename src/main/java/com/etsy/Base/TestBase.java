package com.etsy.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.etsy.Utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//TEST BASE CLASS IS THE SUPER CLASS OR PARENT CLASS 

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Logger Log; 
	public static String screenshot;
	public static ExtentReports report; // This class generates HTML report in the specified path
	public static ExtentTest test; // This class is used to log test steps (generate logs) in the HTML report. 
	public static String ERROR;
	public WebDriverWait wait; 
	
	//CREATING A CONSTRUCTOR FOR TEST BASE CLASS
	
	public TestBase(){ 
		
		// Initializing the log object (Log) using getLogger method of LogManager class. 
		
		Log = LogManager.getLogger(this.getClass()); 
						
		//System.out.println("class object ===== > ::"+this.getClass());
		//System.out.println("class name ===> :: "+Log.getName());
		
		// READING THE PROPERTIES FROM THE PROPERTIES FILE
		
		try {
			prop = new Properties();			 
			FileInputStream file = new FileInputStream((System.getProperty("user.dir") + "\\src\\main\\java\\com\\etsy\\Configuration\\Config.properties"));					
			prop.load(file);
						
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}

	// METHOD TO GENERATE THE EXTENT REPORT. 

	@BeforeTest			
	public void startExtent(){
								
		report = new ExtentReports(System.getProperty("user.dir") + "/ExtentReports/EtsyExtentReport.html");							 
		report.addSystemInfo("Host name", "LAPTOP-PBGFF7J3");
		report.addSystemInfo("Username", "Jyothi");
		report.addSystemInfo("Environment", "Automation");
		report.addSystemInfo("OS", "Windows 10");
		report.addSystemInfo("Java Version", "1.8.0_60");
		report.addSystemInfo("Selenium Version", "3.141.59");
		report.addSystemInfo("TestNG Version", "6.14.3");
		report.addSystemInfo("Extent Reports Version", "2.41.2");
		report.addSystemInfo("log4j", "1.2.17");
		report.loadConfig(new File(System.getProperty("user.dir") + "/src/main/Resources/Extent-Config.xml")); 
					
		} 
	
	public static void initialization() 
	{
		// READ THE PROPERTIES FROM CONFIG.PROPERTIES FILE AND INITIALIZE THE BROWSERS, OPEN THE URL, MAXIMIZE THE BROWSER WINDOW AND DELETE COOKIES. 
		
		String BrowserName = prop.getProperty("browser");
		
			if(BrowserName.equals("chrome")){
					
			System.setProperty("webdriver.chrome.driver", "src\\main\\java\\com\\etsy\\Drivers\\chromedriver.exe");			
			System.setProperty("webdriver.chrome.silentOutput", "true"); // Stops showing starting chrome browser...... in the console
			driver = new ChromeDriver();  																		
			}
			else if(BrowserName.equals("firefox")){
				
				System.setProperty("webdriver.gecko.driver", "src\\main\\java\\com\\etsy\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(BrowserName.equals("firefox")){
				
				System.setProperty("webdriver.ie.driver", "src\\main\\java\\com\\etsy\\Drivers\\IEDriverServer64.exe");
				driver = new InternetExplorerDriver();
			}
												
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(TestUtil.page_load_timeout, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(TestUtil.implicit_wait, TimeUnit.SECONDS);
								
				driver.get(prop.getProperty("url"));			
			}

	
	@AfterMethod
	public void teardown(ITestResult result) throws EmailException , IOException{
		if(result.getStatus()==ITestResult.FAILURE)
	{
		// send email of failed tests  -- UNCOMMENT WHEN YOU WANT THE RESULTS TO BE SENT TO EMAIL. 
									
			//TestUtil.sendDetailedEmail("One method in RegisterTest class Failed"); 			
						
		// using simple sysout to print the messages to the console
			
			System.out.println( "Test Case Failed is " + result.getName());
			System.out.println("Test Failed..Email sent"); 	
			
		// using log4j and logger class object to print the messages to file or console (code is written in log4j2.xml file to output to file).
			
			Log.info("Test Case Failed is " + result.getName());
			Log.info("Test Failed..Email sent");
													
		// create log of failed tests -- these logs are created in the extent report. 
							
			test.log(LogStatus.FAIL, "Test case failed is: " + result.getName()); // adds name of the test in the extent report. 
			test.log(LogStatus.FAIL, "Test Failed..Email sent");
									
		// Capture screenshot of failed tests
			
			screenshot = TestUtil.getScreenshot(driver, result.getName() ); // gets the name of the screenshot	
			//System.out.println("Result.getName: " + result.getName());
			//System.out.println("The value of Screenshot is: " +screenshot);
			
			System.out.println("Name of the failed testcase screenshot is: " +result.getName() + ".png"); // prints the name of the screenshot to the console
			Log.info("Name of the failed testcase screenshot is: " +result.getName() + ".png"); // in log4j2.xml code is written to output the log to file --> 
																								// so this prints the log to the file
			
			test.log(LogStatus.FAIL, "Please see attached screenshot");
			test.log(LogStatus.FAIL, "Name of the failed testcase screenshot is: " +result.getName() + ".png");		
			test.log(LogStatus.FAIL, result.getThrowable()); // getThrowable gives the error stacktrace = adds errors/exceptions to the extent report 		
			test.log(LogStatus.FAIL, "Screenshot Attached: " , test.addBase64ScreenShot("data:image/png;base64," +TestUtil.getBase64Image(screenshot)));
		}
		
		else if(result.getStatus()==ITestResult.SKIP)
		{	
			// printing messages to the console
			
			System.out.println("Test Case Skipped is " + result.getName());
			System.out.println("Test Case Skipped - so email is not sent");
			
			// printing the messages to a log file
			
			Log.info("Test Case Skipped is " + result.getName());
			Log.info("Test Case Skipped - so email is not sent");
						
			// printing the messages to the extent report
			
			test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
			test.log(LogStatus.SKIP, "Test Case Skipped - so email is not sent");	    
		    screenshot = TestUtil.getScreenshot(driver, result.getName() );		    
		    test.log(LogStatus.SKIP, "Please see attached screenshot");
		    test.log(LogStatus.SKIP, "Name of the skipped testcase screenshot is: " +result.getName() + ".png");
		    test.log(LogStatus.SKIP, "Screenshot Attached: " , test.addBase64ScreenShot("data:image/png;base64," +TestUtil.getBase64Image(screenshot)));
		    
		    // printing screenshot names to console and log file
		    
		    System.out.println("Name of the skipped testcase screenshot is: " +result.getName() + ".png");
		    Log.info("Name of the skipped testcase screenshot is: " +result.getName() + ".png");
		}
		
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			// printing messages to the console
			
			System.out.println( "Test Case Passed is " + result.getName());
			System.out.println("Test Case Passed - so email is not sent");
			
			// printing the messages to a log file
			
			Log.info( "Test Case Passed is " + result.getName());
			Log.info("Test Case Passed - so email is not sent");
			
			// printing the messages to the extent report
			
			test.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
			test.log(LogStatus.PASS, "Test Case Passed - so email is not sent");		    
		    screenshot = TestUtil.getScreenshot(driver, result.getName() );	
		    test.log(LogStatus.PASS, " Please see attached screenshot");
		    test.log(LogStatus.PASS, "Name of the passed testcase screenshot is: " +result.getName() + ".png");		   
		    test.log(LogStatus.PASS, "Screenshot Attached: " , test.addBase64ScreenShot("data:image/png;base64," +TestUtil.getBase64Image(screenshot)));
		    
		 // printing screenshot names to console and log file
		    System.out.println("Name of the passed testcase screenshot is: " +result.getName() + ".png");
		    Log.info("Name of the passed testcase screenshot is: " +result.getName() + ".png");
		} 
		
		else
		{
			driver.close();
		} 
		
			// Ends the current test and prepares to create HTML report
		
			report.endTest(test);		
			Log.info("Base -- Browser Closed");
			driver.quit();		
		}  
		
@AfterTest
	public void endReport(){
	
		report.endTest(test); // ends test
		report.flush(); // writes or updates test info to the report 
		report.close();
		
	}	

// Calling the finalEmail method to send an email with the results of the complete test execution 

/*  // UNCOMMENT WHEN YOU WANT THE RESULTS TO BE SENT TO EMAIL. 
@AfterSuite
public void wrapUp() throws EmailException
{
	
	TestUtil.sendDetailedEmail("Please find attached test automation execution results");
	
}  */
	

}
