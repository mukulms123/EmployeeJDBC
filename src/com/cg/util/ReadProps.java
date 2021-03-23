package com.cg.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;


public class ReadProps {
	Properties ps;
	String file= "lib/db.property";
	
	public ReadProps() {
	}
	
	public ReadProps(String file) {
		this.file = file;
	}
	
	public Map<String, String> getProps() throws EmployeeException{
		Map<String, String> map= new HashMap<String, String>();
		ps= new Properties();
		Logger log= Logger.getLogger(ReadProps.class);
		try {
			ps.load(new FileInputStream(file));
			Enumeration<?> props= ps.propertyNames();
			while(props.hasMoreElements()) {
				String key= (String) props.nextElement();
				map.put(key, ps.getProperty(key));
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			throw new EmployeeException(EmployeeMessage.FILENOTFOUND);
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new EmployeeException(EmployeeMessage.IOEXCEPTION);
		}
		
		return map;
		
	}
	
	
}
