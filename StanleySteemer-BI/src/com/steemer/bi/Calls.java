package com.steemer.bi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public Calls(int call_id, int company, int year, int month, int week, int day, LocalDate date, int callOrders, int onlineOrders,
			int bookedOrders, int lossOrders, int estimateOrders) {
		this.call_id = call_id;
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
	
	public Calls(int company, int year, int month, int week, int day) {
		this.company = company;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
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
	
	public Calls getCall() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Calls call = null;
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("SELECT * FROM tborders WHERE company_id = ? AND year_id = ? AND month_id = ? AND week_id = ? AND day_id = ?");
			ps.setInt(1, this.getCompany());
			ps.setInt(2, this.getYear());
			ps.setInt(3, this.getMonth());
			ps.setInt(4, this.getWeek());
			ps.setInt(5, this.getDay());
			rs = ps.executeQuery();
			while(rs.next()) {
				call = new Calls(rs.getInt("orders_id"), rs.getInt("company_id"), rs.getInt("year_id"), rs.getInt("month_id"), rs.getInt("week_id"),
						rs.getInt("day_id"), rs.getDate("orders_date").toLocalDate(), rs.getInt("orders_calls"), rs.getInt("orders_online"),
						rs.getInt("orders_booked"), rs.getInt("orders_lost"), rs.getInt("orders_estimates"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ConnectDB.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return call;
	}
	
	public void updateCalls() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("UPDATE tborders SET orders_date = ?, orders_calls = ?, orders_online = ?, orders_booked = ?, " +
					"orders_lost = ?, orders_estimates = ? WHERE company_id = ? AND year_id = ? AND month_id = ? AND week_id = ? AND day_id = ?");
			ps.setDate(1, this.getDate());
			ps.setInt(2, this.getCallOrders());
			ps.setInt(3, this.getOnlineOrders());
			ps.setInt(4, this.getBookedOrders());
			ps.setInt(5, this.getLossOrders());
			ps.setInt(6, this.getEstimateOrders());
			ps.setInt(7, this.getCompany());
			ps.setInt(8, this.getYear());
			ps.setInt(9, this.getMonth());
			ps.setInt(10, this.getWeek());
			ps.setInt(11, this.getDay());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ConnectDB.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
