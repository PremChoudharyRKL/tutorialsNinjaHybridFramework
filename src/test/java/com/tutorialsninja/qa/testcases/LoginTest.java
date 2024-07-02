package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
public	WebDriver driver;
	LoginPage loginpage;
	AccountPage accountpage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		loginpage=homepage.navigateToLoginPage();
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "credentailSupplier")
	public void verifyLoginWithValidCredentials(String[] data) {
		accountpage = loginpage.login(data[0],data[1]);
//		loginpage.enteringEmailAddress(data[0]);
//		loginpage.enterPassword(data[1]);
//		AccountPage accountpage=loginpage.clickOnLoginButton();

		Assert.assertTrue(accountpage.getDisplayedStatusOfEditYourAccountInformationOption(),
				"Edit Your Account Page is Displayed");

	}

	@DataProvider(name = "credentailSupplier")
	public String[][] supplyData() {
		String[][] data = Utilities.getTestDataFromExcel("login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentails() {
		accountpage=loginpage.login(Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
//		loginpage.enteringEmailAddress(Utilities.generateEmailWithTimeStamp());
//
//		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
//
//		loginpage.clickOnLoginButton();

		String actualmsg = loginpage.retriveEmailPasswordWarningMessageText();
		String expectedWarningmessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualmsg.contains(expectedWarningmessage), "Expected warning msg not displayed");

	}

}
