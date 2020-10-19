package com.froalacharts.apitest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class GetDataWithId extends APITestBase {
	
	//The api name according to the data sheet
	private final static String apiName = "getDataWithId()"; 
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
	public void verifyAPIExistsInDataSheetGetDataWithId() 
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedGetDataWithId()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPIGetDataWithId() throws IOException
	{
		String apiScript = TestUtil.apiScript(data, apiName);
		String[] dataheader = {"Food Item","Available Stock","Estimated Demand"};
		String[] dataContent = {"Poultry","P_AS,6000","P_ED,19000","Rice",
				"R_AS,9500","R_ED,16500","Peanut Butter","PB_AS,11900","PB_ED,14300","Salmon","S_AS,8000","S_ED,10000","Cereal",
				"C_AS,9700","C_ED,9800"};
		
		int headerCnt=0;
		int dataCnt=0;

		WebElement table = driver.findElement(By.id("data-table"));

		boolean isTableEmpty = table.findElements(By.xpath(".//*")).isEmpty();
		
		
		Assert.assertTrue(isTableEmpty, "Table should be null");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("GetDataWithId_chart should not be fully rendered")));
		
		
		try {Thread.sleep(22000);} catch (InterruptedException e) {}
		
		isTableEmpty = table.findElements(By.xpath(".//*")).isEmpty();
		
		
		Assert.assertTrue(!isTableEmpty, "Table should not be null");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("GetDataWithId_table below chart with 1 heading row 3 columns and 5 data rows")));	//Code Line for screenshot
		
		List<WebElement> headers= table.findElements(By.tagName("th"));
		
		for(WebElement header : headers)
		{			
			Assert.assertTrue(header.getText().equals(dataheader[headerCnt]), "Table header mismatch");
			Assert.assertTrue(driver.findElements(By.xpath("//*[contains(text(),'" + dataheader[headerCnt] + "')]")).size()>0,"Header Text not visible!");
			headerCnt++;
		}
		
		
		List<WebElement> contents= table.findElements(By.tagName("td"));
		
		for(WebElement content : contents)
		{			
			Assert.assertTrue(content.getText().equals(dataContent[dataCnt]), "Table data mismatch");
			Assert.assertTrue(driver.findElements(By.xpath("//*[contains(text(),'" + dataContent[dataCnt] + "')]")).size()>0,"Table data Text not visible!");
			dataCnt++;
		}
		
		
	}
	
	@AfterTest
	public void shutDown() 
	{
		try
		{
			System.out.println("getDataWithId() executed");
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
