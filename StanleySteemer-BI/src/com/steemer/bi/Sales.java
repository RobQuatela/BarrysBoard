package com.steemer.bi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sales {

	private int sales_id;
	private String company;
	private int year;
	private String month;
	private int week;
	private String day;
	private int salesCore;
	private int salesAirduct;
	private int totalSales;
	
	public Sales(String company, int year, String month, int week, String day, int salesCore, int salesAirduct) {
		this.company = company;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.salesCore = salesCore;
		this.salesAirduct = salesAirduct;
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
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Administrator.STEEMER/SharePoint/Quatela Group - Documents/Quatela Group/Data-BI-Quatela.accdb");
			ps = con.prepareStatement("INSERT INTO tbsales (company, sales_year, sales_month, sales_week, sales_day, sales_core, sales_airduct) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, this.getCompany());
			ps.setInt(2, this.getYear());
			ps.setString(3, this.getMonth());
			ps.setInt(4, this.getWeek());
			ps.setString(5, this.getDay());
			ps.setInt(6, this.getSalesCore());
			ps.setInt(7, this.getSalesAirduct());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
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
}
