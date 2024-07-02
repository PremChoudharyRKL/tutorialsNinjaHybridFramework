package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
public	WebDriver driver;
	RegisterPage registerpage;
	AccountSuccessPage accountSuccessPage;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		registerpage = homepage.navigateToRegisterPage();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryyFields() {
		accountSuccessPage = registerpage.registerWithMandatoryFields(dataProp.getProperty("FirstName"),
				dataProp.getProperty("LastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));

		String actualSuccessheading = accountSuccessPage.getAccountSuccessPageHeading();

		Assert.assertEquals(actualSuccessheading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Success page is not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllField() {
      accountSuccessPage=registerpage.registerWithAllFields(dataProp.getProperty("FirstName"),dataProp.getProperty("LastName"),Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"),prop.getProperty("validPassword"));
		
       String actualSuccessheading = accountSuccessPage.getAccountSuccessPageHeading();

		Assert.assertEquals(actualSuccessheading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Success page is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
	      accountSuccessPage=registerpage.registerWithAllFields(dataProp.getProperty("FirstName"),dataProp.getProperty("LastName"),prop.getProperty("validEmail"),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));
	      String actualWarning = registerpage.retrieveDuplicateEmailAddressWarning();
//		Reporter.log(actualWarning,true);
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),
				"Warning message is not displayed");

	}

	@Test(priority = 4)
	public void RegesteringAccountWithoutFillingAnyDetails() {

		registerpage.clickOnContinueButton();

//		
//		String actualprivacypolicy=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
//		Assert.assertTrue(actualprivacypolicy.contains("Warning: E-Mail Address is already registered!"),"Warning message is not displayed");

		String actualFirstNameWarning = registerpage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"),
				"First name warning message is not displayed");

		String actualLastNameWarning = registerpage.retrieveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"),
				"Last name warning message is not displayed");

		String actualEmailWarning = registerpage.retrieveEmailWarning();
		Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailWarning"),
				"Email text warning message is not displayed");

		String actualTelephoneWarning = registerpage.retrieveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"),
				"Telephone text warning message is not displayed");

		String actualPasswordWarning = registerpage.retrievePasswordWarning();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"),
				"Password text warning message is not displayed");

	}
}
