package com.froalacharts.pom;
import org.openqa.selenium.support.pagefactory.*;
import org.openqa.selenium.support.ui.Select;

import com.froalacharts.main.APITestBase;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
public class APIPageObjectModel extends APITestBase
{
	@FindBy(className="froalacharts-container")
	WebElement mainContainer;
	
	@FindBy(tagName="svg")
	WebElement svgElement;
	
	@FindBy(tagName="svg")
	List<WebElement> svgElements;
	
	@FindBy(id="btn_1")
	WebElement tempButton;
	
	@FindBy(id="set")
	WebElement set;
	
	@FindBy(id="rem")
	WebElement remove;
	
	@FindBy(id="data1")
	WebElement divText;
	
	public APIPageObjectModel()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyIfChartMainContainerDisplayed()
	{
		if(mainContainer.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean verifyIfChartMainContainerExists()
	{
		try {
			if(mainContainer.isDisplayed())
				return true;
			else
				return false;
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			return false;
		}
	}
	
	public WebElement getUserDefinedContainer(String container)
	{
		return driver.findElement(By.id(container));
	}
	
	public List<WebElement> getTotalCharts()
	{
		return driver.findElements(By.className("froalacharts-container"));
	}
	
	public String getInnerHtml(WebElement elem)
	{
		return elem.getAttribute("innerHTML");
	}
	
	public WebElement getSvg()
	{
		return svgElement;
	}
	
	public List<WebElement> getAllSvgElems()
	{
		return svgElements;
	}
	
	public WebElement mainContainer()
	{
		return mainContainer;
	}
	
	public boolean verifyTemporaryButtonExists()
	{
		if(tempButton.isDisplayed())
			return true;
		else
			return false;
	}
	
	public WebElement setButton()
	{
		return set;
	}
	
	public boolean verifySetButtonExists()
	{
		if(set.isDisplayed())
			return true;
		else
			return false;
	}
	
	public WebElement removeButton()
	{
		return remove;
	}
	
	public boolean verifyRemoveButtonExists()
	{
		if(remove.isDisplayed())
			return true;
		else
			return false;
	}
	
	public WebElement divText()
	{
		return divText;
	}
	
	public WebElement getElementByPartialClassName(String tagName,String partialClassName)
	{
		List<WebElement> allElements = driver.findElements(By.tagName(tagName));
		for(WebElement all : allElements)
		{						
			if(all.getAttribute("class").contains(partialClassName))				
				return all;
		}
		return null;
	}
	
	public List<WebElement> getElementsByPartialClassName(String name)
	{
		return svgElement.findElements(By.xpath("//*[contains(@class,'"+name+"')]"));
	}
	
	public int totalChildElements(WebElement elem)
	{
		return elem.findElements(By.xpath(".//*")).size();
	}
	
	public WebElement canvasZoomLine()
	{
		List<WebElement> allCanvas = driver.findElements(By.xpath("//*[contains(@class,'-canvas')]"));
		
		for(WebElement canvas:allCanvas)
		{
			try
			{
				canvas.findElement(By.tagName("rect"));
				return canvas;
			}
			catch(Exception e)
			{
				
			}
		}
		return null;
	}
	
	public String centerLabelText()
	{
		WebElement textTag=null;
		String centerLabel = "";
		WebElement plots = driver.findElement(By.xpath("//*[contains(@class,'-plots')]"));
		List<WebElement> plotsChild = plots.findElements(By.xpath(".//*"));
		
		for(WebElement plot:plotsChild)
		{
			try
			{
				if(plot.getTagName().equals("text"))
					textTag=plot;
			}
			catch(Exception e){}
		}
		List<WebElement> tspans = textTag.findElements(By.tagName("tspan"));
		
		for(WebElement tspan:tspans)
		{
			centerLabel = centerLabel.concat(tspan.getText());
		}
		
		return centerLabel;
	}
	
	public WebElement crossline()
	{
		return driver.findElement(By.xpath("//*[contains(@class,'-crosslineBottom')]")).findElement(By.tagName("path"));
	}
	public String getUpperLimit()
	{
		int ctr=0;
		long textVal;
		String textInTag = null;
		WebElement yAxisLabel = driver.findElement(By.xpath("//*[contains(@class,'dataset-Label-group')]"));
		List<WebElement> textElems = yAxisLabel.findElements(By.tagName("text"));
		for(WebElement text:textElems)
		{
			ctr++;
			if(ctr==2)
			{
				textInTag = text.getText();
				break;
			}
		}
		return textInTag;
	}
	
	public String getLowerLimit()
	{
		int ctr=0;
		long textVal;
		String textInTag = null;
		WebElement yAxisLabel = driver.findElement(By.xpath("//*[contains(@class,'dataset-Label-group')]"));
		List<WebElement> textElems = yAxisLabel.findElements(By.tagName("text"));
		for(WebElement text:textElems)
		{
			ctr++;
			if(ctr==3)
			{
				textInTag = text.getText();
				break;
			}
		}
		return textInTag;
	}
	
	public boolean isTrialWatermarkVisible()
	{
		boolean status = false;
		int count = driver.findElements(By.xpath("//*[text()=\"Powered by\"]")).size();		
		if(count >= 1) {
			status=true;
		}
		return status;
	}
	
	public void selectyear() {
		
		Select year = new Select(driver.findElement(By.id("set_json_data")));
				year.selectByValue("thisyear");
	}
	
}
