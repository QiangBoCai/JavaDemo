package com.lance.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/***********************
 * @author Lance	    
 * @version 1.0        
 * @created 2017-11-14    
 * 
 * 从Document定位到一些元素后，获取元素中的数据eg:id,tagName,className,URLS,text等
 * 
 * eg:
 * 要取得一个属性的值，可以使用Node.attr(String key) 方法
 * 对于一个元素中的文本，可以使用Element.text()方法
 * 对于要取得元素或属性中的HTML内容，可以使用Element.html(), 或 Node.outerHtml()方法
 * 
 * 对元素中链接的相对路径转为绝对路径
 * 
 ***********************/
public class JsoupDemo3 {
	
	public static void main(String[] args)
	{
		String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
		Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
		Element link = doc.select("a").first();//查找第一个a元素

		String text = doc.body().text(); // "An example link"//取得字符串中的文本
		String linkHref = link.attr("href"); // "http://example.com/"//取得链接地址
		String linkText = link.text(); // "example""//取得链接地址中的文本

		String linkOuterH = link.outerHtml(); 
		    // "<a href="http://example.com"><b>example</b></a>"
		String linkInnerH = link.html(); // "<b>example</b>"//取得链接内的html内容
		
		
		try {
			Document doc2 = Jsoup.connect("http://www.open-open.com").get();
			Element links = doc2.select("a").first();
			String relHref = link.attr("href"); // == "/"
			String absHref = link.attr("abs:href"); // "http://www.open-open.com/"
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}
