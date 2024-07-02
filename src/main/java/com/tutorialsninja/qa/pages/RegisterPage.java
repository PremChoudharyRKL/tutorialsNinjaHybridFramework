package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	@FindBy(id="input-firstname")
	private WebElement firstName;
	
	@FindBy(id="input-lastname")
	private WebElement lastName;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;

	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath ="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public void enterFirstName(String firstNameText) {
		firstName.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastName.sendKeys(lastNameText);
	}
	
	public void enterEmail(String email) {
		emailAddressField.sendKeys(email);
	}
	
	public void enterpasswordField(String password) {
		passwordField.sendKeys(password);
	}
	
	public void enterconfirmPassword(String confirmPassword) {
		passwordConfirmField.sendKeys(confirmPassword);
	}
	
	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsLetterOption() {
		yesNewsletterOption.click();
	}
	
	public String retrieveDuplicateEmailAddressWarning() {
		 
		return duplicateEmailAddressWarning.getText();
	}
	
	public String retrieveFirstNameWarning() {
		return firstNameWarning.getText();
	}
	
	public String retrieveLastNameWarning() {
		return lastNameWarning.getText();
	}
	public String retrieveEmailWarning() {
		return emailWarning.getText();
	}
	
	public String retrieveTelephoneWarning() {
	return	telephoneWarning.getText();
	}
	
	public String retrievePasswordWarning() {
		return passwordWarning.getText();
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText) {
		firstName.sendKeys(firstNameText);
		lastName.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	
	public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText) {
		firstName.sendKeys(firstNameText);
		lastName.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		yesNewsletterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	
}
