package com.froalacharts.apitest;

import com.froalacharts.main.APITestBase;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class Configure extends APITestBase {

	//The api name according to the data sheet
	private final static String apiName = "configure()"; 
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
	public void verifyAPIExistsInDataSheetConfigure()
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedConfigure()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "Chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPIConfigure() throws IOException
	{
		String bodyText = driver.findElement(By.tagName("text")).getText();
		Assert.assertTrue(bodyText.equals("Custom Message: No data to load"),"Configure API custom message correctly shown");
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("Configure_Message should be shown instaead of the chart")));	//Code Line for screenshot
	}
	@AfterTest
	public void shutDown() throws IOException
	{
		try
		{
			System.out.println("configure() executed");
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
