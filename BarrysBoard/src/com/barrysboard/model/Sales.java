package com.barrysboard.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class Sales {

	private String orderID;
	private String csrID;
	private String companyID;
	private Date date;
	private String jobType;
	private String custType;
	private double scheduledAmount;
	private double totalAmount;
	
	public Sales(String orderID, String csrID, String companyID, LocalDate date, String jobType, String custType,
			double scheduledAmount, double totalAmount) {
		this.orderID = orderID;
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = Date.valueOf(date);
		this.jobType = jobType;
		this.custType = custType;
		this.scheduledAmount = scheduledAmount;
		this.totalAmount = totalAmount;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getCsrID() {
		return csrID;
	}

	public void setCsrID(String csrID) {
		this.csrID = csrID;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public double getScheduledAmount() {
		return scheduledAmount;
	}

	public void setScheduledAmount(double scheduledAmount) {
		this.scheduledAmount = scheduledAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void insert() {
		Connection con = null;
		PreparedStatement ps = null;
		boolean check = this.checkForDup();
		
		if(!check) {
			try {
				con = DBConnect.connect();
				ps = con.prepareStatement(
						"INSERT INTO tbsales (sales_id, csr_id, date_id, sales_jobtype, sales_custtype, sales_amount_scheduled, sales_amount_total "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, this.getOrderID());
				ps.setString(2, this.getCsrID());
				ps.setDate(3, this.getDate());
				ps.setString(4, this.getJobType());
				ps.setString(5, this.getCustType());
				ps.setDouble(6, this.getScheduledAmount());
				ps.setDouble(7, this.getTotalAmount());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					DBConnect.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private boolean checkForDup() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean check = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbsales WHERE date_id = ? AND co_id = ? AND sales_id = ?");
			ps.setDate(1, this.getDate());
			ps.setString(2, this.getCompanyID());
			ps.setString(3, this.getOrderID());
			rs = ps.executeQuery();
			if(rs.next()) 
				check = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBConnect.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return check;
	}
	
}
