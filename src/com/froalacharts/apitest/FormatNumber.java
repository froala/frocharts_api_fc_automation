package com.froalacharts.apitest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class FormatNumber extends APITestBase
{
	//The api name according to the data sheet
	private final static String apiName = "formatNumber()"; 
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
	public void verifyAPIExistsInDataSheetFormatNumber() 
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedFormatNumber()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPIChartTypeFormatNumber() throws IOException
	{
		String apiScript = TestUtil.apiScript(data, apiName);
		
		String text =driver.findElement(By.id("msg")).getText();
		
		Assert.assertTrue(text.equals(""), "Text is empty");
		
		pom.mainContainer().click();
		
		text =driver.findElement(By.id("msg")).getText();
		
		//jsExecuteWithBuffer("");
		
		Assert.assertTrue(text.equals("&10.23K"), "Text has formatted number value");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("FormatNumber_Number Should Show As &10.23K")));	//Code Line for screenshot
	}
	
	@AfterTest
	public void shutDown() 
	{
		try
		{
			System.out.println("FormatNumber() executed");
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
