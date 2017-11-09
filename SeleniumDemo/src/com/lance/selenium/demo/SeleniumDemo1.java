package com.lance.selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jna.platform.WindowUtils;

/****
 * Selenium Driver Brower (Firefox,Chrome,PhantomJS)
 * 
 */
public class SeleniumDemo1 {
	static Logger logger = LoggerFactory.getLogger(SeleniumDemo1.class);
	
	public static void main(String[] args) 
	{

		//System.setProperty("webdriver.chrome.driver", "D:/tools/chromedriver.exe");
		//	WebDriver webDriver = new ChromeDriver();
	    //System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
		//WebDriver webDriver = new FirefoxDriver();
		//设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
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
        PhantomJSDriver webDriver = new PhantomJSDriver(dcaps);
        
		webDriver.get("http://www.baidu.com");
		
		WebElement kw = webDriver.findElement(By.id("kw"));
		kw.sendKeys("chrome");
		WebElement su = webDriver.findElement(By.id("su"));
		su.click();
		logger.debug("1 Page title is:{} " + webDriver.getTitle());
		
		webDriver.quit();
		
	}

	
	
}
