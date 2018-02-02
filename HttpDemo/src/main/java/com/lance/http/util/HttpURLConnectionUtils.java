package com.lance.http.util;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class HttpURLConnectionUtils {

	

	
	public static String doPostJSON(String url,String jsonString,String who ,String clientIP) throws IOException
	{
		if((null == url)||( null == jsonString))
		{
			return "";
		}
		OutputStream outputStream = null;
	    OutputStreamWriter outputStreamWriter = null;
		InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuilder rspContent = new StringBuilder();
        String tempLine = null;
		try {
			//创建URL资源
			URL localURL = new URL(url);
			//建立http连接
			URLConnection urlConn = localURL.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection)urlConn;
			//允许输入输出
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			 // 设置不用缓存
			httpURLConnection.setUseCaches(false);
			// 设置传递方式
			httpURLConnection.setRequestMethod("POST");
			// 设置维持长连接
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			 // 设置文件字符集:
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 设置文件长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(jsonString.length()));
            // 设置文件类型:
            httpURLConnection.setRequestProperty("contentType", "application/json");

            if(!(null == who || "".equals(who)))
			{
				httpURLConnection.setRequestProperty("who", who);
			}
			if(!(null == clientIP || "".equals(clientIP)))
			{
				httpURLConnection.setRequestProperty("clientIP", clientIP);
			}
			httpURLConnection.setConnectTimeout(18000);
		    httpURLConnection.setReadTimeout(18000);
			
		    // 开始连接请求
		    httpURLConnection.connect();
            

		    outputStream = httpURLConnection.getOutputStream();
		    outputStreamWriter = new OutputStreamWriter(outputStream);
		    outputStreamWriter.write(jsonString);
		    outputStreamWriter.flush();
		    int rspCode = httpURLConnection.getResponseCode();
		    System.out.println("rspCode:"+rspCode);
		    
		    if (rspCode >= 300) {
				throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }else if (rspCode == 200)
            {
            	 inputStream = httpURLConnection.getInputStream(); // 消息响应
                 inputStreamReader = new InputStreamReader(inputStream);
                 reader = new BufferedReader(inputStreamReader);

                 while ((tempLine = reader.readLine()) != null) 
                 {
                	 rspContent.append(tempLine);
                 }
            }

		    
		    
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
		
		return rspContent.toString();
		
	}
}
