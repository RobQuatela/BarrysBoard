package com.steemer.bi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String conString = "jdbc:mysql://localhost:3306/dbsteemerbi?autoReconnect=true&useSSL=false";
	private static Connection con;
	private static String user = "root";
	private static String password = "P@ssG0!";
	
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
		
		con = DriverManager.getConnection(conString, user, password);
		
		return con;

	}
	
	public static void close() throws Exception {
		con.close();
	}
}
