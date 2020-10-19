package com.froalacharts.apitest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.relevantcodes.extentreports.LogStatus;

public class GetJSONData extends APITestBase {
	
	//The api name according to the data sheet
	private final static String apiName = "getJSONData()"; 
	Object[][] data;
	APIPageObjectModel pom;
	
	@BeforeTest
	public void setUp() 
	{
		api = new APITestBase();
		api.initialize();
		pom = new APIPageObjectModel();
		data = TestUtil.getTestData();
	}
	  
	@Test(priority = 1)
	public void verifyAPIExistsInDataSheetGetJSONData()
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedGetJSONData()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPIGetJSONData() throws IOException
	{
		String apiScript = TestUtil.apiScript(data, apiName);
		
		driver.findElement(By.id("get_chart_data")).click();
		String msgText = driver.findElement(By.id("msg")).getText();
		
		Assert.assertTrue(!msgText.isEmpty(), "JSON data should have some value");
		
		try
		{
			//Automation for RED-8929
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse(msgText);
		    JsonObject jsonStructure = jsonObject.getAsJsonObject("chart");
		    String caption = jsonStructure.get("caption").toString();
		    String subCaption = jsonStructure.get("subCaption").toString();
		    String xAxisName = jsonStructure.get("xAxisName").toString();
		    String yAxisName = jsonStructure.get("yAxisName").toString();
		    
		    //Cosmetic Attributes
		    //int lineThickness = Integer.parseInt(jsonStructure.get("linethickness").toString());
		    String lineThickness = jsonStructure.get("lineThickness").toString();
		    String showBorder = jsonStructure.get("showBorder").toString();
		    String bgColor = jsonStructure.get("bgColor").toString();
		    String xAxisLineThickness = jsonStructure.get("xAxisLineThickness").toString();
		   		    
		    Assert.assertEquals("\"Total footfall in Bakersfield Central\"", caption, "Parameters returned are not in user provided form");
		    Assert.assertEquals("\"Last week\"", subCaption, "Parameters returned are not in user provided form");
		    Assert.assertEquals("\"Day\"", xAxisName, "Parameters returned are not in user provided form");
		    Assert.assertEquals("\"No. of Visitors\"", yAxisName, "Parameters returned are not in user provided form");
		    
		    Assert.assertEquals("\"2\"", lineThickness, "Cosmetic Parameters returned are not in user provided form");
		    Assert.assertEquals("\"0\"", showBorder, "Cosmetic Parameters returned are not in user provided form");
		    Assert.assertEquals("\"#ffffff\"", bgColor, "Cosmetic Parameters returned are not in user provided form");
		    Assert.assertEquals("\"1\"", xAxisLineThickness, "Cosmetic Parameters returned are not in user provided form");
		} 
		catch(JsonSyntaxException jse)
		{
			Assert.assertTrue(false, "JSON is in correct format");
		}
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("GetJSONData data should show above chart")));	//Code Line for screenshot
	}
	
	@AfterTest
	public void shutDown() 
	{
		try
		{
			System.out.println("getJSONData() executed");
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
