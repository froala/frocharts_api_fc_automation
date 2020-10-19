package com.froalacharts.apitest;

import java.io.IOException;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtilData;
import com.froalacharts.util.TestUtilSetData;

public class AllChartsSetDataAPIs extends APITestBase  
{
	//The api name according to the data sheet
	//private final static String apiName = "getXMLData()"; 
	Object[][] data;
	APIPageObjectModel pom;
	private static boolean hasRendered;
	
	@BeforeTest
	public void setUp() 
	{
		api = new APITestBase();
		api.initialize();
		pom = new APIPageObjectModel();
		data = TestUtilSetData.getTestData();
		
	}
	  
	
	@Test(priority = 1)
	public void verifyAllChartsSetDataAPIs() throws IOException, SAXException
	{	
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		ObjectMapper mapper = new ObjectMapper();
		
		int cnt=0;
		
		String htmlData;
		String[] allCharts = new String[33];
		allCharts = TestUtilSetData.getAllCharts(data);
		
		Map<String, Object> getChartDataMap = null;
		Map<String, Object> getChartDataJSONMap = null;
		Map<String, Object> getJSONDataMap = null;
		Map<String, Object> getChartJSONMap = null;
		
		
		
		for(String chart:allCharts)
		{
			if(chart==null)
				break;
			cnt++;
			htmlData = TestUtilSetData.chartHtml(data, chart);
			TestUtilSetData.htmlWrite(htmlData);
			driver.navigate().refresh();
			try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
			
			jsExecuteWithBuffer("froalacharts.render();");
			try {Thread.sleep(3000);} catch (InterruptedException e){e.printStackTrace();}
			js.executeScript("arguments[0].scrollIntoView(true);", pom.getSvg());
			
			String getChartData = TestUtilSetData.getChartData(data, chart).toString();
			TestUtilSetData.jsonWrite(getChartData);
			String setChartData = "froalacharts.setChartDataUrl(\"feeddata.json\",\"json\")";
			jsExecuteWithBuffer(setChartData);
			try {Thread.sleep(2000);} catch (InterruptedException e){e.printStackTrace();}
			String getChartDataJSON = (String) js.executeScript("return JSON.stringify(froalacharts.getJSONData(), undefined, 2)");
			
			try {Thread.sleep(4000);} catch (InterruptedException e){e.printStackTrace();}
			
			String getJSONData = TestUtilSetData.getJSON(data, chart).toString();
			TestUtilSetData.jsonWrite(getJSONData);
			String setJSONData = "froalacharts.setJSONUrl(\"feeddata.json\")";
			jsExecuteWithBuffer(setJSONData);
			try {Thread.sleep(2000);} catch (InterruptedException e){e.printStackTrace();}
			String getChartJSON = (String) js.executeScript("return JSON.stringify(froalacharts.getJSONData(), undefined, 2)");
			
			try {Thread.sleep(4000);} catch (InterruptedException e){e.printStackTrace();}	
			
			
			
			

			
			
			
			ObjectMapper om = new ObjectMapper();
	        try 
	        {
	            getChartDataMap = (Map<String, Object>)(om.readValue(getChartData, Map.class));
	            getChartDataJSONMap = (Map<String, Object>)(om.readValue(getChartDataJSON, Map.class));
	            getJSONDataMap = (Map<String, Object>)(om.readValue(getJSONData, Map.class));
	            getChartJSONMap = (Map<String, Object>)(om.readValue(getChartJSON, Map.class));	
	        } 
        	catch (Exception e) 
        	{
        		e.printStackTrace();
        	}
	        Assert.assertTrue(getChartDataMap.equals(getChartDataJSONMap), chart+" setChartDataURL() returns correct data");
	        Assert.assertTrue(getJSONDataMap.equals(getChartJSONMap), chart+" setJSONUrl() returns correct data");
		}
	}
	
	
	@AfterTest
	public void shutDown() throws IOException
	{
		try
		{
			System.out.println("AllChartsSetDataAPIs() executed");
			Thread.sleep(3000);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		report.endTest(test);
		report.flush();
		driver.quit();
	}


}
