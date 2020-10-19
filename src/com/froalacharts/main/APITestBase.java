package com.froalacharts.main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
public class APITestBase 
{
    public static WebDriver driver;
    public static Properties prop;
    public static ExtentReports report;
    public static ExtentTest test;
    public APITestBase api;
    public static String htmlFile = System.getProperty("user.dir")+"/File/NewTestTry.html";
    public static String feeddataFileJSON = System.getProperty("user.dir")+"/File/feeddata.json";
    public static String feeddataFileXML = System.getProperty("user.dir")+"/File/feeddata.xml";
    public static String configFile = System.getProperty("user.dir")+"/src/com/froalacharts/config/config.properties";
    public static String extentreportXMLFile = System.getProperty("user.dir")+"/src/com/froalacharts/config/extent-config.xml";
    public static String extentreportReportFile = System.getProperty("user.dir") +"/test-output/FC_API_ExtentReport.html";
    
    
    public APITestBase() 
    {
        try 
        {
            prop = new Properties();
            FileInputStream file = new FileInputStream(configFile);
            prop.load(file);
            report = new ExtentReports(extentreportReportFile);
            report.loadConfig(new File(extentreportXMLFile));
            test = report.startTest("API Testing All");
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void initialize()
    {
        String browser = prop.getProperty("browser");
        if(browser.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "C://Apache24//htdocs//api_fc_automation//drivers/chromedriver.exe");
            driver=new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
            driver=new FirefoxDriver();
        }
        
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(6, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        
        driver.get(prop.getProperty("url"));
    }
    
    public static void jsExecuteWithBuffer(String apiScript)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver; 
        js.executeScript(apiScript);
        
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static String capture(String screenShotName) throws IOException
    {
        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportRetina(100,0,0,2)).takeScreenshot(driver);
        String dest = System.getProperty("user.dir") + "/Screenshots/"+screenShotName+".png"; 
        ImageIO.write(fpScreenshot.getImage(),"PNG",new File(dest));     
                     
        return dest;
    }
    
    public static String captureElement(WebElement elem,String screenShotName) throws IOException
    {
        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportRetina(100,0,0,2)).takeScreenshot(driver);
        String dest = System.getProperty("user.dir") + "/Screenshots/"+screenShotName+".png"; 
        ImageIO.write(fpScreenshot.getImage(),"PNG",new File(dest));
        
        File fpScreenshotFile = new File(dest);
        
        BufferedImage  fullImg = ImageIO.read(fpScreenshotFile);
        Point point = elem.getLocation();
        // Get width and height of the element
        int eleWidth = elem.getSize().getWidth();
        int eleHeight = elem.getSize().getHeight();
        // Crop the entire page screenshot to get only element screenshot
        BufferedImage elemScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
        ImageIO.write(elemScreenshot,"PNG",new File(dest));     
                     
        return dest;
    }
	
	
	public static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
	    if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
	     for (int x = 0; x < img1.getWidth(); x++) {
	      for (int y = 0; y < img1.getHeight(); y++) {
	       if (img1.getRGB(x, y) != img2.getRGB(x, y))
		       {
		        return false;
		       }
	       }
	      }
	     } else {
	        return false;
	     }
	     return true;
	    }
}
