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
import com.froalacharts.util.TestUtilData;
import com.relevantcodes.extentreports.LogStatus;

public class AllChartsDataAPIs extends APITestBase 
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
		data = TestUtilData.getTestData();
		
		
		
	}
	  
	
	@Test(priority = 1)
	public void verifyAllChartsDataAPIs() throws IOException
	{	
		
		String csvChartData="";
		String jsonChartData="";
		String jsonData="";
		String csvData="";
		String svgData="";
		
		
		String jsonRefData="";
		String csvRefData="";
		String xmlRefData="";
		String getDataAsCSV="";
		String csvTranscodeData="";
		
		String chartType="";
		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		int ctr=0;
		String htmlData;
		String[] allCharts = new String[1];
		allCharts = TestUtilData.getAllCharts(data);
		System.out.println(allCharts);
		
		for(String chart:allCharts)
		{
			if(chart ==null)
				break;
			else
			{
			
			ctr++;
			jsonRefData=TestUtilData.getJSON(data, chart);
			csvRefData= TestUtilData.getCSV(data, chart);
			
			
			String msgGetChartAttr = chart+" should show theme as fusion";
			String msgDispose = chart+" chart should have been disposed";
			String msgChartType = chart+" chart chartType() returns correctly";
			String msgCrossLine = chart+" chart shows crossline properly";
			String msgSVGData = chart+" chart getSVGData() format is correct";
			
			boolean svgDisplayed;
			
			htmlData = TestUtilData.chartHtml(data, chart);
			TestUtilData.htmlWrite(htmlData);
			driver.navigate().refresh();
			try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
			
			hasRendered = (boolean) js.executeScript("return froalacharts.hasRendered()");
			Assert.assertEquals(hasRendered, false);
			
			jsExecuteWithBuffer("froalacharts.render();");
			
			hasRendered = (boolean) js.executeScript("return froalacharts.hasRendered()");
			Assert.assertEquals(hasRendered, true);
			
			chartType = (String) js.executeScript("return froalacharts.chartType()");
			
			Assert.assertTrue(chartType.equals(chart),msgChartType);
			
			if(chart.equals("mscolumn2d")||chart.equals("mscombidy2d"))
			{
				jsExecuteWithBuffer("froalacharts.drawCrossline(0)");
				js.executeScript("arguments[0].scrollIntoView(true);", pom.crossline());
				Assert.assertTrue(pom.crossline().getAttribute("stroke").equals("#ff0000"),msgCrossLine);
				System.out.println(chart+" crossline verified");
			}
			
			
			String theme = (String) js.executeScript("return froalacharts.getChartAttribute(\"theme\")");
			Assert.assertTrue(theme.equals("fusion"),msgGetChartAttr);
			
			driver.findElement(By.id("get_chart_data")).click();
			driver.findElement(By.id("get_CSV_data")).click();
			driver.findElement(By.id("get_JSON_data")).click();
			driver.findElement(By.id("get_SVG_data")).click();
			driver.findElement(By.id("transcode_data")).click();
			
			csvChartData = driver.findElement(By.id("msg")).getText();
			jsonData = driver.findElement(By.id("msg_JSON")).getText();
			csvData = driver.findElement(By.id("msg_CSV")).getText();
			svgData = driver.findElement(By.id("msg_SVG")).getText();
			
			
			Select format = new Select(driver.findElement(By.id("data_format")));
			format.selectByValue("json");
			driver.findElement(By.id("get_chart_data")).click();
			
			jsonChartData = driver.findElement(By.id("msg")).getText();
			
			
			jsExecuteWithBuffer("var csv = froalacharts.getDataAsCSV();\n" + 
					"        csv = csv.replace(/\\n/g, \"<br>\");\n" + 
					"        document.getElementById('msg').innerHTML = csv;");
			
			
			getDataAsCSV = driver.findElement(By.id("msg")).getText();
			csvTranscodeData = driver.findElement(By.id("msg_transcode")).getText();
			
			
			//Assert.assertTrue(csvTranscodeData.equals(csvRefData),chart+" chart transcodeData() for CSV should work properly");
			Assert.assertTrue(getDataAsCSV.equals(csvRefData),chart+" chart getDataAsCSV() for CSV should work properly");
			Assert.assertTrue(csvChartData.equals(csvRefData),chart+" chart getChartData() for CSV should work properly");
			Assert.assertTrue(csvData.equals(csvRefData),chart+" chart getCSVData() should work properly");
			Assert.assertTrue(jsonData.equals(jsonRefData),chart+" chart getJSONData() should work properly");
			Assert.assertTrue(jsonChartData.equals(jsonRefData),chart+" chart getChartData() for JSON should work properly");
			
		//	Assert.assertTrue(svgData.startsWith("<svg"),msgSVGData);
		//	Assert.assertTrue(svgData.endsWith("</svg>"),msgSVGData);
			
			
			jsExecuteWithBuffer("froalacharts.resizeTo(400,250)");
			
			long resizeToWidth = (long) js.executeScript("return froalacharts.apiInstance.getFromEnv('chartWidth')");
			long resizeToHeight = (long) js.executeScript("return froalacharts.apiInstance.getFromEnv('chartHeight')");
			
			int resizeWidthInt = (int) resizeToWidth;
			int resizeToHeightInt = (int) resizeToHeight;
			
			Assert.assertTrue((resizeWidthInt==400 && resizeToHeightInt==250), chart+" resizeTo() API - chart should get resized");
			
			try {Thread.sleep(2000);} catch (InterruptedException e){e.printStackTrace();}
			
			jsExecuteWithBuffer("froalacharts.dispose()");
			
			try
			{
				pom.getSvg().isDisplayed();
				svgDisplayed = true;
			}
			catch(Exception e)
			{
				svgDisplayed =false;
			}
			
			Assert.assertTrue(!svgDisplayed,msgDispose);
			}
		}
	}
	
	
	@AfterTest
	public void shutDown() throws IOException
	{
		try
		{
			System.out.println("AllChartsDataAPIs() executed");
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
