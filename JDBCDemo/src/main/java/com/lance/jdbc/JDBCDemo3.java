package com.lance.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;


/**
 * 
 * 简易用户管理——开销户系统：
 * 
 * 检测用户是否存在  查询,避免重复用户名
 * 用户注册 添加 :批量开户以及默认密码，个别开户...
 * 用户登录 查询: 不同账号类别登陆页面...,根据流程图细化
 * 
 * 用户修改密码,用户名  更新 :管理员恢复初始密码 ...
 * 用户销户   删除 :批量销户，个别销户...
 * 。。。
 * 账号类别，权限：eg 管理员，
 * 
 * */
public class JDBCDemo3 {

	//JDBC Driver & DB_URL & &DB_USERNAME,DB_PASSWD 
	private static final String filePath = "src/db.properties";
	private static String JDBC_DRIVER = null ;
	private static String DB_URL = null ;
	private static String DB_USERNAME = null ;
	private static String DB_PASSWD = null;
	
	//DB sql 
	private static String DB_SQL_QUERY = "select count(*)  countUser from test_user " ;
	private static String DB_SQL_ADD ="insert into test_user (username,passwd)";
	private static String DB_SQL_DEL ="delete from test_user ";
	private static String DB_SQL_MODIFY ="update  test_user ";
	//client data
	private static String USERNAME = null ;
	private static String PASSWD = null ;
	private static String NEWPWD = null ;


