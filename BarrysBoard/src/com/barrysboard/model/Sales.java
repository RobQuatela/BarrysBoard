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
	private String address;
	private String city;
	private String state;
	private String zip;
	private String commID;
	private String advertising;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	
	public Sales(String orderID, String csrID, String companyID, LocalDate date, String jobType, String custType, String status,
			double scheduledAmount, double totalAmount, String address, String city, String state, String zip) {
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
			double scheduledAmount, double totalAmount, String address, String city, String state, String zip, String advertising,
			LocalDateTime dateCreated, LocalDateTime dateModified) {
		this.orderID = orderID;
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.jobType = jobType;
		this.custType = custType;
		this.status = status;
		this.scheduledAmount = scheduledAmount;
		this.totalAmount = totalAmount;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.advertising = advertising;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}
	
	public Sales(String orderID, String commID) {
		this.orderID = orderID;
		this.commID = commID;
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

	public String getCommID() {
		return commID;
	}

	public void setCommID(String commID) {
		this.commID = commID;
	}

	public String getAdvertising() {
		return advertising;
	}

	public void setAdvertising(String advertising) {
		this.advertising = advertising;
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
		String commID;
		
		try {
			commID = this.getCommID();
		} catch(Exception e) {
			commID = null;
		}
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement(
					"INSERT INTO tbsales (sales_id, csr_id, co_id, date_id, sales_jobtype, sales_custtype, sales_status, sales_amount_scheduled, sales_amount_total, "
							+ "sales_address, sales_city, sales_state, sales_zipcode, comm_id, sales_advertising, sales_date_created, sales_date_modified) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCompanyID());
			ps.setDate(4, Date.valueOf(this.getDate()));
			ps.setString(5, this.getJobType());
			ps.setString(6, this.getCustType());
			ps.setString(7, this.getStatus());
			ps.setDouble(8, this.getScheduledAmount());
			ps.setDouble(9, this.getTotalAmount());
			ps.setString(10, this.getAddress());
			ps.setString(11, this.getCity());
			ps.setString(12, this.getState());
			ps.setString(13, this.getZip());
			ps.setString(14, commID);
			ps.setString(15, this.getAdvertising());
			ps.setTimestamp(16, Timestamp.valueOf(this.getDateCreated()));
			ps.setTimestamp(17, Timestamp.valueOf(this.getDateModified()));
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
			ps = con.prepareStatement("UPDATE tbsales SET sales_amount_scheduled = ?, sales_amount_total = ?, sales_date_modified = ?, date_id = ?, sales_jobtype = ?, " +
					"sales_custtype = ?, sales_status = ?, csr_id = ?, sales_address = ?, sales_city = ?, sales_state = ?, sales_zipcode = ?, comm_id = ?, sales_advertising = ? " +
					"WHERE sales_id = ? AND co_id = ?");
			ps.setDouble(1, this.getScheduledAmount());
			ps.setDouble(2, this.getTotalAmount());
			ps.setTimestamp(3, Timestamp.valueOf(this.getDateModified()));
			ps.setDate(4, Date.valueOf(this.getDate()));
			ps.setString(5, this.getJobType());
			ps.setString(6, this.getCustType());
			ps.setString(7, this.getStatus());
			ps.setString(8, this.getCsrID());
			ps.setString(9, this.getAddress());
			ps.setString(10, this.getCity());
			ps.setString(11, this.getState());
			ps.setString(12, this.getZip());
			ps.setString(13, this.getCommID());
			ps.setString(14, this.getAdvertising());
			ps.setString(15, this.getOrderID());
			ps.setString(16, this.getCompanyID());
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
			ps = con.prepareStatement("SELECT * FROM tbsales WHERE sales_id = ? AND co_id = ?");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCompanyID());
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
					"AND (sales_amount_scheduled != ? OR sales_amount_total != ? OR date_id != ? OR sales_status != ? OR sales_jobtype != ? OR sales_custtype != ? OR csr_id != ? OR " +
					"sales_address != ? OR sales_city != ? OR sales_state != ? OR sales_zipcode != ?)");
			ps.setString(1, this.getCompanyID());
			ps.setString(2, this.getOrderID());
			ps.setDouble(3, this.getScheduledAmount());
			ps.setDouble(4, this.getTotalAmount());
			ps.setDate(5, Date.valueOf(this.getDate()));
			ps.setString(6, this.getStatus());
			ps.setString(7, this.getJobType());
			ps.setString(8, this.getJobType());
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
	
	public void updateCOMM() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("UPDATE tbsales SET comm_id = ? WHERE sales_id = ?");
			ps.setString(1, this.getCommID());
			ps.setString(2, this.getOrderID());
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
	
	private void checkBacklog() {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT orders_id FROM tborders WHERE orders_id = ? AND date_id >= ? AND date_id <= ?");
			ps.setString(1, this.getOrderID());
			ps.setDate(2, Date.valueOf(this.getDate().minusYears(1)));
			ps.setDate(3, Date.valueOf(this.getDate().plusYears(1)));
			rs = ps.executeQuery();
			if(rs.next()) {
				ps1 = con.prepareStatement("UPDATE tborders SET orders_backlog = 'C', orders_backlog_date = ? WHERE orders_id = ?");
				ps1.setDate(1, Date.valueOf(this.getDate()));
				ps1.setString(2, rs.getString("orders_id"));
				ps1.executeUpdate();
			} else {
				ps1 = con.prepareStatement("UPDATE tborders SET orders_backlog = 'O' WHERE orders_id = ?");
				ps1.setString(1, this.getOrderID());
				ps1.executeUpdate();
			}
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
	
	public Sales updateOriginCSR() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT csr_id, comm_id FROM tborders WHERE orders_id = ?");
			ps.setString(1, this.getOrderID());
			rs = ps.executeQuery();
			if(rs.next()) {
				this.setCsrID(rs.getString(1));
				this.setCommID(rs.getString(2));
			}
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
		
		return this;
	}
	
	public void authenticate() {
		boolean isDup = this.checkForDup();

		if(!isDup)
			this.insert();
		else
			this.update();
		
		this.checkBacklog();
	}
	
}
