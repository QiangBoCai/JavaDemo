package com.lance.dao.EmpDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lance.entity.Emp.Emp;
import com.lance.util.C3P0DBManager;

public class EmpDao {
	
	
	//EmpDao 构造方法
	
	public EmpDao()
	{
		
	}
	
	
	/*
	 * 查询所有员工信息
	 * */
	
	public static List<Emp> getAllEmp()
	{
		List<Emp> empList = new ArrayList<Emp>();
		Connection conn =C3P0DBManager.getConnection();
		ResultSet rs= null ;
		String sql = "select emp_id,emp_name,salary from test_emp";
		Object[] paras = new Object[0];
	
		if(conn!=null)
		{
			try
			{
				rs = C3P0DBManager.executeQuery(conn, sql, paras);
	
				while(rs.next())
				{
					Emp emp =new Emp();
					emp.setEmpId(rs.getInt("emp_id"));
					emp.setEmpName(rs.getString("emp_name"));
					emp.setSalary(rs.getDouble("salary"));
					empList.add(emp);
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally
			{
				if(rs!=null)
				{
					try
					{
						rs.close();
					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				C3P0DBManager.closeConnection(conn);
			}
		}
		return empList;
	}
	
	
	//public static void main(String[] args)
	{
		List<Emp> emps= getAllEmp();
		
		for(Emp emp:emps)
		{
			System.out.println("empId="+emp.getEmpId()
							+";empName="+emp.getEmpName()
							+";empSalary="+emp.getSalary());
			
		}
	}
}
