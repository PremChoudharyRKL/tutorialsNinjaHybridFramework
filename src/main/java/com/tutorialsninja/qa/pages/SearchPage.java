package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	
WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(partialLinkText = "HP LP3065")
	private WebElement validHpProduct;
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;
	
	public boolean displayStatusOfHpValidProduct() {
		return validHpProduct.isDisplayed();
	}
	
	public String retrieveNoProductMessageText() {
		
		return noProductMessage.getText();
	}
	
	
}
