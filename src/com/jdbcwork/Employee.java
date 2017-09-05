package com.jdbcwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//Create an Employee class
public class Employee {
	//Declare four private String variables for the employee’s first name, last name, timeIn, and timeOut. 
	private String firstName , lastName , timeIn , timeOut;
	// Declare an integer variable for the employee number. 
	int employee_num;

	//Create a constructor for this class that will take two Strings as parameters (the employee’s first and last name) 
	// assign them to the initially declared variables: firstName & lastName.
	public Employee(String first , String last) {
		this.firstName = first;
		this.lastName=last;
	}
	//Create a method called createNewEmployee(). 
	//This method will establish a database connection, and insert the employee information into the database. 
	void createNewEmployee(){
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement preparedStatement = null;
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String URL ="jdbc:oracle:thin:@localhost:1521:orcl";
		Properties info = new Properties();
		info.put("user","sys as SYSDBA");
		info.put("password", "password123");
		conn = DriverManager.getConnection(URL , info);
		stmt=conn.createStatement();
//		adding new Employee to  Employees table
		String insertTableSQL ="insert into Employees "
					+ "(firstName, lastName) VALUES"
					+ "(?,?)";
		preparedStatement = conn.prepareStatement(insertTableSQL);
		preparedStatement.setString(1, this.firstName);
		preparedStatement.setString(2, this.lastName);
		// execute insert SQL statement
		preparedStatement.executeUpdate();
		System.out.println("Employee is inserted into Employees table!");
		
		//Then execute a query to retrieve the employee number (automatically generated Unique ID) of the new employee 
		String sql="select EmployeeNo from Employees where firstName = ? and lastName =?";
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, this.firstName);
		preparedStatement.setString(2, this.lastName);
		ResultSet rs= preparedStatement.executeQuery();
		while(rs.next()) {
			// store it into the initially declared ‘employee number’ variable. 
			this.employee_num= rs.getInt("EmployeeNo");
			//Then print to the screen
			System.out.println(this.firstName+ " "+this.lastName +" your employee number is "+ this.employee_num);
		}
		//Use this number to clock in and out.” 
		//create a new InputCollector object and run the getEmployeeInput() method
		InputCollector collector = new InputCollector();
		collector.getEmployeeInput();
		conn.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}


}
