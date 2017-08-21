package com.steemer.bi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Day {

	private int dayID;
	private String dayName;
	
	public Day(int dayID, String dayName) {
		this.dayID = dayID;
		this.dayName = dayName;
	}

	public int getDayID() {
		return dayID;
	}

	public void setDayID(int dayID) {
		this.dayID = dayID;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	
	public static ArrayList<Day> getDays() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Day> days = new ArrayList<>();
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("SELECT * FROM tbday");
			rs = ps.executeQuery();
			while(rs.next()) {
				days.add(new Day(rs.getInt(1), rs.getString(2)));
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
		
		return days;
	}
}
