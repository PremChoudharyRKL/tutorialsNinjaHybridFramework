package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterData {

	public static ExtentReports generateExtentReport() {
		ExtentReports extentreport=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Results Reports");
		sparkReporter.config().setDocumentTitle("TN Automation Reports");
		sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
		
		extentreport.attachReporter(sparkReporter);
		
		Properties configProp=new Properties();
		File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fisConfigProp=null;
		try {
			fisConfigProp = new FileInputStream(file);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			configProp.load(fisConfigProp);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentreport.setSystemInfo("Application URL",configProp.getProperty("url"));
		extentreport.setSystemInfo("Browser Name",configProp.getProperty("browser"));
		extentreport.setSystemInfo("Email",configProp.getProperty("validEmail"));
		extentreport.setSystemInfo("Password",configProp.getProperty("validPassword"));
		extentreport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentreport.setSystemInfo("Username",System.getProperty("user.name"));
		extentreport.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return extentreport;

	}
}
