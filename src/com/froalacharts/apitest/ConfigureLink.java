package com.froalacharts.apitest;

import com.froalacharts.main.APITestBase;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class ConfigureLink extends APITestBase {

	//The api name according to the data sheet
	private final static String apiName = "configureLink()"; 
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
	public void verifyAPIExistsInDataSheetConfigureLink()
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedConfigureLink()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "Chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPIConfigureLink() throws IOException
	{
		WebElement plot = pom.getElementByPartialClassName("g", "plot-group");
		WebElement singleplot = plot.findElement(By.tagName("rect"));
		Actions action = new Actions(driver);
		action.moveToElement(singleplot).build().perform();
		action.click().build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {}
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("ConfigureLink_pie chart should be visible with back button")));	//Code Line for screenshot
		
		WebElement mainContainer = pom.mainContainer();
		List<WebElement> spans = mainContainer.findElements(By.tagName("span"));
		
		WebElement button = null;
		for(WebElement span:spans)
		{
			if(span.getText().equals("Back to parent chart"))
				button = span;
		}
		
		action.moveToElement(button).build().perform();
		action.click().build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {}
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("ConfigureLink_column2d chart should be visible")));
		
	}
	@AfterTest
	public void shutDown() throws IOException
	{
		try
		{
			System.out.println("configureLink() executed");
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
