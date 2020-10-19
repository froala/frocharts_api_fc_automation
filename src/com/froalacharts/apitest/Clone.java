package com.froalacharts.apitest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class Clone extends APITestBase
{
	//The api name according to the data sheet
	private final static String apiName = "clone()"; 
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
	public void verifyAPIExistsInDataSheetClone()
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedClone()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPIClone() throws IOException
	{
		List<WebElement> svgTotal = pom.getAllSvgElems();
		Assert.assertTrue(svgTotal.size()==1, "Only one chart getting rendered");
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		String apiScript = TestUtil.apiScript(data, apiName);
		jsExecuteWithBuffer(apiScript);
		svgTotal = pom.getAllSvgElems();
        Assert.assertTrue(svgTotal.size()==2, "Totally 2 charts exist");
		
		int firstChartElementsTotal = svgTotal.get(0).findElements(By.xpath("*")).size();
		int clonedChartElementsTotal = svgTotal.get(1).findElements(By.xpath("*")).size();
		Assert.assertTrue(firstChartElementsTotal==clonedChartElementsTotal, "Child elements of both are equal in number");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("Clone_there Should Be 2 Similar Charts")));	//Code Line for screenshot
		
		
		driver.findElement(By.id("modified_copy")).click();
		
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		svgTotal = pom.getAllSvgElems();
		Assert.assertTrue(svgTotal.size()==2, "Totally 2 charts exist");
		String clonedChartType = (String) js.executeScript("return clonedchart.type");
		
		Assert.assertTrue(clonedChartType.equals("doughnut"), "Cloned chart type is doughnut");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("Clone_there Should Be a line and a bar Chart")));	//Code Line for screenshot
		
	}
	
	@AfterTest
	public void shutDown() throws IOException
	{
		try
		{
			System.out.println("Clone() executed");
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
