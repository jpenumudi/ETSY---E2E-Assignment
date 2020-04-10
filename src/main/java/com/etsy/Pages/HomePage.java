package com.etsy.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.etsy.Base.TestBase;

public class HomePage extends TestBase {

	// CREATE PAGE FACTORY OR OBJECT REPOSITORY BY USING @FINDBY ANNOTATION
	
	@FindBy(xpath="//*[@id='gnav-header-inner']/div[4]/nav/ul/li[1]/button")
	WebElement signinoption;
	
	@FindBy(name="search_query")
	WebElement searchbar;
	
	@FindBy(xpath="//form[1]/div[1]/div[1]/button[1]/span[1]/*")
	WebElement searchbutton;
	
	// INITIALIZING THE PAGE OBJECTS
	
		public HomePage() // CONSTRUCTOR CLASS	
		{
			PageFactory.initElements(driver, this); 
		}
		
	// ACTIONS PERFORMED ON THE ELEMENTS DEFINED ABOVE. 
		
		// Validate title on the Home page
		
		public String validateHomePageTitle()
		{
			return driver.getTitle();
		}
		
		// Validate the presence of Signin option on home page			
		
		public Boolean validateSignInOptionOnHomePage()	
		{			
			return signinoption.isDisplayed();
		}
		
		// Validate search functionality --> when a keyword is entered --> items belonging to the search keyword should be displayed.
		
		public void validateSearchFunctionality(String searchkeyword )
		{
			searchbar.sendKeys(searchkeyword);				
			searchbutton.click();
		}		


}
