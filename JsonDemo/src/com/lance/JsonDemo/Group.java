package com.lance.JsonDemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Group implements Serializable{
	
	@JSONField(ordinal=1)
	private Long id;
	@JSONField(ordinal=2)
	private String name ;
	@JSONField(ordinal=3)
	private List<User> users = new ArrayList<User>();
	
	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Group(Long id, String name, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	public void addUser(User user)
	{
		users.add(user);
	}

	@Override
	public String toString() {
		return "Group {id=" + id + ", name=" + name + ", users=" + users + "}";
	}
	
	
}
