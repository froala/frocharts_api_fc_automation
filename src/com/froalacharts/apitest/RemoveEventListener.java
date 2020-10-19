package com.froalacharts.apitest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class RemoveEventListener extends APITestBase {

	//The api name according to the data sheet
	private final static String apiName = "removeEventListener()"; 
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
	public void verifyAPIExistsInDataSheetRemoveEventListener() 
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedRemoveEventListener()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPIRemoveEventListener() throws IOException
	{
		String apiScript = TestUtil.apiScript(data, apiName);
		jsExecuteWithBuffer(apiScript);
		
		Actions action = new Actions(driver);
		
		List<WebElement> allG = driver.findElements(By.tagName("g"));
		
		
		if(pom.verifySetButtonExists() && pom.verifyRemoveButtonExists())
		{
			pom.setButton().click();
			if(pom.divText().getText().equals("Hover Now"))
			{
				Assert.assertTrue(true, "Text visible should be - Hover Now");
				test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("RemoveEventListener_text Below Should Be - Hover Now")));	//Code Line for screenshot
			}
			else
				Assert.assertTrue(false, "Text visible should be - Hover Now");
			
		/*	WebElement piePlots = pom.getElementByPartialClassName("g", "top-Side");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<WebElement> allPlots = piePlots.findElements(By.xpath("*"));
			for(WebElement plot : allPlots)
				action.moveToElement(plot).build().perform();
			
			if(!pom.divText().getText().equals("Hover Now") && !pom.divText().getText().equals("Removed") && !pom.divText().getText().equals(""))
			{
				Assert.assertTrue(true, "Text visible should be some number as plot value");
				test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("RemoveEventListener_text Below Should Be Some Number")));	//Code Line for screenshot
			}
			else
				Assert.assertTrue(false, "Text visible should be some number as plot value");*/
			
			pom.removeButton().click();
			
			if(pom.divText().getText().equals("Removed"))
			{
				Assert.assertTrue(true, "Text visible should be - Removed");
				test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("RemoveEventListener_text Below Should Be - Removed")));	//Code Line for screenshot
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*for(WebElement plot : allPlots)
				action.moveToElement(plot).build().perform();
			
			if(pom.divText().getText().equals("Removed"))
			{
				Assert.assertTrue(true, "Text visible should be - Removed");
				test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("RemoveEventListener_text Below Should Be - Removed(2)")));	//Code Line for screenshot
			}
			*/
		}
	}
	
	@AfterTest
	public void shutDown() 
	{
		try
		{
			System.out.println("removeEventListener() executed");
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
