package com.steemer.bi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Calls {

	private int call_id;
	private String company;
	private int year;
	private String month;
	private int week;
	private String day;
	private int callOrders;
	private int onlineOrders;
	private int bookedOrders;
	private int lossOrders;
	private int estimateOrders;
	
	public Calls(String company, int year, String month, int week, String day, int callOrders, int onlineOrders,
			int bookedOrders, int lossOrders, int estimateOrders) {
		this.company = company;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.callOrders = callOrders;
		this.onlineOrders = onlineOrders;
		this.bookedOrders = bookedOrders;
		this.lossOrders = lossOrders;
		this.estimateOrders = estimateOrders;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getCallOrders() {
		return callOrders;
	}

	public void setCallOrders(int callOrders) {
		this.callOrders = callOrders;
	}

	public int getOnlineOrders() {
		return onlineOrders;
	}

	public void setOnlineOrders(int onlineOrders) {
		this.onlineOrders = onlineOrders;
	}

	public int getBookedOrders() {
		return bookedOrders;
	}

	public void setBookedOrders(int bookedOrders) {
		this.bookedOrders = bookedOrders;
	}

	public int getLossOrders() {
		return lossOrders;
	}

	public void setLossOrders(int lossOrders) {
		this.lossOrders = lossOrders;
	}

	public int getEstimateOrders() {
		return estimateOrders;
	}

	public void setEstimateOrders(int estimateOrders) {
		this.estimateOrders = estimateOrders;
	}

	public int getCall_id() {
		return call_id;
	}
	
	public void insert() {
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Administrator.STEEMER/SharePoint/Quatela Group - Documents/Quatela Group/Data-BI-Quatela.accdb");
			ps = con.prepareStatement("INSERT INTO tbcalls (company, call_year, call_month, call_week, call_day, call_callorders, call_onlineorders, "
					+ "call_bookedorders, call_lossorders, call_estimateorders) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, this.getCompany());
			ps.setInt(2, this.getYear());
			ps.setString(3, this.getMonth());
			ps.setInt(4, this.getWeek());
			ps.setString(5, this.getDay());
			ps.setInt(6, this.getCallOrders());
			ps.setInt(7, this.getOnlineOrders());
			ps.setInt(8, this.getBookedOrders());
			ps.setInt(9, this.getLossOrders());
			ps.setInt(10, this.getEstimateOrders());
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
