package com.lance.http.demo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lance.http.util.OkHttpUtils;

/***********************
 * @author Lance	    
 * @version 1.0        
 * @created 2018-1-3    
 ***********************/
public class OkHttpDemo {

	public static String defaultUrl= "http://roam.263mobile.cn:30001/MVNO_BSS/channel/recycleImsi";
	//token 07AA3B2225C7AF386C1F03AD59A7AA14
//		channelKey 0ACEAF99F9A59589E290FF05B904E6D6
		public static void main(String[] args) 
		{
			JSONObject jsonParams = new JSONObject();
			jsonParams.put("token", "07AA3B2225C7AF386C1F03AD59A7AA14");
			List<String> imsiList = new ArrayList<String>();
			imsiList.add("454030220206088");
			imsiList.add("454030220206089");
			jsonParams.put("imsi", imsiList);
			jsonParams.put("userId", "testId");
			System.out.println(jsonParams.toJSONString());
			String response;
			try
			{
				OkHttpUtils okhttp = new OkHttpUtils();
				okhttp.postJson(defaultUrl, jsonParams.toJSONString(), new OkHttpUtils.HttpCallback() {
					
					@Override
					public void onSuccess(String data) {
						// TODO Auto-generated method stub
						
					}
					
			        // 开始
			        public void onStart() {
			        	
			        	System.out.println("onStart");
			        };

			        // 成功回调

			        // 失败回调
			        public void onError(String msg) {
			        	
			        	System.out.println("msg");
			        	
			        };
				});
			} catch (Exception e) {
				System.out.println("IOException");
				e.printStackTrace();
			}
		
			
		}
	
}
