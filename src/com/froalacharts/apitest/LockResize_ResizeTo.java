package com.froalacharts.apitest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.relevantcodes.extentreports.LogStatus;

public class LockResize_ResizeTo extends APITestBase {
	
	//The api name according to the data sheet
	private final static String apiName = "lockResize()"; 
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
	public void verifyAPIExistsInDataSheetLockResize_ResizeTo()
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedLockResize_ResizeTo()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPILockResize_ResizeTo() throws IOException
	{
		String apiScript = TestUtil.apiScript(data, apiName);
		Actions action = new Actions(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		long width = (long) js.executeScript("return froalacharts.apiInstance.getFromEnv('chartWidth')");
		long height = (long) js.executeScript("return froalacharts.apiInstance.getFromEnv('chartHeight')");
		
		int widthInt = (int) width;
		int heightInt = (int) height;
		
		action.moveToElement(pom.getSvg()).build().perform();
		
		action.moveByOffset(widthInt/2, 0).build().perform();
		
		action.clickAndHold().build().perform();
		action.moveByOffset(70,0).build().perform();
		action.release().build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e){e.printStackTrace();}
		
		long newWidth = (long) js.executeScript("return froalacharts.apiInstance.getFromEnv('chartWidth')");
		int newWidthInt = (int) newWidth;
		
		
		Assert.assertTrue(newWidthInt!=widthInt, "Chart should get resized");
		
		jsExecuteWithBuffer("froalacharts.lockResize(true)");
		
		action.moveToElement(pom.getSvg()).build().perform();
		
		action.moveByOffset(newWidthInt/2, 0).build().perform();
		
		action.clickAndHold().build().perform();
		action.moveByOffset(70,0).build().perform();
		action.release().build().perform();
		
		long afterLockWidth = (long) js.executeScript("return froalacharts.apiInstance.getFromEnv('chartWidth')");
		int afterLockWidthInt = (int) afterLockWidth;
		
		Assert.assertTrue(newWidthInt==afterLockWidthInt, "Chart should not get resized");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("LockResize_ResizeTo_container width should be more than chart")));	//Code Line for screenshot
		
		jsExecuteWithBuffer("froalacharts.resizeTo(400,250)");
		
		long resizeToWidth = (long) js.executeScript("return froalacharts.apiInstance.getFromEnv('chartWidth')");
		long resizeToHeight = (long) js.executeScript("return froalacharts.apiInstance.getFromEnv('chartHeight')");
		
		int resizeWidthInt = (int) resizeToWidth;
		int resizeToHeightInt = (int) resizeToHeight;
		
		Assert.assertTrue((resizeWidthInt==400 && resizeToHeightInt==250), "resizeTo() API - chart should get resized");
		
		try {Thread.sleep(2000);} catch (InterruptedException e){e.printStackTrace();}
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("LockResize_ResizeTo_JSON data should show above chart")));	//Code Line for screenshot
	}
	
	@AfterTest
	public void shutDown() 
	{
		try
		{
			System.out.println("lockResize() executed");
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
