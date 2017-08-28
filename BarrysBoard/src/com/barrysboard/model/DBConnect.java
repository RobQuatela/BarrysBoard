package com.barrysboard.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static String connString = "jdbc:mysql://localhost:3306/dbbarrysboard?autoReconnect=true&useSSL=false";
	private static String driver = "com.mysql.jdbc.Driver";
	private static Connection con;
	private static String user = "root";
	private static String password = "tturtles";
	
	public static Connection connect() throws SQLException {
		
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		con = DriverManager.getConnection(connString, user, password);
		
		return con;
	}
	
	public static void close() throws Exception {
		con.close();
	}
}
