package com.cg.serv;

import com.cg.bean.Employee;
import com.cg.util.EmployeeException;

public interface EmployeeService {

	public boolean createEmp(Employee emp) throws EmployeeException;

	public void closeConnection() throws EmployeeException;

	public boolean notValid(String name);

	public boolean notValidMobile(long mobile);

	public boolean updateEmployee(Employee emp) throws EmployeeException;

	public boolean delete(int id) throws EmployeeException;

	public Employee display(int id) throws EmployeeException; 

}
