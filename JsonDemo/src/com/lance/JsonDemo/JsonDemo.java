package com.lance.JsonDemo;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.lance.utils.FastJsonUtils;
import com.lance.utils.GsonUtils;
import com.lance.utils.OrgJsonUtils;

public class JsonDemo {

	private static String gsonString = null ;
	private static String fastJsonString = null ;
	public static void main(String[] args)
	{
		User user = new User(1,"张三",22 ,new Brithday(1992,1,2));
		//1 JavaBean>JsonString
		gsonString = GsonUtils.toJSONString(user);
		fastJsonString = FastJsonUtils.toJSONString(user);//FastJsonUtils.toJSONString(user);
		System.out.println("Gson| JavaBean > JsonString: "+gsonString);
		System.out.println("FastJson| JavaBean > JsonString: "+fastJsonString);
		System.out.println("--------------------------------------------------------");
		//2.JsonString >JavaBean
		
		User gUser= GsonUtils.parseObject(gsonString,User.class);
		User fUser = FastJsonUtils.parseObject(fastJsonString,User.class);
		System.out.println("Gson |jsonString>JavaBean:"+gUser);
		System.out.println("FastJson| jsonString>JavaBean:"+fUser);

		System.out.println("--------------------------------------------------------");
		//3.Object 转为 JsonString
		Group group = new Group();
		group.setId(1L);
		group.setName("firstGroup");
		group.addUser(new User(1,"张三",22 ,new Brithday(1992,1,2)));
		group.addUser(new User(2,"李四",23 ,new Brithday(1993,1,3)));
		group.addUser(new User(3,"王五",24 ,new Brithday(1994,1,4)));
		
		gsonString = GsonUtils.toJSONString(group);
		fastJsonString = FastJsonUtils.toJSONString(group);
		System.out.println("Gson | Object<T> > JsonString: "+gsonString);
		System.out.println("FastJson | Object<T> > JsonString: "+fastJsonString);
		System.out.println("--------------------------------------------------------");
		
		//4. jsonString 转换为 Object
		Group gGoup = GsonUtils.parseObject(gsonString, Group.class);
		Group fGoup = FastJsonUtils.parseObject(fastJsonString, Group.class);
		System.out.println("Gson | JsonString >Object[T]: "+gGoup.toString());
		System.out.println("Fastjson | JsonString >Object[T]: "+fGoup.toString());
		System.out.println("--------------------------------------------------------");
		

		//5. List<T> 转换为 jsonString 
		List<User> users = new ArrayList<User>();
		users = group.getUsers();
		
		gsonString = GsonUtils.toJSONString(users);
		fastJsonString = FastJsonUtils.toJSONString(users);

		System.out.println("Gson |   List<T> > JsonString:"+gsonString);
		System.out.println("FastJson | List<T> > JsonString : "+fastJsonString);
		System.out.println("--------------------------------------------------------");
		

		//6. jsonString > List<T>
		List<User> fUsers = FastJsonUtils.toList(fastJsonString, User.class);
		List<User> gUsers = GsonUtils.toList(gsonString, User.class);
		
		System.out.println("Gson | jsonString > List<T> :" +gUsers);// User里面的Int类型都变成了Double;
		System.out.println("FastJson | jsonString > List<T> :" +fUsers);
		System.out.println("--------------------------------------------------------");
	
	}

	


}
