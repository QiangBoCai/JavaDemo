package com.lance.utils;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;


/**
 * Gson 工具类
 * */
public class GsonUtils {

	private static Gson gson = null ;
	
	static
	{
		if(gson == null)
		{
			gson = new Gson();
		}
	}
	
	public GsonUtils()
	{
		
	}
	
	/*
	 * 1 object 转换成jsonSring
	 * */
	
	public static String toJSONString(Object obj)
	{
		String gsonString = null ;
		if(gson!=null)
		{
			gsonString = gson.toJson(obj);
		}
		return gsonString ;
	}
	
	/*
	 * 2 gsonString  转换成 泛型javabean 
	 * */
	
	public static <T>T parseObject(String gsonString , Class<T> cls)
	{
		T t = null ;
		if(gson !=null)
		{
			
			t = gson.fromJson(gsonString, cls);
		}
		return t;
	}
	

	
	
	/*
	 * gsonString > List<T>
	 * 解决泛型问题
	 * */
	
	public static <T> List<T> toList(String gsonString,Class<T>cls)
	{
		List<T> list = new ArrayList<T>();
		JsonArray array = new JsonParser().parse(gsonString).getAsJsonArray();
		for(final JsonElement elem :array)
		{
			list.add(gson.fromJson(elem, cls));
		}
		return list;
	}
	
	
	/*
	 * 转成list中的map
	 * */
	
	public static <T> List<Map<String,T>> toListMap(String gsonString)
	{
		List<Map<String,T>> list = null;
		if(gson !=null)
		{
			list =gson.fromJson(gsonString, 
					new TypeToken<List<Map<String,T>>>(){}.getType());
		}
		return list;
	}
	
	/*
	 *  转成map
	 * */
	
	public static<T> Map<String,T> toMap(String gsonString,Class<T>clz)
	{
		Type type = new TypeToken<Map<String,T>>(){}.getType();
		return gson.fromJson(gsonString, type);
		
	}
}
