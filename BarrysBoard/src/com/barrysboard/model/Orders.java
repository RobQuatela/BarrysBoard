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
import java.util.ArrayList;

public class Orders {

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
	private String backlog;
	private LocalDate backlogDate;
	private String commID;
	private String advertising;
	private LocalDate dateScheduled;
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
			String address, String city, String state, String zip, LocalDate dateScheduled, String advertising, LocalDateTime dateCreated,
			LocalDateTime dateModified) {
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
		this.dateScheduled = dateScheduled;
		this.advertising = advertising;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}
	
	public Orders(String orderID, String csrID, String companyID, LocalDate date, 
			LocalTime time, String jobType, String custType, String status, double amountScheduled, double amountTotal,
			String address, String city, String state, String zip, String commID, LocalDate dateScheduled, LocalDateTime dateCreated, LocalDateTime dateModified) {
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
		this.commID = commID;
		this.dateScheduled = dateScheduled;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}
	
	public Orders(String orderID, String csrID, String address, String city, String state, String zip) {
		this.orderID = orderID;
		this.csrID = csrID;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
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

	public String getBacklog() {
		return backlog;
	}

	public void setBacklog(String backlog) {
		this.backlog = backlog;
	}

	public LocalDate getBacklogDate() {
		return backlogDate;
	}

	public void setBacklogDate(LocalDate backlogDate) {
		this.backlogDate = backlogDate;
	}

	public String getCommID() {
		return commID;
	}

	public void setCommID(String commID) {
		this.commID = commID;
	}

	public LocalDate getDateScheduled() {
		return dateScheduled;
	}

	public void setDateScheduled(LocalDate dateScheduled) {
		this.dateScheduled = dateScheduled;
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
			ps = con.prepareStatement("INSERT INTO tborders (orders_id, csr_id, co_id, date_id, orders_time, orders_jobtype, orders_custtype, " +
					"orders_status, orders_amount_scheduled, orders_amount_total, orders_address, orders_city, orders_state, orders_zipcode, " +
					"comm_id, orders_date_scheduled, orders_advertising, orders_date_created, orders_date_modified) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
			ps.setString(15, commID);
			ps.setDate(16,Date.valueOf(this.getDateScheduled()));
			ps.setString(17, this.getAdvertising());
			ps.setTimestamp(18, Timestamp.valueOf(this.getDateCreated()));
			ps.setTimestamp(19, Timestamp.valueOf(this.getDateModified()));
			ps.executeUpdate();
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.update();
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
					"orders_address = ?, orders_city = ?, orders_state = ?, orders_zipcode = ?, orders_jobtype = ?, orders_time = ?, orders_date_scheduled = ?, comm_id = ?, " +
					"orders_advertising = ? WHERE orders_id = ? AND co_id = ?");
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
			ps.setDate(13, Date.valueOf(this.getDateScheduled()));
			ps.setString(14, this.getCommID());
			ps.setString(15, this.getAdvertising());
			ps.setString(16, this.getOrderID());
			ps.setString(17, this.getCompanyID());
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
			ps = con.prepareStatement("SELECT * FROM tborders WHERE orders_id = ? AND co_id = ?");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCompanyID());
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
	
	public Orders matchOrder() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Orders> match = new ArrayList<>();
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tborders INNER JOIN tbcsr ON tborders.csr_id = tbcsr.csr_id " +
					"WHERE tborders.co_id = ? AND tborders.orders_address = ? AND tborders.orders_city = ? AND tborders.orders_state = ? AND tborders.orders_zipcode = ? " +
					"AND tborders.orders_id != ? AND tborders.date_id >= ? AND tborders.date_id <= ? AND tbcsr.emptype_id = 'CSR' AND (tborders.orders_jobtype = 'EST' OR " +
					"tborders.orders_jobtype = 'DE' OR tborders.orders_jobtype = 'DC')");
			ps.setString(1, this.getCompanyID());
			ps.setString(2, this.getAddress());
			ps.setString(3, this.getCity());
			ps.setString(4, this.getState());
			ps.setString(5, this.getZip());
			ps.setString(6, this.getOrderID());
			ps.setDate(7, Date.valueOf(this.getDate().minusDays(30)));
			ps.setDate(8, Date.valueOf(this.getDate()));
			rs = ps.executeQuery();
			while(rs.next()) {
				match.add(new Orders(rs.getString("orders_id"), rs.getString("csr_id"), rs.getString("orders_address"), 
						rs.getString("orders_city"), rs.getString("orders_state"), rs.getString("orders_zipcode")));
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
		
		if(match.size() >= 1)
			return match.get(match.size() - 1);
		else
			return null;
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
	
	private void checkBacklog() {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT sales_id, date_id FROM tbsales WHERE sales_id = ? AND date_id >= ? AND date_id <= ?");
			ps.setString(1, this.getOrderID());
			ps.setDate(2, Date.valueOf(this.getDate().minusYears(1)));
			ps.setDate(3, Date.valueOf(this.getDate().plusYears(1)));
			rs = ps.executeQuery();
			if(rs.next()) {
				ps1 = con.prepareStatement("UPDATE tborders SET orders_backlog = 'C', orders_backlog_date = ? WHERE orders_id = ?");
				ps1.setDate(1, rs.getDate("date_id"));
				ps1.setString(2, this.getOrderID());
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
	
	public void authenticate() {
		//boolean isDup = this.checkForDup();

		//if(!isDup) {
			this.insert();
		//} else {
		//	this.update();
		//}
		
		this.checkBacklog();
	}
	
	
}
