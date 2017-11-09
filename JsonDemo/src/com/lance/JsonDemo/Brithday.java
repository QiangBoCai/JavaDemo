package com.lance.JsonDemo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class Brithday implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@JSONField(ordinal=1)
	private int year;
	@JSONField(ordinal=2)
	private int month;
	@JSONField(ordinal=3)
	private int day;
	
	public Brithday()
	{
		super();
	}
	
	public Brithday(int year , int month ,int day)
	{
		super();
		this.year=year;
		this.month=month;
		this.day=day;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String toString() {
		return "Brithday {year=" + year + ", month=" + month + ", day=" + day
				+ "}";
	}
	
}
