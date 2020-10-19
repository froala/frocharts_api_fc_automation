package com.froalacharts.apitest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class ShowChartMessage extends APITestBase 
{
	//The api name according to the data sheet
	private final static String apiName = "showChartMessage()"; 
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
	public void verifyAPIExistsInDataSheetShowChartMessage()
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedShowChartMessage()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "Chart is rendered");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 3)
	public void verifyAPIShowChartMessage() throws IOException
	{
		String msg = "Chart Message";
		WebElement Label = driver.findElement(By.tagName("input"));
		Label.sendKeys(msg);
		
		WebElement mode = driver.findElement(By.id("mode"));
		Select options = new Select(mode);
		options.selectByValue("overlay");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String buttonMessage = driver.findElement(By.tagName("button")).getText();
		Assert.assertTrue(buttonMessage.equals("Show Message"),"This button should be clicked");
		WebElement button = driver.findElement(By.tagName("button"));
		Actions action = new Actions(driver);
		action.click(button).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("ShowChartMessage()_Overlay Chart Message should be shown over the Chart")));
		
		WebElement messageGroup = pom.getElementByPartialClassName("g", "messageGroup");
		WebElement msgGroupRect = messageGroup.findElement(By.tagName("rect"));
		Assert.assertTrue(msgGroupRect.getAttribute("fill-opacity").equals("0.2"),"Opacity should be 0.2");
		
		WebElement messageGroup1 = pom.getElementByPartialClassName("g", "messageGroup");
		WebElement messageText = messageGroup1.findElement(By.tagName("text"));
		String checkText = messageText.getText();
		Assert.assertTrue(checkText.equals("Chart Message"),"Chart Message should be shown as: Chart Message");
		
		options.selectByValue("onchart");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		String buttonMessage1 = driver.findElement(By.tagName("button")).getText();
		Assert.assertTrue(buttonMessage1.equals("Show Message"),"This button should be clicked");
		WebElement button1 = driver.findElement(By.tagName("button"));
		Actions action1 = new Actions(driver);
		action1.click(button1).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("ShowChartMessage()_OnChart Chart Message should be shown on the Chart")));
		Assert.assertTrue(checkText.equals("Chart Message"),"Chart Message should be shown as: Chart Message");
	}
	
	@AfterTest
	public void shutDown() throws IOException
	{
		try
		{
			System.out.println("showChartMessage() executed");
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
