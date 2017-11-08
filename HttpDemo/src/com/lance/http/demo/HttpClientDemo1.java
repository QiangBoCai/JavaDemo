package com.lance.http.demo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientDemo1 {

	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException 
	{
		//创建httpclient实例，采用默认的参数配置
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//通过URIBuilder类创建URI
		
	/*	URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("www.baidu.com")
					.build();*/
		String uri = "http://ww.baidu.com";
		
		//使用get方法提交请求
		HttpGet httpGet = new HttpGet(uri);
		
        //请求的参数配置，分别设置连接池获取连接的超时时间，连接上服务器的时间，服务器返回数据的时间
		RequestConfig requestConfig = RequestConfig.custom()
											.setConnectionRequestTimeout(3000)
											.setConnectTimeout(3000)
											.setSocketTimeout(3000)
											.build();
		//设置配置
		httpGet.setConfig(requestConfig);
		
		//通过httpclient的execute提交 请求 ，并用CloseableHttpResponse接受返回信息
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		//服务器返回状态
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if(HttpStatus.SC_OK == statusCode)
		{
			System.out.println("获取信息成功:"+httpResponse.getEntity().getContentType().getValue()
					+";headers="+httpResponse.getAllHeaders());
				//	+"content:"+EntityUtils.toString(httpResponse.getEntity()));
			//Apache 不建议使用EntityUtils,除非响应的实体来自于信任的Http服务器，并且知道它的长度。
			byte[] bytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
			String content = new String(bytes);
			System.out.println("content:"+content);
		}
		httpResponse.close();
	}

}
