package com.lance.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})//注解作用域：可以用在哪些地方
/* TYPE :类，接口
 * METHOD:方法
 * CONSTRUCTOR:构造方法声明
 * FIELD 字段声明
 * ...
 */
@Retention(RetentionPolicy.RUNTIME)//注解生命周期
/*
 * SOURCE:源码显示，编译时丢弃
 * CLASS:编译时记录到.class中，运行时忽略
 * RUNTIME:运行时有效，可以通过反射读取
 */
@Documented //生成javadoc时包含注解
@Inherited //注解可以没有成员，叫标识注解，它允许子注解继承
public @interface Description {//@interface关键字定义一个注解，注解不是接口
	//String desc() default ""; //desc()类似接口里面的方法，但是在注解里面它只是一个成员变量，一般以无参无异常的方式声明；
	//String author();//成员类型只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型
					     //和String,Enum,Class,annotations等数据类型
						  //  第一,只能用public或默认(default)这两个访问权修饰,默认是default
	//int age() default 18; //成员变量可以使用default 指定一个默认值,使用赋值等号赋值，多个成员之间，逗号隔开
	  String value();					//如果只有一个成员，则成员名必须取名为value(); 使用的时候可以忽略成员名和赋值等号 eg:@SuppressWarnings("deprecation")
	
}
