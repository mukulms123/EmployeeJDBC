package com.cg.util;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

public class DBUtil {
	
	private static Map<String, String> props;
	private static Connection con;
	public DBUtil() throws EmployeeException {
		Logger log= Logger.getLogger(DBUtil.class);
		props= new ReadProps().getProps();
		try {
			Class.forName(props.get("DRIVER"));
			if(con==null||con.isClosed()) {
				
				con = DriverManager.getConnection(props.get("URL"), props.get("USER"), props.get("PASSWORD"));
				log.info("DB Connected- "+props);
			}
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFound- "+e.getMessage());
			throw new EmployeeException(EmployeeMessage.CLASSNOTFOUND);
		} catch (SQLException e) {
			log.error("Sql Error- "+e.getMessage());
			throw new EmployeeException(EmployeeMessage.SQLEXCEPTION);
		}
		
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public void closeConnection() throws EmployeeException {
		Logger log= Logger.getLogger(DBUtil.class);
		try {
			if(con==null||con.isClosed()) {
				return;
			}
			else {
				log.info("DB connection clossed");
				con.close();
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new EmployeeException(EmployeeMessage.SQLEXCEPTION);
		}
	}

}
