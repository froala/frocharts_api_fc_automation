package com.froalacharts.licensevalidationtest;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;

public class LicenseValidation extends APITestBase {
	
	Object[][] data;
	APIPageObjectModel pom;
	
	@BeforeTest
	public void setUp() 
	{
		api = new APITestBase();
		api.initialize();
		pom = new APIPageObjectModel();
		data = TestUtil.getLicenseData();
	}
	  	
	@Test(priority = 1)
	public void verifyWatermarkDisplayed() throws InterruptedException
	{
		String licenseData = "verifywatermarkdisplays"; 
		String htmlData = TestUtil.chartLicenseHtml(data, licenseData);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
		Thread.sleep(2000);
		boolean isWatermarkDisplayed = pom.isTrialWatermarkVisible();
		Assert.assertTrue(isWatermarkDisplayed, "watermark is displayed");
		
		
	}
	
	@Test(priority = 2)
	public void verifyWatermarkNotDisplayed_for_valid_key() throws InterruptedException
	{
		String licenseData = "verifywatermarknotdisplays"; 
		String htmlData = TestUtil.chartLicenseHtml(data, licenseData);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
		Thread.sleep(2000);
		boolean isWatermarkDisplayed = pom.isTrialWatermarkVisible();
		Assert.assertFalse(isWatermarkDisplayed, "watermark is not displayed");
		
	}
	
	@Test(priority = 3)
	public void verifyWatermarkDisplayedForCreditLabelTrue() throws InterruptedException
	{
		String licenseData = "creditLabelTrue"; 
		String htmlData = TestUtil.chartLicenseHtml(data, licenseData);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
		Thread.sleep(2000);
		boolean isWatermarkDisplayed = pom.isTrialWatermarkVisible();
		Assert.assertTrue(isWatermarkDisplayed, "watermark is displayed  creditLabel as true");
	}	
	
	@Test(priority = 4)
	public void verify_clicking_Watermark_is_navigating_to_froala_page() throws InterruptedException
	{
		String licenseData = "creditLabelTrue"; 
		String htmlData = TestUtil.chartLicenseHtml(data, licenseData);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
		Thread.sleep(2000);
		boolean isWatermarkDisplayed = pom.isTrialWatermarkVisible();
		Assert.assertTrue(isWatermarkDisplayed, "watermark is displayed  creditLabel as true");
		pom.click_on_watermark();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url,"https://froala.com/?utm_source=chart&utm_medium=watermark&utm_content=trial-200910-froalacharts");
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}

	
	@AfterTest
	public void shutDown() 
	{
		try
		{
			System.out.println("License validation Tests executed");
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
