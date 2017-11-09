package com.lance.selenium.demo;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *Selenium findElement/findElements
 *
 */
public class SeleniumDemo2 {
	static Logger logger = LoggerFactory.getLogger(SeleniumDemo2.class);
	
	public static void main(String[] args) 
	{

		//设置必要参数
        DesiredCapabilities dcaps = DesiredCapabilities.phantomjs();//new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        
        //驱动支持
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
        		"D:/tools/phantomjs-1.9.7-windows/phantomjs.exe");
       
        //创建无界面浏览器对象
        WebDriver webDriver = new PhantomJSDriver(dcaps);
        
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		logger.debug("currentTime:"+df.format(new Date()));
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//注意这个是全局变量，影响所有元素加载超时异常

		webDriver.manage().window().maximize();
		webDriver.get("http://www.baidu.com");
		try
		{
			WebElement errorElement = webDriver.findElement(By.id("errorElement"));
		}
		catch(NoSuchElementException e)
		{	
			logger.debug("not find element errorElement");
			logger.debug("currentTime2:"+df.format(new Date()));
			
		}
		
	//	webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	
		WebElement kw = webDriver.findElement(By.id("kw"));
		
		kw.sendKeys("chrome");
		WebElement su = webDriver.findElement(By.id("su"));
		
		WebElement link = webDriver.findElement(By.xpath("//*[@id=\"u1\"]/a[2]"));
		String linkText =  link.getText();
		String linkHref = link.getAttribute("href");
		//1.截取整个页面
		try {
			//截取整个页面
			File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(srcFile,new File("D:\\screenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.页面元素截图
		WrapsDriver wrapsDriver = (WrapsDriver)link;
		//获取整个页面
		File screen = ((TakesScreenshot)wrapsDriver.getWrappedDriver())
				.getScreenshotAs(OutputType.FILE);
		
		try {
			BufferedImage img;
			img = ImageIO.read(screen);
			int width = link.getSize().getWidth();
			int height = link.getSize().getHeight();
			//创建一个矩形框
			Rectangle rect  = new Rectangle(width,height);
			//得到元素的坐标
			Point p = link.getLocation();
			BufferedImage dest = img.getSubimage(p.getX(),p.getY(),rect.width,rect.height);
			ImageIO.write(dest, "png", screen);
			FileUtils.copyFile(screen,new File("D:\\screenshot2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		//远程截图
		/*WebDriver browser = null ;
		try {
			browser = new RemoteWebDriver(new URL("http://localhost:1212/wew/we"),dcaps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		browser = new Augmenter().augment(browser);
		try {
			File srcFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile,new File("D:\\screenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		su.click();
		logger.debug("1 Page title is:{} " + webDriver.getTitle());
		logger.debug("linkText:" +linkText+";linkHref:"+linkHref+";kw width:"+kw.getCssValue("width"));
		
		webDriver.quit();
		
	}

	
	
}
