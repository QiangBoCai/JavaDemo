package com.lance.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class C3P0Demo {
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
		
		Connection conn = C3P0DBManager.getConnection();
		//String mysql = DB_SQL_ADD+" values (?,?)";
		String oracle_sql = "insert into test_user (user_id,username,passwd)values (seq_on_test_user.nextval,?,?)"; 
		Object[] paras = new Object[2];
		paras[0] = username;
		paras[1] = passwd ;
		System.out.println("paras0="+paras[0]+";paras1="+paras[1]);
		if(conn !=null)
		{
			C3P0DBManager.setAutoCommit(conn,false);
			
			try
			{
				count = C3P0DBManager.executeUpdate(conn, oracle_sql, paras);
				System.out.println("count="+count);
				
				if(count>=1)
				{
					System.out.println("注册成功");
				}
				else
				{
					System.out.println("注册失败");
				}
				C3P0DBManager.commit(conn);
				
				return count>=1?true:false;
			} 
			catch (Exception e) 
			{
				C3P0DBManager.rollback(conn);
				e.printStackTrace();
			}
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
		Connection conn = C3P0DBManager.getConnection();
		ResultSet rs = null ;
		if(conn !=null)
		{
			try
			{
				String mysql = DB_SQL_QUERY+"where username = ? ";
				Object[] paras = new Object[1];
				paras[0]=username;
				System.out.println("mysql:"+DB_SQL_QUERY
									+"where username = '"+username+"'");
				rs = C3P0DBManager.executeQuery(conn, mysql, paras);
				if(rs.next())
				{
					countUser= rs.getInt("countUser");
					System.out.println( "countUser="+countUser);
					return countUser>0 ? true : false;
				}
			}
			catch (Exception e)
			{
				System.out.println("查询查错");
				e.printStackTrace();
			}
			finally
			{

				try
				{
					if(rs !=null)
					{
						rs.close();	
					}
					C3P0DBManager.closeConnection(conn);
				}
				catch
				(SQLException e) 
				{
					e.printStackTrace();
				}
				
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
		
		Connection conn =C3P0DBManager.getConnection();
		String mysql = DB_SQL_DEL+" where username = ? and passwd = ?";
		Object[] paras = new Object[2];
		paras[0] = username;
		paras[1] = passwd ;
		System.out.println("paras0="+paras[0]+";paras1="+paras[1]);
		if(conn !=null)
		{
			C3P0DBManager.setAutoCommit(conn, false);
			try
			{
				count = C3P0DBManager.executeUpdate(conn, mysql, paras);
				System.out.println("count="+count);
				
				if(count>=1)
				{
					System.out.println("用户注销成功");
				}
				else
				{
					System.out.println("用户注销失败");
				}
				C3P0DBManager.commit(conn);
			} catch (Exception e) 
			{
				C3P0DBManager.rollback(conn);
				e.printStackTrace();
			}
			
		}
		return count>=1?true:false;
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
		
		Connection conn = C3P0DBManager.getConnection();
		String mysql =DB_SQL_MODIFY+
				" set  passwd = ? where username = ? and passwd = ?";
		int count = 0;
		if(conn != null)
		{
			Object[] paras = new Object[3];
			paras[0] = newPwd;
			paras[1] = username;
			paras[2] = oldPwd;
			C3P0DBManager.setAutoCommit(conn, false);
			try 
			{
				count = C3P0DBManager.executeUpdate(conn, mysql, paras);
				System.out.println("count="+count);
				if(count==1)
				{
					System.out.println("用户修改密码成功");
				}
				else
				{
					System.out.println("用户修改密码失败");
				}
				C3P0DBManager.commit(conn);
			}
			catch (Exception e)
			{
				C3P0DBManager.rollback(conn);
				e.printStackTrace();
			}
			
			
		}
		return count>0 ?true:false;
	}
	
	
	/*
	 * 用户登录 
	 * */
	public static boolean userLogin(String username, String passwd) 
	{
		int countUser = 0 ;
	
		if ( username == null ||username.length() == 0||
				passwd == null ||passwd.length() == 0)
        {
    	   System.out.println("用户名或密码为空!");
           return false;
        }
		if(hasUser(username))
		{
			Connection conn = C3P0DBManager.getConnection();
			
			ResultSet rs = null ;
			String mysql = DB_SQL_QUERY
				+"where username = ? "
				+"and passwd = ?";//SQL注入无效 :' or '1'='1
			System.out.println("query:"+mysql);
			
			Object[] paras = new Object[2];
			paras[0] = username;
			paras[1] = passwd ;
			try 
			{
				rs = C3P0DBManager.executeQuery(conn, mysql, paras);
				if(rs.next())
				{
					countUser= rs.getInt("countUser");
					if(countUser >= 1)
					{
				          System.out.println("恭喜您登陆成功");
			        }
					
					else
					{
			          System.out.println("密码错误!");
					}
				}
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}finally
			{
				try
				{
					if(rs !=null)
					{
						rs.close();	
					}
					C3P0DBManager.closeConnection(conn);
				}
				catch
				(SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			System.out.println("该用户不存在，去注册吧！");
		}
		
		return countUser >= 1?true:false;
		
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
		if(userLogin(USERNAME,PASSWD))
		{
			System.out.println("请输入新密码:");
			NEWPWD = scanner.nextLine().trim();
			System.out.println("user="+USERNAME +";PASSWORD="+PASSWD+"NEWPWD="+NEWPWD);

			updatePwd(USERNAME,PASSWD,NEWPWD);
		}

	}
	
	

}
