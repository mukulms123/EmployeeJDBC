package com.cg.dao;

import com.cg.bean.Employee;
import com.cg.util.EmployeeException;

public interface EmployeeDao {

	boolean createUser(Employee emp) throws EmployeeException;

	void closeConnection() throws EmployeeException;

	boolean updateEmployee(Employee emp) throws EmployeeException;

	boolean delete(int id) throws EmployeeException;

	Employee display(int id) throws EmployeeException;

}
