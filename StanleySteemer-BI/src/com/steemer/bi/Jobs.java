package com.steemer.bi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Jobs {

	private int jobsID;
	private int company;
	private int year;
	private int month;
	private int week;
	private int day;
	private Date date;
	private int jobsCore;
	private int jobsAirduct;
	private int jobsTotal;
	
	public Jobs(int company, int year, int month, int week, int day, LocalDate date, int jobsTotal, int jobsAirduct) {
		this.company = company;
		this.year = year;
		this.month = month;
		this.week = week;
		this.day = day;
		this.date = Date.valueOf(date);
		this.jobsTotal = jobsTotal;
		this.jobsAirduct = jobsAirduct;
		this.jobsTotal = jobsTotal - jobsAirduct;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDay(int day) {
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
		//String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			//con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Administrator.STEEMER/SharePoint/Quatela Group - Documents/Quatela Group/Data-BI-Quatela.accdb");
			con = ConnectDB.connect();
			ps = con.prepareStatement("INSERT INTO tbjobs (company_id, year_id, month_id, week_id, day_id, jobs_date, jobs_total, jobs_airduct) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, this.getCompany());
			ps.setInt(2, this.getYear());
			ps.setInt(3, this.getMonth());
			ps.setInt(4, this.getWeek());
			ps.setInt(5, this.getDay());
			ps.setDate(6, this.getDate());
			ps.setInt(7, this.getJobsTotal());
			ps.setInt(8, this.getJobsAirduct());
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
}
