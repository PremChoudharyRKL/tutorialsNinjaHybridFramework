package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporterData;
import com.tutorialsninja.qa.utils.Utilities;




public class MyListener implements ITestListener{
	String testName;
	ExtentReports extentReports;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		 extentReports = ExtentReporterData.generateExtentReport();
	}
	@Override
	public void onTestStart(ITestResult result) {
    	 testName=result.getName();
    	extentTest = extentReports.createTest(testName);
    	extentTest.log(Status.INFO,testName+"started executing");
    	
    }

	@Override
	public void onTestSuccess(ITestResult result) {
		 testName=result.getName();
		extentTest.log(Status.PASS,testName+" got successfully executed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		testName=result.getName();
		WebDriver driver=null;
		try {
	    driver=(WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}catch(Throwable e) {
			e.printStackTrace();
		}
	String destinationScreenshotPath=	Utilities.captureScreenShot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,testName+"got Failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 testName=result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,testName+" got skipped");
	
	}



	@Override
	public void onFinish(ITestContext context) {
	extentReports.flush();
	String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
	File extentReport =new File(pathOfExtentReport);
	try {
		Desktop.getDesktop().browse(extentReport.toURI());//to open extent report automatically
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	}
	
	

	
}
