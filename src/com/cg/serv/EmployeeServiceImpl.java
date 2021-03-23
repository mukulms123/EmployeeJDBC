package com.cg.serv;

import com.cg.bean.Employee;
import com.cg.dao.EmployeeDao;
import com.cg.dao.EmployeeDaoImpl;
import com.cg.util.EmployeeException;

public class EmployeeServiceImpl implements EmployeeService{

	EmployeeDao eDao;
	public EmployeeServiceImpl() {
		eDao= new EmployeeDaoImpl();
	}
	
	@Override
	public boolean createEmp(Employee emp) throws EmployeeException {
		// TODO Auto-generated method stub
		return eDao.createUser(emp);
	}

	@Override
	public void closeConnection() throws EmployeeException {
		// TODO Auto-generated method stub
		eDao.closeConnection();
	}

	@Override
	public boolean notValid(String name) {
		// TODO Auto-generated method stub
		return !name.matches("[A-Z][a-zA-Z]+");
	}

	@Override
	public boolean notValidMobile(long mobile) {
		// TODO Auto-generated method stub
		return !Long.toString(mobile).matches("[7-9][0-9]{9}");
	}

	@Override
	public boolean updateEmployee(Employee emp) throws EmployeeException {
		// TODO Auto-generated method stub
		return eDao.updateEmployee(emp);
	}

	@Override
	public boolean delete(int id) throws EmployeeException {
		// TODO Auto-generated method stub
		return eDao.delete(id);
	}

	@Override
	public Employee display(int id) throws EmployeeException {
		// TODO Auto-generated method stub
		return eDao.display(id);
	}
	
	
	

}
