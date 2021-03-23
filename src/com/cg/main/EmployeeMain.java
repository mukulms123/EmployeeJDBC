package com.cg.main;

import com.cg.bean.Employee;
import com.cg.serv.EmployeeService;
import com.cg.serv.EmployeeServiceImpl;
import java.util.*;
import com.cg.util.*;

public class EmployeeMain {
	
	private EmployeeService es;
	
	public EmployeeMain() {
		es= new EmployeeServiceImpl();
	}
	
	public void execute() {
		int choice=0;
		Scanner sc= new Scanner(System.in);
		while(true) {
			choice= getChoice(sc);
			switch(choice) {
				case 1:
					System.out.println("Create user");
					System.out.println("Enter user Details: ");
					boolean valid=true;
					int id;
					String name;
					String desig;
					long mobile;
					Employee emp;
					do {
						try {
							System.out.println("Enter ID");
							id= sc.nextInt();
							do {
								System.out.println("Enter Name");
								name= sc.next();
							}while(es.notValid(name));
							System.out.println("Enter Desig");
							desig= sc.next();
							do {
								System.out.println("Enter Mobile number");
								mobile= sc.nextLong();
							}while(es.notValidMobile(mobile));
							
							
							emp= new Employee(id, name, desig, mobile);
							valid=true;
							boolean res;
							res = es.createEmp(emp);
							if(res) {
								System.out.println("User created successfully");
							}else {
								System.out.println("Couldn't create User");
							}
						}
						catch(InputMismatchException e) {
							sc.nextLine();
							valid=false;
						}catch (EmployeeException e) {
							System.out.println("Couldn't create User");
							System.out.println(e.getMessage());
						}
					}while(!valid);	
					break;
				case 2:
					System.out.println("Update user");
					do {
						try {
							System.out.println("Enter Id of user you want to Update");
							id= sc.nextInt();
							name=null;
							System.out.println("Enter New Designation? (y|n)");
							String c= sc.next();
							if(c.charAt(0)=='y' || c.charAt(0)=='Y') {
								System.out.println("Enter Desig");
								desig=sc.next();
							}else {
								desig=null;
							}
							System.out.println("Enter New Mobile? (y|n)");
							c= sc.next();
							if(c.charAt(0)=='y' || c.charAt(0)=='Y') {
								do {
									System.out.println("Enter Mobile");
									mobile=sc.nextLong();
								}
								while(es.notValidMobile(mobile));
							}else {
								mobile=0;
							}
							
							emp= new Employee(id, name, desig, mobile);
//							System.out.println(emp);
							boolean res = es.updateEmployee(emp);
							if(res) {
								System.out.println("User updated successfully");
							}else {
								System.out.println("Couldn't update User");
							}
							valid = true;
						}
						catch(InputMismatchException e) {
							sc.nextLine();
							valid=false;
							System.out.println("Please make valid Enteries");
							
						} catch (EmployeeException e) {
							valid = true;
							System.out.println("Couldn't update Employee");
							System.out.println(e.getMessage());
						}
					}while(!valid);
					
					break;
				case 3:
					System.out.println("Delete user");
					
					try {
						System.out.println("Enter Id of user to delete.");
						id= sc.nextInt();
						boolean res= es.delete(id);
						if(res) {
							System.out.println("Successfully deleted Employee");
						}else {
							System.out.println("Employee wasn't deleted");
						}
					} catch (EmployeeException e) {
						System.out.println("Couldn't delete Employee");
						System.out.println(e.getMessage());
						
					}
					break;
				case 4:
					System.out.println("Display user");
					try {
						System.out.println("Enter Id of user to display.");
						id= sc.nextInt();
						emp= es.display(id);
						if(emp!=null) {
							System.out.println(emp);
						}else {
							System.out.println("Couldn't fetch employee");
						}
					} catch (EmployeeException e) {
						System.out.println("Couldn't fetch Employee");
						System.out.println(e.getMessage());
						
					}
					break;
				case 0:
					try {
						es.closeConnection();
					} catch (EmployeeException e) {
						System.out.println(e.getMessage());
					}
					System.exit(0);
					break;
				default:
					break;
			}
			
		}
	}

	public static int getChoice(Scanner sc) {
		System.out.println("Employee System, enter your choice: ");
		System.out.println("1. Create Employee");
		System.out.println("2. Update Employee");
		System.out.println("3. Delete Employee");
		System.out.println("4. Display Employee");
		System.out.println("0. Exit Employee");
		int choice = sc.nextInt();
		return choice;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeMain em= new EmployeeMain();
		em.execute();
	}

}
