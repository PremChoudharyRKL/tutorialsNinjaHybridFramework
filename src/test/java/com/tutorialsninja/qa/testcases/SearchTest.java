package com.tutorialsninja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{
public  WebDriver driver;
  SearchPage searchpage;
  HomePage homepage;
  
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		 homepage=new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		
		searchpage=homepage.searchForAProduct(dataProp.getProperty("validProduct"));
		
      Assert.assertTrue(searchpage.displayStatusOfHpValidProduct(),"Valid HP product is not displayed");
	
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		searchpage=homepage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		
        String actualMessage=searchpage.retrieveNoProductMessageText();
		Assert.assertEquals(actualMessage,dataProp.getProperty("NoProductTextInSearchResults"),"No product message in search result is not displayed");
		
	}
	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {
	
		searchpage=homepage.clickOnSearchButton();
		
		String actualMessage=searchpage.retrieveNoProductMessageText();
		Assert.assertEquals(actualMessage,dataProp.getProperty("NoProductTextInSearchResults"),"No product message in search result is not displayed");
	}
}
