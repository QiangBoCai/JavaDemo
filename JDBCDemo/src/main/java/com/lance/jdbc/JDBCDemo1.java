package com.lance.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo1 {

	//JDBC Driver & DB_URL & &DB_USERNAME,DB_PASSWD 
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWD = "we2345678";
	
//	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//  private static final String DB_URL= "jdbc:oracle:thin:@localhost:1521:Lance";
//	private static final String DB_USERNAME = "system";
//	private static final String DB_PASSWD = "Xtao12345678";
	
	//DB sql 
	private static final String DB_SQL_QUERY = "SELECT  id , name , url ,alexa ,country FROM  websites";
	private static final String DB_SQL_ADD ="INSERT INTO websites(name,url,alexa,country)values('myblog','www.bocaifintech.com',1,'China')";
	private static final String DB_SQL_UPDATE ="UPDATE websites SET name='shujushouge'  WHERE url = 'www.bocaifintech.com'";
	private static final String DB_SQL_DEL ="DELETE FROM websites WHERE url = 'www.bocaifintech.com' ";
	
	 //JDBC Instance init 
	private static Connection conn = null ;
	private static Statement stmt = null ;
	private static ResultSet rs = null ;
	

	
	

	
	private static void JDBCClose() 
	{
		try
		{
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}


	public static void JDBCInit(String jdbcDriver,String dbUrl,String dbUserName,String dbPasswd)
	{
		try
		{
			
			Class.forName(jdbcDriver);
			System.out.println("JDBC Demo conn +:"+conn);
			conn=DriverManager.getConnection(dbUrl,dbUserName,dbPasswd);
			System.out.println("JDBC Demo conn -:"+conn);		
			stmt = conn.createStatement();
		}
		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
		}	
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static int  JDBCUpdate(String sql)
	{
		int count= 0 ;
		try
		{
			count =stmt.executeUpdate(sql);
			System.out.println("JDBCUpate - count:"+count);
			return count;
		}
		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
		}	

		System.out.print("JDBC Update over");
		return count ;		
	}
	
	
	public static void JDBCQuery(String query)
	{
		
		
		try
		{
			rs =stmt.executeQuery(DB_SQL_QUERY);
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String url = rs.getString("url");
				int alexa = rs.getInt("alexa");
				String country = rs.getString("country");
				
				System.out.print("ID:"+id);
				System.out.print(",Name:"+name);
				System.out.print(",URL:"+url);
				System.out.print(",ALEXA:"+alexa);
				System.out.print(",COUNTRY:"+country);

				System.out.print("\n");
			}
			
			rs.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取元数据信息
	 */
	public static void getMetaData(String sql)
	{
		
		try 
		{
			if(conn != null)
			{
				DatabaseMetaData dbMetaData = conn.getMetaData();
				//获取数据库版本		
				String dbProductName = dbMetaData.getDatabaseProductName();
				String dbProductVersion = dbMetaData.getDatabaseProductVersion();
				System.out.println("dbProductName="+dbProductName+";dbProductVersion="+dbProductVersion);
				//获取表信息
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsMetaData = rs.getMetaData();
				
				//遍历输出表信息；// 注意遍历  ResultSet时， 结果集 id 从1 开始
				for(int i = 1 ;i<=rsMetaData.getColumnCount();i++)
				{
					System.out.println("ResultSet columnCount="+rsMetaData.getColumnCount()
							+" columnName="+rsMetaData.getColumnName(i)
							+" columnTypeName="+rsMetaData.getColumnTypeName(i)
							+" columnDisplaySize="+rsMetaData.getColumnDisplaySize(i));
				}
			}
			else
			{
				System.out.println("conn="+conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//public static void main(String[] args)
	{
		JDBCInit(JDBC_DRIVER,DB_URL,DB_USERNAME,DB_PASSWD);
		
		//JDBCQuery(DB_SQL_QUERY);
		//JDBCUpdate(DB_SQL_ADD);
		JDBCQuery(DB_SQL_QUERY);
		//getMetaData(DB_SQL_QUERY);
		JDBCClose();
	}
	
}
