package com.lance.selenium.firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/****
 * Selenium Driver for Firefox HeadLess
 * 
 */
public class SeleniumFirefoxHeadlessDemo {
	static Logger logger = LoggerFactory.getLogger(SeleniumFirefoxHeadlessDemo.class);
	
	public static void main(String[] args) 
	{
	    FirefoxBinary firefoxBinary = new FirefoxBinary();
	   // firefoxBinary.addCommandLineOptions("--headless");
	    System.setProperty("webdriver.gecko.driver", "D:/tools/geckodriver.exe");
	    FirefoxOptions firefoxOptions = new FirefoxOptions();
	    firefoxOptions.setBinary(firefoxBinary);
	    WebDriver webDriver = new FirefoxDriver(firefoxOptions);
		webDriver.get("http://www.baidu.com");
		
		WebElement kw = webDriver.findElement(By.id("kw"));
		kw.sendKeys("Selenium");
		WebElement su = webDriver.findElement(By.id("su"));
		su.click();
		logger.debug("1 Page title is:{} " + webDriver.getTitle());
		
		//webDriver.quit();
		
	}

	
	
}
