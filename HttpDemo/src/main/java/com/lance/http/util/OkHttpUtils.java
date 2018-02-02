package com.lance.http.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {
	    public static String TAG="debug-okhttp";
	    public static boolean isDebug=true;

	    private OkHttpClient client;
	    // 超时时间
	    public static final int TIMEOUT = 1000 * 60;

	    //json请求
	    public static final MediaType JSON = MediaType
	            .parse("application/json; charset=utf-8");


	    public OkHttpUtils() {
	        this.init();
	    }

	    private void init() {

	        client = new OkHttpClient();

	        // 设置超时时间
	        client.newBuilder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
	                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
	                .readTimeout(TIMEOUT, TimeUnit.SECONDS).build();

	    }

	    /**
	     * post请求，json数据为body
	     * 
	     * @param url
	     * @param json
	     * @param callback
	     */
	    public void postJson(String url, String json, final HttpCallback callback) {

	        RequestBody body = RequestBody.create(JSON, json);
	        Request request = new Request.Builder().url(url).post(body).build();

	        onStart(callback);

	        client.newCall(request).enqueue(new Callback() {


				public void onFailure(Call arg0, IOException arg1) {
					 onError(callback, arg1.getMessage());
		                arg1.printStackTrace();					
				}

				public void onResponse(Call arg0, Response response)
						throws IOException {
					 if (response.isSuccessful()) {
		                    onSuccess(callback, response.body().string());
		                } else {
		                    onError(callback, response.message());
		                }					
				}
	        });

	    }

	 
	    /**
	     * get请求
	     * @param url
	     * @param callback
	     */
	    public void get(String url, final HttpCallback callback) {

	        Request request = new Request.Builder().url(url).build();

	        onStart(callback);

	        client.newCall(request).enqueue(new Callback() {


				public void onFailure(Call arg0, IOException arg1) {
				     onError(callback, arg1.getMessage());
		                arg1.printStackTrace();					
				}

				public void onResponse(Call arg0, Response response)
						throws IOException {
				    if (response.isSuccessful()) {
	                    onSuccess(callback, response.body().string());
	                } else {
	                    onError(callback, response.message());
	                }					
				}
	        });

	    }


	    private void onStart(HttpCallback callback) {
	        if (null != callback) {
	            callback.onStart();
	        }
	    }

	    private void onSuccess(final HttpCallback callback, final String data) {


	    }

	    private void onError(final HttpCallback callback,final String msg) {

	    }


	    /**
	     * http请求回调
	     * 
	     *
	     */
	    public static abstract class HttpCallback {
	        // 开始
	        public void onStart() {};

	        // 成功回调
	        public abstract void onSuccess(String data);

	        // 失败回调
	        public void onError(String msg) {};
	    }

}
