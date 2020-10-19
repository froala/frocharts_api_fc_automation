package com.froalacharts.apitest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIAnnotationPageObjectModel;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class AnnotationAPIs extends APITestBase{

	//The api name according to the data sheet
	private final static String apiName = "annotationAPIs()"; 
	Object[][] data;
	APIAnnotationPageObjectModel pom;
	
	@BeforeTest
	public void setUp() 
	{
		api = new APITestBase();
		api.initialize();
		pom = new APIAnnotationPageObjectModel();
		data = TestUtil.getTestData();
	}
	  
	@Test(priority = 1)
	public void verifyAPIExistsInDataSheetAnnotationAPIs()
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedAnnotationAPIs()
	{
		String htmlData = TestUtil.chartHtml(data, apiName);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
		boolean containerDisplayed = pom.verifyIfChartMainContainerDisplayed();
		Assert.assertTrue(containerDisplayed, "chart is rendered");
	}
	
	@Test(priority = 3)
	public void verifyAPIAnnotationAPIs() throws IOException
	{
		boolean annotationDisplayed;
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		Actions action = new Actions(driver);
		
		annotationDisplayed = pom.annotationExists();
		Assert.assertTrue(!annotationDisplayed,"Annotation not displayed");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		js.executeScript("arguments[0].scrollIntoView(true);", pom.addGroupAddItem());
		action.click(pom.addGroupAddItem()).build().perform();
		
		
		annotationDisplayed = pom.annotation().isDisplayed();
		Assert.assertTrue(annotationDisplayed,"Annotation displayed");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		
		js.executeScript("arguments[0].scrollIntoView(true);", pom.update());
		action.click(pom.update()).build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		Assert.assertTrue(pom.annotation().findElement(By.tagName("rect")).getAttribute("fill").equals("#ff0000"),"Annotation Updated correctly");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		//js.executeScript("arguments[0].scrollIntoView(true);", pom.hide());
		action.click(pom.hide()).build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		annotationDisplayed = pom.annotation().isDisplayed();
		
		Assert.assertTrue(!annotationDisplayed,"Annotation not displayed");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		js.executeScript("arguments[0].scrollIntoView(true);", pom.show());
		action.click(pom.show()).build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		annotationDisplayed = pom.annotation().isDisplayed();
		Assert.assertTrue(annotationDisplayed,"Annotation displayed");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		js.executeScript("arguments[0].scrollIntoView(true);", pom.destroy());
		action.click(pom.destroy()).build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		Assert.assertTrue(!pom.annotationExists(),"Annotation does not exist");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		js.executeScript("arguments[0].scrollIntoView(true);", pom.addGroupAddItem());
		action.click(pom.addGroupAddItem()).build().perform();
		
		Assert.assertTrue(pom.annotationExists(),"Annotation exists by adding after destroy");
		
		jsExecuteWithBuffer("chart.annotations.clear()");
		
		Assert.assertTrue(!pom.annotationExists(),"Annotation does not exist after clear()");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		
		
		
		
		
		js.executeScript("arguments[0].scrollIntoView(true);", pom.addMarker());
		action.click(pom.addMarker()).build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		Assert.assertTrue(pom.getAddedMarker().getAttribute("fill").equals("#0000ff"),"Marker is an ellipse of blue color");
		Assert.assertTrue(pom.getAddedMarker().isDisplayed(),"Marker is displayed after addition");
		Assert.assertTrue(pom.totalEllipseMarkers()==5,"Total ellipse markers are 5");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		js.executeScript("arguments[0].scrollIntoView(true);", pom.updateMarker());
		action.click(pom.updateMarker()).build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		Assert.assertTrue(pom.getAddedMarker().getAttribute("fill").equals("#00ff00"),"Marker after update is of green color");
		Assert.assertTrue(pom.getAddedMarker().isDisplayed(),"Marker is displayed after updation");
		Assert.assertTrue(pom.totalEllipseMarkers()==5,"Total ellipse markers are 5");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		js.executeScript("arguments[0].scrollIntoView(true);", pom.removeMarker());
		action.click(pom.removeMarker()).build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		Assert.assertTrue(pom.getAddedMarker()==null,"Marker is not displayed");
		Assert.assertTrue(pom.totalEllipseMarkers()==4,"Total ellipse markers are 4");
	}
	
	@AfterTest
	public void shutDown() throws IOException
	{
		try
		{
			System.out.println("AnnotationAPIs() executed");
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
