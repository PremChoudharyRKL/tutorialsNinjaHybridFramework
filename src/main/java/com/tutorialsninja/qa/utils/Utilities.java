package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;




public class Utilities {
	public static final  int IMPLICIT_WAIT_TIME=10;

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timestamp= date.toString().replace(" ", "_").replace(":", "_");
		return "amotoori"+timestamp+"@gmail.com";
	}
	
	public static String[][] getTestDataFromExcel(String sheetName) {
		File excelFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testData\\tutorialsNinjaTestData.xlsx");
	
	
		
		FileInputStream fis=null;
		XSSFWorkbook workbook=null;;
		try {
			fis = new FileInputStream(excelFile);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 workbook=new XSSFWorkbook(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int rows=sheet.getPhysicalNumberOfRows();
		int columns=sheet.getRow(0).getLastCellNum();
        String[][] data=new String[rows-1][columns];
		
		for(int i=0;i<rows-1;i++) {
			for(int j=0;j<columns;j++) {
				DataFormatter df=new DataFormatter();
			data[i][j]=	df.formatCellValue( sheet.getRow(i+1).getCell(j));
			}

		}
		
	return data;
		
	}
	public static String captureScreenShot(WebDriver driver,String testName) {
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
	

	
}
