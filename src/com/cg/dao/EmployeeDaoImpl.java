package com.cg.dao;

import java.sql.*;

import org.apache.log4j.Logger;

import com.cg.bean.Employee;
import com.cg.util.*;

public class EmployeeDaoImpl implements EmployeeDao{

	private static Logger log= Logger.getLogger(EmployeeDaoImpl.class);
	
	@Override
	public boolean createUser(Employee emp) throws EmployeeException {
		// TODO Auto-generated method stub
		
		try {
			
			Connection con= new DBUtil().getConnection();
			String query= "Insert into emp values(?,?,?,?)";
			PreparedStatement pst= con.prepareStatement(query);
			pst.setInt(1, emp.getId());
			pst.setString(2, emp.getName());
			pst.setString(3, emp.getDesig());
			pst.setLong(4, emp.getMobile());
			int res= pst.executeUpdate();
			if(res>0) {
				log.info("Created User- "+emp);
				return true;
			}else {
				return false;
				}
			
		} 
		catch (SQLException e) {
			log.error("SqlError-  "+e.getMessage());
			throw new EmployeeException(EmployeeMessage.SQLEXCEPTION);
		}
		
	}

	@Override
	public void closeConnection() throws EmployeeException {
		new DBUtil().closeConnection();
		
	}

	@Override
	public boolean updateEmployee(Employee emp) throws EmployeeException {
		// TODO Auto-generated method stub
		
		try {
			Connection con= new DBUtil().getConnection();
			String sql= "update emp set ";
			if(emp.getDesig()!=null) {
				sql=sql+"desig= \'"+emp.getDesig()+"\'";
				if(emp.getMobile()!=0) {
					sql=sql+" , ";
				}
			}
			if(emp.getMobile()!=0) {
				sql=sql+"mobile= "+emp.getMobile();
			}
			sql= sql+" where id="+emp.getId();
//			System.out.println(sql);
			Statement stm= con.createStatement();
			int res=stm.executeUpdate(sql);
			
//			PreparedStatement ps= con.prepareStatement(sql);
//			int res=ps.executeUpdate();
			if(res>0) {
				log.info("Updated user with id: "+emp.getId());
				return true;
			}
			else
			return false;
			
		} catch (SQLException e) {
			log.error("Update- sqlerror- "+e.getMessage());
			throw new EmployeeException(EmployeeMessage.SQLEXCEPTION);
		}
		
	}

	@Override
	public boolean delete(int id) throws EmployeeException {
		// TODO Auto-generated method stub
		
		try {
			Connection con= new DBUtil().getConnection();
			String sql= "Delete from emp where id="+id;
			Statement stm= con.createStatement();
			int res= stm.executeUpdate(sql);
			if(res>0) {
				log.info("Deleted Employee with id- "+id);
				return true;
			}else {
				log.error("Tried Deleting Employee with id- "+id);
				return false;
			}
			
		} catch (SQLException e) {
			log.error("Delete-SQL error- "+e.getMessage());
			throw new EmployeeException(EmployeeMessage.SQLEXCEPTION);
		}
//		return false;
	}

	@Override
	public Employee display(int id) throws EmployeeException {
		// TODO Auto-generated method stub
		try {
			Connection con= new DBUtil().getConnection();
			String sql= "Select * from emp where id="+id;
			Statement stm= con.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			Employee emp = null;
			if(rs.next()) {
				emp=new Employee(id, rs.getString(2), rs.getString(3), rs.getLong(4));
				log.info("Display employee- "+emp);
				return emp;
			}
			else {
				log.error("Tried Deleting Employee with id- "+id);
				return emp;
			}
			
		} catch (SQLException e) {
			log.error("Display-SQL error- "+e.getMessage());
			throw new EmployeeException(EmployeeMessage.SQLEXCEPTION);
		}
	}

}
