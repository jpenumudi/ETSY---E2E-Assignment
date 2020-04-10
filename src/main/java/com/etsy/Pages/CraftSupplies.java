package com.etsy.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.etsy.Base.TestBase;
import com.etsy.Utilities.TestUtil;

public class CraftSupplies extends TestBase {

	// create page factory for the objects on craft supplies page
	
	@FindBy(xpath="//span[contains(text(), 'Craft Supplies')]")
	WebElement CraftSupplieslink;
	
	@FindBy(xpath="//*[@id='search-filter-reset-form']/fieldset[1]/div/button[1]")
	WebElement showmore;
	
	@FindBy(xpath="//label[contains(text(),'FREE shipping')]")
	WebElement freeshippingchkbox;
	
	@FindBy(xpath="//label[contains(text(),'Handmade')]")
	WebElement handmaderadiobtn;
	
	@FindBy(id="shop-location-input")
	WebElement locationtextbox;
	
	@FindBy(xpath="//form[1]/fieldset[6]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/button[1]/span[1]/*")
	WebElement locationarrow;
	
	// INITIALIZING THE PAGE OBJECTS
	
		public CraftSupplies() // CONSTRUCTOR CLASS	
			{
				PageFactory.initElements(driver, this); 
			}
		
		// ACTIONS PERFORMED ON THE WEBELEMENTS DEFINED ABOVE. 
	
	// Validate Show more feature - When show more button is clicked more options should be displayed. 
		
	public void validateShowMoreOption()
	{		
		CraftSupplieslink.click();
		TestUtil.waitForElementClick(driver, showmore, 20);
		showmore.click();
		return;		
	}
	
	// validate checkbox - When free shipping check box is clicked, free shipping items should be displayed. 
	
	public void validateCheckBox()
	{		
		CraftSupplieslink.click();
		TestUtil.waitForElementClick(driver, freeshippingchkbox, 20);
		freeshippingchkbox.click();
		return;		
	}
	
	// validate RadioButton - When a Handmade radio button is clicked --> handmade products should be displayed.  
	
	public void validateRadioButton()
		{
			CraftSupplieslink.click();
			TestUtil.waitForElementClick(driver, handmaderadiobtn, 25);
			handmaderadiobtn.click();
			return;		
		}
	
	public void validateTextBox(String location) //Under Shop Location --> Enter location in the text box -->
	// Items from india should be displayed. 
	{
		CraftSupplieslink.click();
		TestUtil.waitForSendingKeys(driver, locationarrow, 25);
		locationtextbox.sendKeys(location);
		locationarrow.click();
		return;
	}
	

}
