package com.lance.http.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/***********************
 * @author Lance	    
 * @version 1.0        
 * @created 2017-11-8   
 *  
 *  HttpClient post  提交表单
 ***********************/
public class HttpClientDemo2 {
	
	public static void main (String[] args) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost("http://www.baidu.com");
		//封装提交到服务器的表单参数信息
		
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("name","lance"));
		list.add(new BasicNameValuePair("age","28"));
		UrlEncodedFormEntity  formEntity = new UrlEncodedFormEntity(list,Consts.UTF_8);
		
		//设置参数信息
		httpPost.setEntity(formEntity);
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		int stateCode =httpResponse.getStatusLine().getStatusCode();
		System.out.println("stateCode:"+stateCode);
	}
}
