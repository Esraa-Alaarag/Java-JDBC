package com.jdbcwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

//Create another class by extending the Employee class and call it ‘existingEmployee’
public class ExistingEmployee extends Employee {
	
public ExistingEmployee(String first, String last) {
		super(first, last);
		// TODO Auto-generated constructor stub
	}

	//	Create a method called clockIn(). 
//	This method will take the employee number as a parameter. 
	void clockIn(int num) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement preparedStatement = null;
//		It will then create a new date object, convert it to a string and store in into the ‘timeIn’ variable.
		Date now = new Date();
		String timeIn = now.toString();
//		Then establish a database connection 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String URL ="jdbc:oracle:thin:@localhost:1521:orcl";
			Properties info = new Properties();
			info.put("user","sys as SYSDBA");
			info.put("password", "password123");
			conn = DriverManager.getConnection(URL , info);
			stmt=conn.createStatement();
			// update the ‘time¬_in’ column for that employee with the ‘timeIn’ variable. 
			String updateTableSQL ="update Employees "
						+ "set time_in= ?"
						+ "where EmployeeNo=?";
			preparedStatement = conn.prepareStatement(updateTableSQL);
			preparedStatement.setString(1, timeIn);
			preparedStatement.setInt(2, num);
			// execute update SQL statement
			preparedStatement.executeUpdate();
			System.out.println("Employee timein inserted successsfully into Employees table!");
//			Then perform a query to retrieve the employee’s information, using the employee number as a reference, 
			String sql="select firstName , lastName , time_in from Employees where EmployeeNo = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			ResultSet rs= preparedStatement.executeQuery(sql);
			while(rs.next()) {
				// store it into the initially declared ‘employee number’ variable. 
				String firstName= rs.getString("firstName");
				String lastName=  rs.getString("lastName");
				timeIn=  rs.getString("time_in");
//				and print to the screen …“ ‘firstName’, you’ve clocked in at ‘timeIn’ ”. 
				System.out.println(firstName+ " "+lastName +" you’ve clocked in at "+ timeIn);
			}
			conn.close();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
//	Create a method called clockOut() . 
//	This method will take the employee number as a parameter. 
	void clockOut(int num) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement preparedStatement = null;
//		It will then create a new date object, convert it to a string and store in into the ‘timeIn’ variable.
		Date now = new Date();
		String timeOut  = now.toString();
//		Then establish a database connection 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String URL ="jdbc:oracle:thin:@localhost:1521:orcl";
			Properties info = new Properties();
			info.put("user","sys as SYSDBA");
			info.put("password", "password123");
			conn = DriverManager.getConnection(URL , info);
			stmt=conn.createStatement();
			// update the ‘time¬_in’ column for that employee with the ‘timeIn’ variable. 
			String updateTableSQL ="update Employees "
						+ "set time_out= ?"
						+ "where EmployeeNo=?";
			preparedStatement = conn.prepareStatement(updateTableSQL);
			preparedStatement.setString(1, timeOut);
			preparedStatement.setInt(2, num);
			// execute update SQL statement
			preparedStatement.executeUpdate();
			System.out.println("Employee timeOut inserted successsfully into Employees table!");
//			Then perform a query to retrieve the employee’s information, using the employee number as a reference, 
			String sql="select firstName , lastName , time_out from Employees where EmployeeNo = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			ResultSet rs= preparedStatement.executeQuery(sql);
			while(rs.next()) {
				// store it into the initially declared ‘employee number’ variable. 
				String firstName= rs.getString("firstName");
				String lastName=  rs.getString("lastName");
				timeOut=  rs.getString("time_out");
//				and print to the screen …“ ‘firstName’, you’ve clocked in at ‘time_out’ ”. 
				System.out.println(firstName+ " "+lastName +" you’ve clocked out at "+ timeOut);
			}
			conn.close();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		
	}
	//Create a method called editInfo that will take the employee number as a parameter. 
	void editInfo (int num) {
		Scanner sc = new Scanner(System.in);
		//Prompt the user to ‘Enter Updated First Name’,
		System.out.println("Enter Updated First Name");
		String firstName = sc.nextLine();
		//then ‘Enter Updated Last Name’ and assign the input collected to the firstName and lastName variables.
		System.out.println("Enter Updated Last Name");
		String lastName = sc.nextLine();
		//Establish a database connection, 
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement preparedStatement = null;
//		Then establish a database connection 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String URL ="jdbc:oracle:thin:@localhost:1521:orcl";
			Properties info = new Properties();
			info.put("user","sys as SYSDBA");
			info.put("password", "password123");
			conn = DriverManager.getConnection(URL , info);
			stmt=conn.createStatement();
			//and update the ‘firstName’ and ‘lastName’ columns in the database using the employee number as a reference.
			String updateTableSQL ="update Employees "
						+ "set firstName= ? , lastName= ?"
						+ "where EmployeeNo=?";
			preparedStatement = conn.prepareStatement(updateTableSQL);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setInt(3, num);
			// execute update SQL statement
			preparedStatement.executeUpdate();
			//Then print to the screen “Entry Successful, Your information has been updated”.
			System.out.println("Entry Successful, Your information has been updated!");
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