	/*
	 * JDBC初始化加载
	 * */
	static
	{
		Properties props = new Properties();
		InputStream inStream;
		try
		{
			inStream = new FileInputStream(filePath);
			props.load(inStream);
			
			JDBC_DRIVER = props.getProperty("jdbc_driver");
			DB_URL = props.getProperty("db_url");
			DB_USERNAME = props.getProperty("db_username");
			DB_PASSWD = props.getProperty("db_passwd");
		
			System.out.println("DB_URL:"+DB_URL
					+"\n DB_USERNAME:"+DB_USERNAME
					+"\n DB_PASSWD:"+DB_PASSWD);
			
			Class.forName(JDBC_DRIVER);
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 获取数据库连接
	 * */
	public static Connection getConnection()
	{
		Connection conn = null ;
		try 
		{
			conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWD);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
	/*
	 * 关闭数据库连接
	 * */
	public static void closeConnection(Connection conn)
	{
		try 
		{
			if(conn !=null)
			{
				conn.close();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	/*
	 * 查询用户1 Statement
	 * */
	
	public static int JDBCQuery1(Connection conn ,String sql)
	{
		int countUser = 0 ;
		Statement stmt = null ;
		ResultSet rs = null ;
		try
		{
			if(conn !=null )
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);//处理sql，并且查询
				if(rs.next())
				{
					countUser= rs.getInt("countUser");
					System.out.println( "countUser="+countUser);
				}	
			}
			
		} 
		catch 
		(SQLException e)
		{
			e.printStackTrace();
			return countUser;
		}
		finally
		{
			try
			{
				if(rs != null)
				{
					rs.close();
					
				}	
				if(rs != null)
				{
					stmt.close();
				}	
			
				closeConnection(conn);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return countUser;
	}
	
	
	/*
	 * 实现Object数组表示的参数列表和SQL语句中的"?"绑定
	 * 
	 */
	public static void setPreparedStatementParas(PreparedStatement pstmt,
			Object[] paras) throws SQLException
	{
		if (paras != null && paras.length > 0)
		{
			for (int i = 0; i < paras.length; i++)
			{
				if (paras[i] == null) {
					// 注意，SQL不支持java.sql.Types.NULL，所以使用java.sql.Types.VARCHAR代替
					pstmt.setNull(i + 1, java.sql.Types.VARCHAR);
				} else {
					// begin from index 1
					pstmt.setObject(i + 1, paras[i]);
				}
			}
		}
	}
	
	public static int JDBCQuery2(Connection conn ,String sql,Object[] paras)
	{
		int countUser = 0 ;
		PreparedStatement preStmt = null ;
		ResultSet rs = null ;
		try
		{
			if(conn !=null )
			{	
				preStmt = conn.prepareStatement(sql);//预处理sql
				
				setPreparedStatementParas(preStmt,paras);
				
				rs = preStmt.executeQuery();//直接查询
				if(rs.next())
				{
					countUser= rs.getInt("countUser");
					System.out.println( "countUser="+countUser);
				}	
			}
			
		} 
		catch 
		(SQLException e)
		{
			e.printStackTrace();
			return countUser;
		}
		finally
		{
			try
			{
				if(rs != null)
				{
					rs.close();
					
				}	
				if(rs != null)
				{
					preStmt.close();
				}	
			
				closeConnection(conn);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return countUser;
	}
	
	/*
	 * 插入用户
	 * */
	public static int JDBCUpdate(Connection conn ,String sql ,Object[] paras) throws Exception
	{
		int count =0;
		PreparedStatement preStmt = null ;
		if(conn !=null)
		{
			try
			{
				preStmt = conn.prepareStatement(sql);
				setPreparedStatementParas(preStmt,paras);
				count = preStmt.executeUpdate();
			
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new Exception("** Error in JDBCUpdate():" + e.getMessage()
						+ "**", e);
			}
			finally
			{
				try
				{
					if(preStmt!=null)
					{
						preStmt.close();
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
				closeConnection(conn);
			}
			
		}
		
		return count ;
	}
	

	/*
	 * 控制事务非自动化
	 * */
	public static void startTransaction(Connection conn,boolean autoCommit) {
		try 
		{
			if (conn != null)
			{
				// 得到当前线程上绑定连接开启事务
				conn.setAutoCommit(autoCommit);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/*
	 * 提交事务
	 * */
	public static void commit(Connection conn) {
		try 
		{
			if (conn != null)
			{
				conn.commit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 事务回滚
	 * */
	public static void rollback(Connection conn) {
		try 
		{
			if (conn != null)
			{
				conn.rollback();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 用户注册
	 * */
	public static boolean userRegister(String username,String passwd)
	{
			int count = 0;
			
			if ( username == null ||username.length() == 0||
					passwd == null ||passwd.length() == 0)
	        {
	    	   System.out.println("用户名或密码为空!");
	           return false;
	        }
			
			Connection conn = getConnection();
			String sql = DB_SQL_ADD+" values (?,?)";
			Object[] obj = new Object[2];
			obj[0] = username;
			obj[1] = passwd ;
			System.out.println("obj0="+obj[0]+";obj1="+obj[1]);
			if(conn !=null)
			{
				startTransaction(conn,false);
				
				try
				{
					count = JDBCUpdate(conn,sql,obj);
					System.out.println("count="+count);
					
					if(count>=1)
					{
						System.out.println("注册成功");
					}
					else
					{
						System.out.println("注册失败");
					}
					commit(conn);
					return count>=1?true:false;
				} catch (Exception e) {
					rollback(conn);
					e.printStackTrace();
				}
				
				
			}
			else
			{
				rollback(conn);
				throw new RuntimeException("数据库连接异常");
			}
			return count>=1?true:false;
	}
	
		
	
	/*
	 * 检查用户是否存在
	 * */
	
	public static boolean hasUser(String username)
	{
		int  countUser = 0;
		
		if ( username == null ||username.length() == 0)
        {
    	   System.out.println("用户名或密码为空!");
    	   return false;
        }
		PreparedStatement preStmt =null;
		Connection conn = getConnection();
		ResultSet rs = null ;
		if(conn !=null)
		{
			try
			{
				String mysql = DB_SQL_QUERY+"where username = ?";
				Object[] obj = new Object[1];
				obj[0]=username;
				
				preStmt= conn.prepareStatement(mysql);
				setPreparedStatementParas(preStmt,obj);
				rs = preStmt.executeQuery();
				if(rs.next())
				{
					countUser= rs.getInt("countUser");
					System.out.println( "countUser="+countUser);
					return countUser>0 ? true : false;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
			}
				
		}
		
		return countUser>0 ? true : false;
	}
	
	/*
	 * 用户注销
	 * */
	
	public static boolean deleteUser(String username,String passwd)
	{
		int count = 0;
		
		if ( username == null ||username.length() == 0||
				passwd == null ||passwd.length() == 0)
        {
    	   System.out.println("用户名或密码为空!");
           return  false;
        }
		
		Connection conn = getConnection();
		String sql = DB_SQL_DEL+" where username = ? and passwd = ?";
		Object[] obj = new Object[2];
		obj[0] = username;
		obj[1] = passwd ;
		System.out.println("obj0="+obj[0]+";obj1="+obj[1]);
		if(conn !=null)
		{
			startTransaction(conn,false);
			try
			{
				count = JDBCUpdate(conn,sql,obj);
				System.out.println("count="+count);
				
				if(count>=1)
				{
					System.out.println("用户注销成功");
				}
				else
				{
					System.out.println("用户注销失败");
				}
				commit(conn);
			} catch (Exception e) 
			{
				rollback(conn);
				e.printStackTrace();
			}
		}
		return count >=1 ?true:false;
	}
	
	
	/*
	 * 修改密码
	 * */
	
	public static boolean updatePwd(String username,String oldPwd,String newPwd)
	{
		if ( username == null ||username.length() == 0||
				oldPwd == null ||oldPwd.length() == 0||
				newPwd == null ||newPwd.length() == 0)
        {
           return  false;
        }
		
		PreparedStatement preStmt = null ;
		Connection conn = getConnection();
		String mysql =DB_SQL_MODIFY+
				" set  passwd = ? where username = ? and passwd = ?";
		int count = 0;
		if(conn != null)
		{
			Object[] paras = new Object[3];
			paras[0] = newPwd;
			paras[1] = username;
			paras[2] = oldPwd;
			
			startTransaction(conn,false);
			try 
			{
				count = JDBCUpdate(conn,mysql,paras);
				System.out.println("count="+count);
				if(count==1)
				{
					System.out.println("用户修改密码成功");
				}
				else
				{
					System.out.println("用户修改密码失败");
				}
				commit(conn);
			}
			catch (Exception e)
			{
				rollback(conn);
				e.printStackTrace();
			}
			
			
		}
		return count>0 ?true:false;
	}
	
	
	/*
	 * 用户登录 
	 * */
	public static void userLogin(String username, String passwd) 
	{
		int countUser = 0 ;
	
		if ( username == null ||username.length() == 0||
				passwd == null ||passwd.length() == 0)
        {
    	   System.out.println("用户名或密码为空!");
           return ;
        }
		
		Connection conn = getConnection();
		
		/*String query = DB_SQL_QUERY
				+"where username = '"+username
			+ "' and passwd = '"+passwd +"'" ;  // SQL 注入 : ' or '1'='1
		System.out.println("query:"+query);
		
		countUser = JDBCQuery1(conn,query);
		*/
		String mysql = DB_SQL_QUERY
			+"where username = ? "
			+"and passwd = ?";//SQL注入无效 :' or '1'='1
		System.out.println("query:"+mysql);
		
		Object[] objs = new Object[2];
		objs[0] = username;
		objs[1] = passwd ;
		countUser = JDBCQuery2(conn,mysql,objs);
		
		
		if(countUser >= 1)
		{
	          System.out.println("恭喜您登陆成功");
        }
		
		else
		{
          System.out.println("去注册吧!");
		}
		
	}

	
	//public static void main(String[] args)
	{
		System.out.println("用户名:");
		Scanner scanner = new Scanner(System.in);
		USERNAME = scanner.nextLine().trim();
		System.out.println("密码:");
		scanner = new Scanner(System.in);
		System.out.println();
		PASSWD = scanner.nextLine().trim();
		
		System.out.println("user="+USERNAME +";PASSWD="+PASSWD);
		
		//userLogin(USERNAME,PASSWD);
		//userRegister(USERNAME,PASSWD);
		/*if(hasUser(USERNAME))
		{
			System.out.println("该用户名已被注册"); 
		}*/
		
		System.out.println("请输入新密码:");
		NEWPWD = scanner.nextLine().trim();
		System.out.println("user="+USERNAME +";PASSWORD="+PASSWD+"NEWPWD="+NEWPWD);

		updatePwd(USERNAME,PASSWD,NEWPWD);
	}
}