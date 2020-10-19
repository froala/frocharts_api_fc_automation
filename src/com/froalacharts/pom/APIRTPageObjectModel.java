package com.froalacharts.pom;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.froalacharts.main.APITestBase;

public class APIRTPageObjectModel extends APITestBase
{
	@FindBy(id="chart_1")
	WebElement rtgauge;
	
	@FindBy(id="chart_2")
	WebElement rtarea;
	
	@FindBy(id="chart_3")
	WebElement rtbulb;
	
	@FindBy(id="chart_4")
	WebElement rtcolumn;
	
	@FindBy(id="chart_5")
	WebElement rtcylinder;
	
	@FindBy(id="chart_6")
	WebElement rthlinear;
	
	@FindBy(id="chart_7")
	WebElement rtline;
	
	@FindBy(id="chart_8")
	WebElement rtlinedy;
	
	@FindBy(id="chart_9")
	WebElement rtsarea;
	
	@FindBy(id="chart_10")
	WebElement rtscolumn;
	
	@FindBy(id="chart_11")
	WebElement rtthermo;
	
	@FindBy(id="chart_12")
	WebElement rthbullet;
	
	@FindBy(id="chart_13")
	WebElement rthled;
	
	@FindBy(id="chart_14")
	WebElement rtvbullet;
	
	@FindBy(id="chart_15")
	WebElement rtvled;
	
	public APIRTPageObjectModel()
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement rtgauge()
	{
		return rtgauge;
	}
	
	public WebElement rtarea()
	{
		return rtarea;
	}
	
	public WebElement rtbulb()
	{
		return rtbulb;
	}
	
	public WebElement rtcolumn()
	{
		return rtcolumn;
	}
	
	public WebElement rtcylinder()
	{
		return rtcylinder;
	}
	
	public WebElement rthlinear()
	{
		return rthlinear;
	}
	
	public WebElement rtline()
	{
		return rtline;
	}
	
	public WebElement rtlinedy()
	{
		return rtlinedy;
	}
	
	public WebElement rtsarea()
	{
		return rtsarea;
	}
	
	public WebElement rtscolumn()
	{
		return rtscolumn;
	}
	
	public WebElement rtthermo()
	{
		return rtthermo;
	}
	
	public WebElement rthbullet()
	{
		return rthbullet;
	}
	
	public WebElement rthled()
	{
		return rthled;
	}
	
	public WebElement rtvbullet()
	{
		return rtvbullet;
	}
	
	public WebElement rtvled()
	{
		return rtvled;
	}
	
	
	@SuppressWarnings("finally")
	public int totalPlotsIn(WebElement elem)
	{
		if(elem.getAttribute("id").equals("chart_2") || elem.getAttribute("id").equals("chart_9"))
		{
			List<WebElement> datalabel = new LinkedList<WebElement>();
			List<WebElement> allGElem = elem.findElements(By.tagName("g"));
			
			for(WebElement gElem : allGElem)
			{
				if(gElem.getAttribute("class").contains("froalacharts-datalabels"))
					datalabel.add(gElem);
			}
			
			for(WebElement data : datalabel)
			{
				try
				{
					data.findElement(By.tagName("text"));
					return data.findElements(By.tagName("text")).size();
				}
				catch(NoSuchElementException e)
				{
					
				}
			}
		}
		else
		{
			List<WebElement> plotgroup = new LinkedList<WebElement>();
			List<WebElement> allGElem = elem.findElements(By.tagName("g"));
			
			char valid='a';
			int size = -1;
			
			for(WebElement gElem : allGElem)
			{
				if(gElem.getAttribute("class").contains("plot-group"))
					plotgroup.add(gElem);
			}
			
			for(WebElement data : plotgroup)
			{
				try
				{
					data.findElement(By.tagName("rect"));
					valid='r';
				}
				catch(NoSuchElementException e)
				{
					try 
					{
					data.findElement(By.tagName("path"));
					valid='p';
					}
					catch(NoSuchElementException e1) {}
				}
				finally
				{
					if(valid=='r')
					{
						size = data.findElements(By.tagName("rect")).size();
						break;
					}
					else if(valid=='p')
					{
						size = data.findElements(By.tagName("path")).size();
						if(size>1)
							break;
						else
							return -1;
					}
				}
			}
			return size;
		}
		return -1;
		
	}
	
}
