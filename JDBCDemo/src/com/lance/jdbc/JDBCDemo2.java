package com.lance.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCDemo2 
{
	
	//JDBC Driver & DB_URL & &DB_USERNAME,DB_PASSWD 
	private static final String filePath = "src/com/lance/config/db.properties";
	private static String JDBC_DRIVER = null ;
	private static String DB_URL = null ;
	private static String DB_USERNAME = null ;
	private static String DB_PASSWD = null;
	
	//DB sql 
	private static final String DB_SQL_QUERY = "SELECT  id , name , url ,alexa ,country FROM  websites";
	private static final String DB_SQL_ADD ="INSERT INTO websites(name,url,alexa,country)values('myblog','www.bocaifintech.com',1,'China')";
	private static final String DB_SQL_UPDATE ="UPDATE websites SET name='shujushouge'  WHERE url = 'www.bocaifintech.com'";
	private static final String DB_SQL_DEL ="DELETE FROM websites WHERE url = 'www.bocaifintech.com' ";
	
	
	static
	{
		try
		{
			Properties props = new Properties();
			InputStream inStream = new FileInputStream(filePath);
			props.load(inStream);
			JDBC_DRIVER = props.getProperty("jdbc_driver");
			DB_URL = props.getProperty("db_url");
			DB_USERNAME = props.getProperty("db_username");
			DB_PASSWD = props.getProperty("db_passwd");
			Class.forName(JDBC_DRIVER);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		Connection conn = null;
		try 
		{
			System.out.println("JDBC Demo conn +:"+conn);
			conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWD);
			System.out.println("JDBC Demo conn +:"+conn);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return conn ;
		
	}
	
	public static void closeConnection(Connection conn)
	{
		try 
		{
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
	
	public static ResultSet executeQuery(Connection conn,String sql)
	{
		ResultSet rs = null ;
		Statement stmt = null ;
		try 
		{
			if(conn != null)
			{
				 stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			}
			else
			{
				System.out.println("conn is null");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static int executeUpdate(Connection conn ,String sql)
	{
		int count = -1;
		Statement stmt = null ;
		if(conn != null )
		{
			try 
			{
				stmt = conn.createStatement();
				count = stmt.executeUpdate(sql);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				try 
				{
					if(stmt!=null)
					{
						stmt.close();
	
					}
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return count ;
	}

	public static void JDBCQuery ( String sql )
	{
		Connection conn = getConnection();
		
		ResultSet rs = executeQuery(conn , sql);
		
		try 
		{
			
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();	
				}
				closeConnection(conn);
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
	}
	

	public static int JDBCUpdate ( String sql )
	{
		Connection conn = getConnection();
		
		int number = executeUpdate(conn,sql);
		
		closeConnection(conn);
		
		return number;
	}
	
	public static void getMetaData(String sql)
	{
		Connection conn = getConnection();
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
				Statement stmt = conn.createStatement();
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
		}finally
		{
			closeConnection(conn);
		}
		
	}
	
	
	//public static void main(String[] args)
	{
		
		
		//JDBCUpdate(DB_SQL_ADD);
		//JDBCUpdate(DB_SQL_UPDATE);
		//JDBCQuery(DB_SQL_QUERY);
		getMetaData(DB_SQL_QUERY);
		
	}
}
