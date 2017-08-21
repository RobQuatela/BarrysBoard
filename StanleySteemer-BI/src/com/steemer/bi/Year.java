package com.steemer.bi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Year {

	private int yearID;
	
	public Year(int yearID) {
		this.yearID = yearID;
	}

	public int getYearID() {
		return yearID;
	}

	public void setYearID(int yearID) {
		this.yearID = yearID;
	}
	
	public static ArrayList<Year> getYears() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Year> years = new ArrayList<>();
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("SELECT * FROM tbyear");
			rs = ps.executeQuery();
			while(rs.next()) {
				years.add(new Year(rs.getInt(1)));
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
		
		return years;
	}
}
