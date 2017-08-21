package com.steemer.bi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Week {

	private int weekID;
	
	public Week(int weekID) {
		this.setWeekID(weekID);
	}

	public int getWeekID() {
		return weekID;
	}

	public void setWeekID(int weekID) {
		this.weekID = weekID;
	}
	
	public static ArrayList<Week> getWeeks() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Week> weeks = new ArrayList<>();
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("SELECT * FROM tbweek");
			rs = ps.executeQuery();
			while(rs.next()) {
				weeks.add(new Week(rs.getInt(1)));
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
		
		return weeks;
	}
}
