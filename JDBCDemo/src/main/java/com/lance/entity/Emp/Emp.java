package com.lance.entity.Emp;

import java.io.Serializable;

public class Emp implements Serializable{//2. 对象序列化
	
	//1.显示一个无参构造方法
	
	public Emp()
	{
		
	}
	//2.私有属性
	private int empId;
	private String empName;
	private double salary;
	
	
	//3.公有get&set方法 ,快捷键 alt+shift+s
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
