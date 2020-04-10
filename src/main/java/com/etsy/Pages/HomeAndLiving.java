package com.etsy.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.etsy.Base.TestBase;
import com.etsy.Utilities.TestUtil;

public class HomeAndLiving extends TestBase{

	// create page factory for the objects on homenliving page
	
	@FindBy(xpath="//span[@id='catnav-primary-link-891']")
	WebElement HomeNlivinglink;
	
	@FindBy(xpath="//*[@id='gnav-header-inner']/div[4]/nav/ul/li[1]/button")
	WebElement signinoption;
	
	@FindBy(xpath="//span[@class='wt-menu__trigger__label']")
	WebElement sortbydropdown;
	
	@FindBy(xpath="//a[contains(text(),'Top Customer Reviews')]")
	WebElement topreview;
	
	// INITIALIZING THE PAGE OBJECTS
	
			public HomeAndLiving() // CONSTRUCTOR CLASS	
			{
				PageFactory.initElements(driver, this); 
			}
	
	// ACTIONS PERFORMED ON THE ELEMENTS DEFINED ABOVE. 
			
		// Validate Title of Home & Living page
			
			public String validateHomeNLvngTitle() 
			{				
				HomeNlivinglink.click();
				return driver.getTitle();
			}
		
		// Validate the presence of Signin option on Home & Living page			
			
			public Boolean validateSignInOptionOnHomeAndLivingPage()	
			{					
				HomeNlivinglink.click();
				return signinoption.isDisplayed();
			}
		
		// Validate Sort By functionality --> when an option is selected under sort by --> a page for that option should be displayed. 
			
			public void checkSortByList(String list) throws InterruptedException
			{				
				HomeNlivinglink.click();	
				TestUtil.waitForElementClick(driver, sortbydropdown, 30);
				sortbydropdown.click();
				Actions action = new Actions(driver);
				action.moveToElement(topreview).click().build().perform();
				
				return;
			}
			
		// Validate url after selecting top reviews under sort by
			
			public String validateTopReviewUrl() throws InterruptedException
			{
				
				HomeNlivinglink.click();	
				TestUtil.waitForElementClick(driver, sortbydropdown, 30);
				sortbydropdown.click();
				Actions action = new Actions(driver);				
				action.moveToElement(topreview).click().build().perform();				
				return driver.getCurrentUrl();
			}
	

}
