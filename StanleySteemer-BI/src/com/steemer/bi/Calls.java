package com.steemer.bi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Calls {

	private int call_id;
	private int company;
	private int year;
	private int month;
	private int week;
	private int day;
	private Date date;
	private int callOrders;
	private int onlineOrders;
	private int bookedOrders;
	private int lossOrders;
	private int estimateOrders;
	
	public Calls(int company, int year, int month, int week, int day, LocalDate date, int callOrders, int onlineOrders,
			int bookedOrders, int lossOrders, int estimateOrders) {
		this.company = company;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.date = Date.valueOf(date);
		this.callOrders = callOrders;
		this.onlineOrders = onlineOrders;
		this.bookedOrders = bookedOrders;
		this.lossOrders = lossOrders;
		this.estimateOrders = estimateOrders;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("INSERT INTO tborders (company_id, year_id, month_id, week_id, day_id, orders_date, orders_calls, orders_online, "
					+ "orders_booked, orders_lost, orders_estimates) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, this.getCompany());
			ps.setInt(2, this.getYear());
			ps.setInt(3, this.getMonth());
			ps.setInt(4, this.getWeek());
			ps.setInt(5, this.getDay());
			ps.setDate(6, this.getDate());
			ps.setInt(7, this.getCallOrders());
			ps.setInt(8, this.getOnlineOrders());
			ps.setInt(9, this.getBookedOrders());
			ps.setInt(10, this.getLossOrders());
			ps.setInt(11, this.getEstimateOrders());
			ps.executeUpdate();
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
