package com.steemer.bi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jobs {

	private int jobsID;
	private String company;
	private int year;
	private String month;
	private int week;
	private String day;
	private int jobsCore;
	private int jobsAirduct;
	private int jobsTotal;
	
	public Jobs(String company, int year, String month, int week, String day, int jobsCore, int jobsAirduct) {
		this.company = company;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.jobsCore = jobsCore;
		this.jobsAirduct = jobsAirduct;
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

	public int getJobsCore() {
		return jobsCore;
	}

	public void setJobsCore(int jobsCore) {
		this.jobsCore = jobsCore;
	}

	public int getJobsAirduct() {
		return jobsAirduct;
	}

	public void setJobsAirduct(int jobsAirduct) {
		this.jobsAirduct = jobsAirduct;
	}

	public int getJobsID() {
		return jobsID;
	}

	public int getJobsTotal() {
		return jobsTotal;
	}
	
	public void insert() {
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Administrator.STEEMER/SharePoint/Quatela Group - Documents/Quatela Group/Data-BI-Quatela.accdb");
			ps = con.prepareStatement("INSERT INTO tbjobs (company, jobs_year, jobs_month, jobs_week, jobs_day, jobs_core, jobs_airduct) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, this.getCompany());
			ps.setInt(2, this.getYear());
			ps.setString(3, this.getMonth());
			ps.setInt(4, this.getWeek());
			ps.setString(5, this.getDay());
			ps.setInt(6, this.getJobsCore());
			ps.setInt(7, this.getJobsAirduct());
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
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
