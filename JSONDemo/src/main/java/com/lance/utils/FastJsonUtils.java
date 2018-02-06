package com.lance.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lance.JsonDemo.User;

public class FastJsonUtils 
{
	private static final SerializeConfig config ;
	
	static
	{
		config = new SerializeConfig();
		//使用json-lib 兼容的日期输出格式
		config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
		config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
	} 

	private static final SerializerFeature[] features = 
	{
		
		SerializerFeature.WriteMapNullValue, // 输出空置字段  
        SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null  
        SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null  
        SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null  
        SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null  
	};

	
	/*
	 * object eg:JavaBean,object[]
	 *  转换成jsonSring
	 * */
	
	public static String toJSONString(Object object)
	{
		
		return JSON.toJSONString(object,config,features) ;
	}
	
	public static Object toJSON(Object obj)
	{
		return JSON.toJSON(obj);
	}
	/*
	 * 1.2 jsonString  转换成 泛型javabean 
	 * */
	
	public static <T>T parseObject(String jsonString , Class<T> cls)
	{
		
		return JSON.parseObject(jsonString,cls);
	}
	
	/*
	 * jsonString 转换成Object
	 * */
	public static Object parseObject(String jsonString )
	{
		
		return JSON.parseObject(jsonString);
	}
	
	/*
	 * jsonString > List<T>
	 * */
	public static <T> List<T> toList(String jsonString, Class<T> cls)
	{
		
		return JSON.parseArray(jsonString, cls);
	}

	/*
	 * jsonString > List<Map<String,Object>>
	 * */
	public static <T> List<T> toListMap(String jsonString, Class<T> cls)
	{
		return JSON.parseArray(jsonString, cls);
	}
	
	
	/*
	 * jsonString >  Map<String,Object>>
	 * */
	public static Map toMap(String jsonString)
	{
		Map map = JSON.parseObject(jsonString);
		return map;
	}

	
	/*
	 *   Map<String,Object>>  >jsonString 
	 * */
	public static String toMap(Map map)
	{
		String jsonString= JSON.toJSONString(map);
		return jsonString;
	}

}
