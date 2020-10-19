package com.froalacharts.apitest;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;


public class IsPlotItemSliced_slicePlotItem extends APITestBase 
{
	private final static String apiName = "isPlotItemSliced()"; 
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

	public void verifyAPIExistsInDataSheetIsPlotItemSliced_slicePlotItem() 
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedIsPlotItemSliced_slicePlotItem()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPIIsPlotItemSliced_slicePlotItem() throws IOException
	{
		Object boolValue;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		List<WebElement> plots = pom.getElementsByPartialClassName("plots");
		Actions action = new Actions(driver);
		
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(0);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_pie2d.slicePlotItem(0)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the first plot should be sliced")));	//Code Line for screenshot
		
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(1);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_pie2d.slicePlotItem(1)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the 2 plots should be sliced in order")));	//Code Line for screenshot
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(2);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_pie2d.slicePlotItem(2)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the 3 plots should be sliced in order")));	//Code Line for screenshot
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(3);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_pie2d.slicePlotItem(3)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie2d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(0);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_pie3d.slicePlotItem(0)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the first plot should be sliced")));	//Code Line for screenshot
		
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(1);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_pie3d.slicePlotItem(1)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the 2 plots should be sliced in order")));	//Code Line for screenshot
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(2);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_pie3d.slicePlotItem(2)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the 3 plots should be sliced in order")));	//Code Line for screenshot
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(3);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_pie3d.slicePlotItem(3)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_pie3d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(0);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_doughnut2d.slicePlotItem(0)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the first plot should be sliced")));	//Code Line for screenshot
		
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(1);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_doughnut2d.slicePlotItem(1)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the 2 plots should be sliced in order")));	//Code Line for screenshot
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(2);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_doughnut2d.slicePlotItem(2)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the 3 plots should be sliced in order")));	//Code Line for screenshot
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(3);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_doughnut2d.slicePlotItem(3)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut2d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(0);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_doughnut3d.slicePlotItem(0)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the first plot should be sliced")));	//Code Line for screenshot
		
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(1);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_doughnut3d.slicePlotItem(1)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the 2 plots should be sliced in order")));	//Code Line for screenshot
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(2);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_doughnut3d.slicePlotItem(2)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(false), "Item should not be sliced");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the 3 plots should be sliced in order")));	//Code Line for screenshot
		
		for(WebElement plotTag : plots)
		{
			List<WebElement> plotmain = plotTag.findElements(By.xpath(".//*"));
			WebElement firstplot = plotmain.get(3);
			String tagName = firstplot.getTagName();
			if(tagName.equals("path"))
			{
				js.executeScript("froalacharts_doughnut3d.slicePlotItem(3)");
				break;
			}
		}
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(0)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(1)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(2)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		boolValue = js.executeScript("return froalacharts_doughnut3d.isPlotItemSliced(3)");
		Assert.assertTrue(boolValue.equals(true), "Item should be sliced");
		
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("IsPlotItemSliced_In Pie chart the 4 plots should be sliced in order")));	//Code Line for screenshot
	}
	
	@AfterTest
	public void shutDown() 
	{
		try
		{
			System.out.println("isPlotItemSliced() executed");
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
