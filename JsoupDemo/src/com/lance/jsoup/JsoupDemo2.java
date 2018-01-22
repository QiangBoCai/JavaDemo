package com.lance.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/***********************
 * @author Lance	    
 * @version 1.0        
 * @created 2017-11-14    
 * Html解析成Document后，使用类似DOM的方法或者CSS Selector进行操作，
 * 解析HTML文档的结构，定位抽取元素
 * 
 * 
 ***********************/
public class JsoupDemo2 {
	private static Document doc =null;
	
	public static void main(String[] args)
	{
		
		try {
			doc = Jsoup.connect("http://news.baidu.com/").get();
			//定位元素
			Element content = doc.getElementById("content");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
