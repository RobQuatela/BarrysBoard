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

public class Orders {

	//private int ID;
	private String orderID;
	private String csrID;
	private String csrName;
	private String companyID;
	private LocalDate date;
	private LocalTime time;
	private String jobType;
	private String custType;
	private String status;
	private double amountScheduled;
	private double amountTotal;
	private String address;
	private String city;
	private String state;
	private String zip;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	
	public Orders(String orderID, String csrID, String csrName, String companyID, LocalDate date, 
			LocalTime time, String jobType, String custType, String status, double amountScheduled, double amountTotal) {
		this.orderID = orderID;
		this.csrID = csrID;
		this.csrName = csrName;
		this.companyID = companyID;
		this.date = date;
		this.time = time;
		this.jobType = jobType;
		this.custType = custType;
		this.status = status;
		this.amountScheduled = amountScheduled;
		this.amountTotal = amountTotal;
	}
	
	public Orders(String orderID, String csrID, String companyID, LocalDate date, 
			LocalTime time, String jobType, String custType, String status, double amountScheduled, double amountTotal,
			String address, String city, String state, String zip, LocalDateTime dateCreated, LocalDateTime dateModified) {
		this.orderID = orderID;
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.time = time;
		this.jobType = jobType;
		this.custType = custType;
		this.status = status;
		this.amountScheduled = amountScheduled;
		this.amountTotal = amountTotal;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
	public String getOrderID() {
		return orderID;
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

	public String getCsrName() {
		return csrName;
	}

	public void setCsrName(String csrName) {
		this.csrName = csrName;
	}
	
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
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

	public double getAmountScheduled() {
		return amountScheduled;
	}

	public void setAmountScheduled(double amountScheduled) {
		this.amountScheduled = amountScheduled;
	}

	public double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(double amountTotal) {
		this.amountTotal = amountTotal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
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
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("INSERT INTO tborders (orders_id, csr_id, co_id, date_id, orders_time, orders_jobtype, orders_custtype, " +
					"orders_status, orders_amount_scheduled, orders_amount_total, orders_address, orders_city, orders_state, orders_zipcode, orders_date_created, orders_date_modified) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCompanyID());
			ps.setDate(4, Date.valueOf(this.getDate()));
			ps.setTime(5, Time.valueOf(this.getTime()));
			ps.setString(6, this.getJobType());
			ps.setString(7, this.getCustType());
			ps.setString(8, this.getStatus());
			ps.setDouble(9, this.getAmountScheduled());
			ps.setDouble(10,this.getAmountTotal());
			ps.setString(11, this.getAddress());
			ps.setString(12, this.getCity());
			ps.setString(13, this.getState());
			ps.setString(14, this.getZip());
			ps.setTimestamp(15, Timestamp.valueOf(this.getDateCreated()));
			ps.setTimestamp(16, Timestamp.valueOf(this.getDateModified()));
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
	
	private void update() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("UPDATE tborders SET orders_status = ?, csr_id = ?, orders_amount_scheduled = ?, orders_amount_total = ?, orders_date_modified = ?, date_id = ?, " +
					"orders_address = ?, orders_city = ?, orders_state = ?, orders_zipcode = ?, orders_jobtype = ?, orders_time = ? WHERE orders_id = ? AND co_id = ?");
			ps.setString(1, this.getStatus());
			ps.setString(2, this.getCsrID());
			ps.setDouble(3, this.getAmountScheduled());
			ps.setDouble(4, this.getAmountTotal());
			ps.setTimestamp(5, Timestamp.valueOf(this.getDateModified()));
			ps.setDate(6, Date.valueOf(this.getDate()));
			ps.setString(7, this.getAddress());
			ps.setString(8, this.getCity());
			ps.setString(9, this.getState());
			ps.setString(10, this.getZip());
			ps.setString(11, this.getJobType());
			ps.setTime(12, Time.valueOf(this.getTime()));
			ps.setString(13, this.getOrderID());
			ps.setString(14, this.getCompanyID());
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
		boolean isDup = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tborders WHERE orders_id = ? AND csr_id = ? AND co_id = ? AND date_id = ? AND orders_jobtype = ? AND " +
					"orders_custtype = ? AND orders_status = ? AND orders_amount_scheduled = ? AND orders_amount_total = ? AND orders_address = ? AND " +
					"orders_city = ? AND orders_state = ? AND orders_zipcode = ?");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCompanyID());
			ps.setDate(4, Date.valueOf(this.getDate()));
			ps.setString(5, this.getJobType());
			ps.setString(6, this.getCustType());
			ps.setString(7, this.getStatus());
			ps.setDouble(8, this.getAmountScheduled());
			ps.setDouble(9, this.getAmountTotal());
			ps.setString(10, this.getAddress());
			ps.setString(11, this.getCity());
			ps.setString(12, this.getState());
			ps.setString(13, this.getZip());
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
	
	private boolean checkForUpdate() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isUpdate = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tborders WHERE orders_id = ? AND co_id = ? AND " +
					"(orders_jobtype != ? OR orders_custtype != ? OR orders_status != ? OR orders_amount_scheduled != ? OR " +
					"orders_amount_total != ? OR date_id != ? OR csr_id != ? OR orders_address != ? OR orders_city !=? OR orders_state != ? OR orders_zipcode != ?)");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCompanyID());
			ps.setString(3, this.getJobType());
			ps.setString(4, this.getCustType());
			ps.setString(5, this.getStatus());
			ps.setDouble(6, this.getAmountScheduled());
			ps.setDouble(7, this.getAmountTotal());
			ps.setDate(8, Date.valueOf(this.getDate()));
			ps.setString(9, this.getCsrID());
			ps.setString(10, this.getAddress());
			ps.setString(11, this.getCity());
			ps.setString(12, this.getState());
			ps.setString(13, this.getZip());
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
		
		if(!isDup)
			if(isUpdate)
				this.update();
			else
				this.insert();
	}
	
	
}
