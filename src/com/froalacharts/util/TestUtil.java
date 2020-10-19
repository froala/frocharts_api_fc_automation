package com.froalacharts.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.froalacharts.main.APITestBase;

public class TestUtil
{
	static Workbook book;
	static Sheet sheet;
	static String DATA_FILE = System.getProperty("user.dir") + "/src/com/froalacharts/testdata/apichartdata.xls";
	
	public static Object[][] getTestData()
	{
		FileInputStream file=null;
		try {
			file = new FileInputStream(DATA_FILE);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sheet=book.getSheet("api");
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++)
			{
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}
		
		return data;
	}
	
	public static Object[][] getLicenseData()
	{
		FileInputStream file=null;
		try {
			file = new FileInputStream(DATA_FILE);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sheet=book.getSheet("license");
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++)
			{
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}
		
		return data;
	}	
	
	public static void htmlWrite(String htmlData)
	{
		try
		{    
           FileWriter fw=new FileWriter(APITestBase.htmlFile); 
           fw.write(htmlData);    
           fw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public static boolean thisAPIexists(Object[][] data,String apiName)
	{
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			String apiNameSheet = (String) data[i][0];
			if(apiName.equals(apiNameSheet))
				return true;
		}
		return false;
	}
	
	public static String chartHtml(Object[][] data,String apiName)
	{
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			String apiNameSheet = (String) data[i][0];
			if(apiName.equals(apiNameSheet))
				return (String) data[i][2];
		}
		return "";
	}
	
	public static String chartLicenseHtml(Object[][] data,String licenseData)
	{
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			String licenseDataSheet = (String) data[i][0];
			if(licenseData.equals(licenseDataSheet))
				return (String) data[i][1];
		}
		return "";
	}	
	
	public static String apiScript(Object[][] data,String apiName)
	{
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			String apiNameSheet = (String) data[i][0];
			if(apiName.equals(apiNameSheet))
				return (String) data[i][1];
		}
		return "";
	}
}
