package com.froalacharts.apitest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

//import net.avh4.util.imagecomparison.ImageComparison;
//import ru.yandex.qatools.ashot.comparison.ImageDiff;
//import ru.yandex.qatools.ashot.comparison.ImageDiffer;


public class StartingAngle extends APITestBase
{

	private final static String apiName = "startingAngle()"; 
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
	public void verifyAPIExistsInDataSheetStartingAngle()
	{
		boolean apiExists = TestUtil.thisAPIexists(data, apiName);
		Assert.assertTrue(apiExists, "API name matches in data sheet");
	}
	
	@Test(priority = 2)
	public void verifyChartIsRenderedStartingAngle()
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
	public void verifyAPIStartingAngle() throws IOException
	{
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart initial")));
		String apiScript = TestUtil.apiScript(data, apiName);
		jsExecuteWithBuffer(apiScript);
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart after rotating by 60 degrees")));
		
		BufferedImage pieInitial = ImageIO.read(new File(System.getProperty("user.dir") +"/Screenshots/ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart initial.png"));
		BufferedImage pieAfterRotate = ImageIO.read(new File(System.getProperty("user.dir") +"/Screenshots/ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart after rotating by 60 degrees.png"));
		Assert.assertTrue(!bufferedImagesEqual(pieInitial,pieAfterRotate),"initial and Rotate 60 are diff images");
		
        jsExecuteWithBuffer("froalacharts.startingAngle(60,0)");
        
        test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart after absolute rotate 60 degree")));
        BufferedImage pieAfterRotateAbs = ImageIO.read(new File(System.getProperty("user.dir") +"/Screenshots/ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart after absolute rotate 60 degree.png"));
        Assert.assertTrue(bufferedImagesEqual(pieAfterRotateAbs,pieAfterRotate),"First Rotate and Absolute rotate are same images");
        
        jsExecuteWithBuffer("froalacharts.startingAngle(60,1)");
        
        test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart after relative rotate 60 degrees for 1 time")));
        BufferedImage pieAfterRotateRelative1 = ImageIO.read(new File(System.getProperty("user.dir") +"/Screenshots/ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart after relative rotate 60 degrees for 1 time.png"));
        Assert.assertTrue(!bufferedImagesEqual(pieAfterRotateRelative1,pieAfterRotateAbs),"First Rotate and Absolute rotate are same images");
        
        jsExecuteWithBuffer("froalacharts.startingAngle(60,1)");
        
        test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart after relative rotate 60 degrees for 2 times")));
        BufferedImage pieAfterRotateRelative2 = ImageIO.read(new File(System.getProperty("user.dir") +"/Screenshots/ZZZAutoVerify StartingAngle()_Pie and Doughnut Chart after relative rotate 60 degrees for 2 times.png"));
        Assert.assertTrue(!bufferedImagesEqual(pieAfterRotateRelative2,pieAfterRotateRelative1),"Second Rotate and Absolute rotate are same images");
	}
	
	@AfterTest
	public void shutDown() throws IOException
	{
		try
		{
			System.out.println("startingAngle() executed");
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
