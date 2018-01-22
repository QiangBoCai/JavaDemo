package com.lance.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/***********************
 * @author Lance	    
 * @version 1.0        
 * @created 2017-11-14  
 * 
 * 设置元素的属性，class属性，html内容，文本内容
 *   
 * 消除不受信任的HTML，防止Cross-site scripting
 * 
 ***********************/
public class JsoupDemo4 {

	public  static void main(String[] args)
	{
		try {
			//Document doc3 = Jsoup.connect("http://music.163.com/m/song?id=189688").timeout(3000).get();
			
			File in = new File("D:\\test.html");
			Document doc3 = Jsoup.parse(in, "utf-8");
			System.out.println(doc3.toString());
			
			return;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
