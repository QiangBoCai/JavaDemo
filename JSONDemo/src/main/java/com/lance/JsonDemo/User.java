package com.lance.JsonDemo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class User  implements Serializable{
	

	
	private static final long serialVersionUID = 1L;
	
	//fastjson序列化一个java bean，默认是根据fieldName的字母序进行序列化的;
	//你可以通过ordinal指定字段的顺序，这个特性需要1.1.42以上版本。
	//Gson 默认是字段的顺序
	 
	@JSONField(ordinal=1)
	private int userId;
	@JSONField(ordinal=2)
	private String name ;
	@JSONField(ordinal=3)
	private int age;
	@JSONField(ordinal=4)
	private Brithday brithday;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int userId,String name, int age, Brithday brithday) {
		super();
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.brithday = brithday;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Brithday getBrithday() {
		return brithday;
	}
	public void setBrithday(Brithday brithday) {
		this.brithday = brithday;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", age=" + age
				+ ", brithday=" + brithday + "]";
	}
	
	
}
