package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet 开发步骤 ：
 * 
 * 1. myeclipse 创建一个web 工程，创建class 继承HttpServlet类
 * 
 * 2. 重写 init service,doGet ,doPost,doPut,destory等方法
 * 	  主要是doGet和doPost方法。
 * 
 * 3.配置 web.xml 生成webapp
 * 				appname  
				|--html,jsp,css,js文件等，web应用根目录下,外界可以直接访问的到。eg:index.jsp
				|--WEB-INF 目录，由web服务器调度，外界无法直接访问。
					|--classess	Java类
					|--lib	jar包
					|--web.xml web应用配置文件  
					eg:应用首页
					  <welcome-file-list>
 	<!-- Servlet 配置
 		注意： servlet-class 要带包名，servlet和servlet-mapping的servlet-name 名称可以随便取，但是要一致。
 			url-pattern 代表servlet-url 匹配
 			参考:[servlet-url 简介](http://blog.csdn.net/csdn_gia/article/details/53442475)
  	-->
  <servlet>
  	<servlet-name>HelloServlet</servlet-name>
 	<servlet-class>com.example.servlet.HelloServlet</servlet-class>
  </servlet>
  <servlet-mapping>
  	<servlet-name>HelloServlet</servlet-name>
  	<url-pattern>/HelloServlet</url-pattern>
  </servlet-mapping>
  
  4.run as MyEclipse Server Application编译,打包,自动拷贝到Servlet容器tomcat的webapp目录下
  	
  	注：window>preferences>MyEclipse>Servers>tomcat>enable 并指定Tomcat安装目录
 
  5.自动启动servlet容器 
  	
  6.访问
 	http://ip:port/appname/servlet-url
  
 
 * 
 */
public class HelloServlet  extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public HelloServlet()
	{
		System.out.println("Create HelloServlet Instance");
	}
	
	
	public void service(HttpServletRequest request ,HttpServletResponse response) 
	{
		System.out.println("HelloServlet service ");
	
		String name = request.getParameter("name");
		
		response.setContentType("text/html;charset=utf-8");
		
		
		try 
		{
			PrintWriter output=response.getWriter();
			output.println("Hello "+name);
			output.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
