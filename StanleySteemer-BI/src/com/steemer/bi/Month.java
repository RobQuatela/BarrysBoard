package com.steemer.bi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class Month {

	private int MonthID;
	private String monthName;
	
	public Month(int monthID, String monthName) {
		this.MonthID = monthID;
		this.monthName = monthName;
	}

	public int getMonthID() {
		return MonthID;
	}

	public void setMonthID(int monthID) {
		MonthID = monthID;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	public static ArrayList<Month> getMonths() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Month> months = new ArrayList<>();
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("SELECT * FROM tbmonth");
			rs = ps.executeQuery();
			while(rs.next()) {
				months.add(new Month(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return months;
	}
}
