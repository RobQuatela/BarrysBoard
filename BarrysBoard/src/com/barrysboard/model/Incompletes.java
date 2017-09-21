package com.barrysboard.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Incompletes {

	private String orderID;
	private String csrID;
	private String coID;
	private LocalDate date;
	private LocalTime time;
	private String custType;
	private String jobType;
	private final String status = "I";
	private final Double amountScheduled = 0.0;
	private final Double amountTotal = 0.0;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	
	public Incompletes(String orderID, String csrID, String coID, LocalDate date, LocalTime time, String custType,
			String jobType, LocalDateTime dateCreated, LocalDateTime dateModified) {
		this.orderID = orderID;
		this.csrID = csrID;
		this.coID = coID;
		this.date = date;
		this.time = time;
		this.custType = custType;
		this.jobType = jobType;
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

	public String getCoID() {
		return coID;
	}

	public void setCoID(String coID) {
		this.coID = coID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
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

	public String getStatus() {
		return status;
	}

	public Double getAmountScheduled() {
		return amountScheduled;
	}

	public Double getAmountTotal() {
		return amountTotal;
	}
	
	private void insert() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("INSERT INTO tborders (orders_id, csr_id, co_id, date_id, orders_time, orders_jobtype, orders_custtype, " +
					"orders_status, orders_amount_scheduled, orders_amount_total, orders_date_created, orders_date_modified) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCoID());
			ps.setDate(4, Date.valueOf(this.getDate()));
			ps.setTime(5, Time.valueOf(this.getTime()));
			ps.setString(6, this.getJobType());
			ps.setString(7, this.getCustType());
			ps.setString(8, this.getStatus());
			ps.setDouble(9, this.getAmountScheduled());
			ps.setDouble(10,this.getAmountTotal());
			ps.setTimestamp(11, Timestamp.valueOf(this.getDateCreated()));
			ps.setTimestamp(12, Timestamp.valueOf(this.getDateModified()));
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
	
/*	private void update() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("UPDATE tborders SET orders_status = ?, csr_id = ?, orders_amount_scheduled = ?, orders_amount_total = ?, " +
					"orders_date_modified = ?, date_id = ?, orders_jobtype = ?, orders_time = ?, orders_custtype = ? WHERE orders_id = ? AND co_id = ?");
			ps.setString(1, this.getStatus());
			ps.setString(2, this.getCsrID());
			ps.setDouble(3, this.getAmountScheduled());
			ps.setDouble(4, this.getAmountTotal());
			ps.setTimestamp(5, Timestamp.valueOf(this.getDateModified()));
			ps.setDate(6, Date.valueOf(this.getDate()));
			ps.setString(7, this.getJobType());
			ps.setTime(8, Time.valueOf(this.getTime()));
			ps.setString(9, this.getCustType());
			ps.setString(10, this.getOrderID());
			ps.setString(11, this.getCoID());
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
	}*/
	
	private boolean checkForDup() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isDup = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tborders WHERE orders_id = ? AND co_id = ?");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCoID());
			rs = ps.executeQuery();
			if(rs.next())
				isDup = true;
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
		
		return isDup;
	}
	
/*	private boolean checkForUpdate() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isUpdate = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tborders WHERE orders_id = ? AND co_id = ? AND " +
					"(orders_jobtype != ? OR orders_custtype != ? OR orders_status != ? OR orders_amount_scheduled != ? OR " +
					"orders_amount_total != ? OR date_id != ? OR csr_id != ?)");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCoID());
			ps.setString(3, this.getJobType());
			ps.setString(4, this.getCustType());
			ps.setString(5, this.getStatus());
			ps.setDouble(6, this.getAmountScheduled());
			ps.setDouble(7, this.getAmountTotal());
			ps.setDate(8, Date.valueOf(this.getDate()));
			ps.setString(9, this.getCsrID());
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
	}*/
	
	public void authenticate() {
		boolean isDup = this.checkForDup();
		
		if(!isDup)
			this.insert();
	}
	
	
}
