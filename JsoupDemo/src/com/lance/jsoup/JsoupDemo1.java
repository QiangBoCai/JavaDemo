package com.lance.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/***********************
 * @author Lance	    
 * @version 1.0        
 * @created 2017-11-14   
 * 
 *  Jsoup.parse
 *  Jsoup.parseBodyFragment
 *  Jsoup.connect
 *  解析 字符串，本地文件，网络url 成Document
 *  
 ***********************/
public class JsoupDemo1 {
	static String html = "<html><head><title>First parse</title></head>" +
			"<body><p>Parse HTML into a doc.</p>" +
			"<p>没有关闭的标签解析" +
			"</body></html>";
	

	public static void main(String[] args) 
	{
		Document doc = Jsoup.parse(html);
		doc.toString();
		System.out.println(doc);
		String fragmentHtml = "<div><p>some fragment</p></div>";
		Document doc2 = Jsoup.parseBodyFragment(fragmentHtml);
		System.out.println(doc2);
		
		System.out.println(doc2.body());
		
		
		try {
			Document doc3 = Jsoup.connect("http://www.bocaifintech.com").timeout(3000).get();
			System.out.println(doc3.title());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File in = new File("//test.html");
		try {
			Document doc4 = Jsoup.parse(in, "UTF-8", "http://www.bocaifintech.com/");
			System.out.println(doc4.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
