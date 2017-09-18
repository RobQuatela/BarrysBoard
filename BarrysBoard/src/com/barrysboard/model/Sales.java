package com.barrysboard.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Sales {

	private String orderID;
	private String csrID;
	private String companyID;
	private LocalDate date;
	private String jobType;
	private String custType;
	private String status;
	private double scheduledAmount;
	private double totalAmount;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	
	public Sales(String orderID, String csrID, String companyID, LocalDate date, String jobType, String custType, String status,
			double scheduledAmount, double totalAmount) {
		this.orderID = orderID;
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.jobType = jobType;
		this.custType = custType;
		this.status = status;
		this.scheduledAmount = scheduledAmount;
		this.totalAmount = totalAmount;
	}
	
	public Sales(String orderID, String csrID, String companyID, LocalDate date, String jobType, String custType, String status,
			double scheduledAmount, double totalAmount, LocalDateTime dateCreated, LocalDateTime dateModified) {
		this.orderID = orderID;
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.jobType = jobType;
		this.custType = custType;
		this.status = status;
		this.scheduledAmount = scheduledAmount;
		this.totalAmount = totalAmount;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateModified() {
		return dateModified;
	}

	public void setDateModified(LocalDateTime dateModified) {
		this.dateModified = dateModified;
	}

	private void insert() {
		Connection con = null;
		PreparedStatement ps = null;
		boolean check = this.checkForDup();
		
		if(!check) {
			try {
				con = DBConnect.connect();
				ps = con.prepareStatement(
						"INSERT INTO tbsales (sales_id, csr_id, co_id, date_id, sales_jobtype, sales_custtype, sales_status, sales_amount_scheduled, sales_amount_total, sales_date_created, sales_date_modified) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, this.getOrderID());
				ps.setString(2, this.getCsrID());
				ps.setString(3, this.getCompanyID());
				ps.setDate(4, Date.valueOf(this.getDate()));
				ps.setString(5, this.getJobType());
				ps.setString(6, this.getCustType());
				ps.setString(7, this.getStatus());
				ps.setDouble(8, this.getScheduledAmount());
				ps.setDouble(9, this.getTotalAmount());
				ps.setTimestamp(10, Timestamp.valueOf(this.getDateCreated()));
				ps.setTimestamp(11, Timestamp.valueOf(this.getDateModified()));
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
	
	private void update() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("UPDATE tbsales SET sales_amount_scheduled = ?, sales_amount_total = ?, sales_date_modified = ?, date_id = ? WHERE " +
					"sales_id = ? AND co_id = ?");
			ps.setDouble(1, this.getScheduledAmount());
			ps.setDouble(2, this.getTotalAmount());
			ps.setTimestamp(3, Timestamp.valueOf(this.getDateModified()));
			ps.setDate(4, Date.valueOf(this.getDate()));
			ps.setString(5, this.getOrderID());
			ps.setString(6, this.getCompanyID());
			
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
	
	private boolean checkForDup() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean check = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbsales WHERE date_id = ? AND co_id = ? AND sales_id = ?");
			ps.setDate(1, Date.valueOf(this.getDate()));
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
	
	private boolean checkForUpdate() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isUpdate = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbsales WHERE co_id = ? AND sales_id = ? " +
					"AND (sales_amount_scheduled != ? OR sales_amount_total != ? OR date_id != ?)");
			ps.setString(1, this.getCompanyID());
			ps.setString(2, this.getOrderID());
			ps.setDouble(3, this.getScheduledAmount());
			ps.setDouble(4, this.getTotalAmount());
			ps.setDate(5, Date.valueOf(this.getDate()));
			rs = ps.executeQuery();
			if(rs.next()) 
				isUpdate = true;
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
		
		return isUpdate;
	}
	
	public void authenticate() {
		boolean isDup = this.checkForDup();
		boolean isUpdate = this.checkForUpdate();
		
		if(!isDup) {
			if(isUpdate)
				this.update();
			else
				this.insert();
		} //else {
		//	this.insert();
		//}
	}
	
}
