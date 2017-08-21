package com.steemer.bi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Sales {

	private int sales_id;
	private int company;
	private int year;
	private int month;
	private int week;
	private int day;
	private Date date;
	private int salesCore;
	private int salesAirduct;
	private int totalSales;
	
	public Sales(int company, int year, int month, int week, int day, LocalDate date, int totalSales, int salesAirduct) {
		this.company = company;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.date = Date.valueOf(date);
		this.totalSales = totalSales;
		this.salesAirduct = salesAirduct;
	}
	
	public Sales(int salesID, int company, int year, int month, int week, int day, LocalDate date, int totalSales, int salesAirduct) {
		this.sales_id = salesID;
		this.company = company;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.date = Date.valueOf(date);
		this.totalSales = totalSales;
		this.salesAirduct = salesAirduct;
	}
	
	public Sales(int company, int year, int month, int week, int day) {
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

	public int getSalesCore() {
		return salesCore;
	}

	public void setSalesCore(int salesCore) {
		this.salesCore = salesCore;
	}

	public int getSalesAirduct() {
		return salesAirduct;
	}

	public void setSalesAirduct(int salesAirduct) {
		this.salesAirduct = salesAirduct;
	}

	public int getSales_id() {
		return sales_id;
	}

	public int getTotalSales() {
		return totalSales;
	}
	
	public void insert() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("INSERT INTO tbsales (company_id, year_id, month_id, week_id, day_id, sales_date, sales_total, sales_airduct) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, this.getCompany());
			ps.setInt(2, this.getYear());
			ps.setInt(3, this.getMonth());
			ps.setInt(4, this.getWeek());
			ps.setInt(5, this.getDay());
			ps.setDate(6, this.getDate());
			ps.setInt(7, this.getSalesCore());
			ps.setInt(8, this.getSalesAirduct());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Sales getSales() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sales sale = null;
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("SELECT * FROM tbsales WHERE company_id = ? AND year_id = ? AND month_id = ? " +
					"AND week_id = ? AND day_id = ?");
			ps.setInt(1, this.getCompany());
			ps.setInt(2, this.getYear());
			ps.setInt(3, this.getMonth());
			ps.setInt(4, this.getWeek());
			ps.setInt(5, this.getDay());
			rs = ps.executeQuery();
			if(rs.next()) {
				sale = new Sales(rs.getInt("sales_id"), rs.getInt("company_id"), rs.getInt("year_id"),
						rs.getInt("month_id"), rs.getInt("week_id"), rs.getInt("day_id"), rs.getDate("sales_date").toLocalDate(),
						rs.getInt("sales_total"), rs.getInt("sales_airduct"));
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
		
		return sale;
	}
}
