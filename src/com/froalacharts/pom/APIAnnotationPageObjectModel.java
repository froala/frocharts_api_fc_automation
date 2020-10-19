package com.froalacharts.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.froalacharts.main.APITestBase;

public class APIAnnotationPageObjectModel extends APITestBase{

	static APIAnnotationPageObjectModel chart = new APIAnnotationPageObjectModel();
	@FindBy(className="froalacharts-container")
	WebElement mainContainer;
	
	@FindBy(id="add_annotation")
	WebElement addGroupAddItem;
	
	@FindBy(id="destroy_annotation")
	WebElement destroy;
	
	@FindBy(id="update_annotation")
	WebElement update;
	
	@FindBy(id="show_annotation")
	WebElement show;
	
	@FindBy(id="hide_annotation")
	WebElement hide;
	
	@FindBy(id="addMarker")
	WebElement addMarker;
	
	@FindBy(id="updateMarker")
	WebElement updateMarker;
	
	@FindBy(id="removeMarker")
	WebElement removeMarker;
	
	public APIAnnotationPageObjectModel()
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
	
	public WebElement addGroupAddItem()
	{
		return addGroupAddItem;
	}
	
	public WebElement destroy()
	{
		return destroy;
	}
	
	public WebElement update()
	{
		return update;
	}
	
	public WebElement show()
	{
		return show;
	}
	
	public WebElement hide()
	{
		return hide;
	}
	
	public WebElement addMarker()
	{
		return addMarker;
	}
	
	public WebElement updateMarker()
	{
		return updateMarker;
	}
	
	public WebElement removeMarker()
	{
		return removeMarker;
	}
	
	public WebElement annotationSVG()
	{
		return driver.findElement(By.id("chart-container")).findElement(By.tagName("svg"));
	}
	
	public WebElement mapSVG()
	{
		return driver.findElement(By.id("map-container")).findElement(By.tagName("svg"));
	}
	public WebElement annotation()
	{
		return driver.findElement(By.xpath("//*[contains(@class,'-totalFootfall')]"));
	}
	
	public boolean annotationExists()
	{
		try
		{
			driver.findElement(By.xpath("//*[contains(@class,'-totalFootfall')]"));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public WebElement markerParent()
	{
		return driver.findElement(By.xpath("//*[contains(@class,'-markers')]"));
	}
	
	public int totalEllipseMarkers()
	{
		return chart.markerParent().findElements(By.tagName("ellipse")).size();
	}
	
	public WebElement getAddedMarker()
	{
		int ctr=0;
		WebElement addedMarker = null;
		List<WebElement> ellipseMarkers = chart.markerParent().findElements(By.tagName("ellipse"));
		
		for(WebElement marker:ellipseMarkers)
		{
			ctr++;
			if(ctr==5)
				addedMarker = marker;
		}
		
		return addedMarker;
	}
	
}
