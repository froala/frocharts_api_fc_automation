package com.froalacharts.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.xml.sax.SAXException;

import com.froalacharts.main.APITestBase;

public class TestUtilSetData {
	static Workbook book;
	static Sheet sheet;
	static String DATA_FILE = System.getProperty("user.dir") + "/src/com/froalacharts/testdata/apisetchartsdata.xls";
	private final static int numberOfCharts =34;
	
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
		
		sheet=book.getSheet("data");
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
           //FileWriter fw=new FileWriter(APITestBase.htmlFile); 
           OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(APITestBase.htmlFile), StandardCharsets.UTF_8);
           fw.write(htmlData);    
           fw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void jsonWrite(String jsonData)
	{
		try
		{    
           //FileWriter fw=new FileWriter(APITestBase.feeddataFileJSON); 
           OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(APITestBase.feeddataFileJSON), StandardCharsets.UTF_8);
           fw.write(jsonData);    
           fw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void xmlWrite(String xmlData)
	{
		try
		{    
           //FileWriter fw=new FileWriter(APITestBase.feeddataFileXML); 
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(APITestBase.feeddataFileXML), StandardCharsets.UTF_8);
           fw.write(xmlData);    
           fw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static String chartHtml(Object[][] data,String chart)
	{
		String[] allCharts = new String[numberOfCharts];
		
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			allCharts[i] = (String) data[i][0];
			if(allCharts[i].equals(chart))
			{
				return (String) data[i][1];
			}
		}
		return "";
	}
	
	public static String[] getAllCharts(Object[][] data)
	{
		String[] allCharts = new String[numberOfCharts];
		
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			allCharts[i] = (String) data[i][0];
		}
		return allCharts;
	}
	
	public static String getChartData(Object[][] data,String chart)
	{
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			String chartName = (String) data[i][0];
			if(chart.equals(chartName))
				return (String) data[i][2];
		}
		return "";
	}
	
	public static String getJSON(Object[][] data,String chart)
	{
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			String chartName = (String) data[i][0];
			if(chart.equals(chartName))
				return (String) data[i][3];
		}
		return "";
	}
	
	public static String getXML(Object[][] data,String chart)
	{
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			String chartName = (String) data[i][0];
			if(chart.equals(chartName))
				return (String) data[i][4];
		}
		return "";
	}
	
	public static void XMLequal(String sourceStr, String targetStr) throws SAXException, IOException
	{
		XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setCompareUnmatched(true);
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
        
//		Reader sourceRdr = new StringReader(sourceStr);
//		Reader targetRdr = new StringReader(targetStr);
//		BufferedReader source = new BufferedReader(sourceRdr);
//		BufferedReader target = new BufferedReader(targetRdr);
		
//		Diff xmlDiff = new Diff(source, target);
		
		DetailedDiff detailXmlDiff = new DetailedDiff(XMLUnit.compareXML(sourceStr, targetStr));
		
		detailXmlDiff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
		
        Iterator i = detailXmlDiff.getAllDifferences().iterator();

        while (i.hasNext()) {

            System.out.println(i.next().toString());

        }

        System.out.println("================== if soarting issues are ignored =============================");

        //this can use ignore soarting issues and assert

        XMLAssert.assertXMLEqual("XML files are mismatch", detailXmlDiff, true);
//		List differences = detailXmlDiff.getAllDifferences();
//		
//		int totalDifferences = differences.size();
//		
//		if(totalDifferences==0)
//			return true;
//		else
//			return false;
		}



}
