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
/*	private int booked;
	private int loss;
	private int estimate;
	private int cancel;
	private int complete;*/
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	
/*	public Orders(int orderID, String csrID, String companyID, LocalDate date, int booked, int loss, int estimate,
			int cancel, int complete) {
		this.ID = orderID;
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.booked = booked;
		this.loss = loss;
		this.estimate = estimate;
		this.cancel = cancel;
		this.complete = complete;
	}
	
	public Orders(int orderID, String csrID, String csrName, String companyID, LocalDate date, int booked, int loss, int estimate,
			int cancel, int complete) {
		this.csrID = csrID;
		this.csrName = csrName;
		this.companyID = companyID;
		this.date = date;
		this.booked = booked;
		this.loss = loss;
		this.estimate = estimate;
		this.cancel = cancel;
		this.complete = complete;
	}
	
	public Orders(int orderID, String csrID, String csrName, String companyID, LocalDate date, int booked, int loss, int estimate,
			int cancel, int complete, LocalDateTime dateCreated, LocalDateTime dateModified) {
		this.csrID = csrID;
		this.csrName = csrName;
		this.companyID = companyID;
		this.date = date;
		this.booked = booked;
		this.loss = loss;
		this.estimate = estimate;
		this.cancel = cancel;
		this.complete = complete;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}*/
	
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
			LocalDateTime dateCreated, LocalDateTime dateModified) {
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

/*	public int getBooked() {
		return booked;
	}

	public void setBooked(int booked) {
		this.booked = booked;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}*/

	public String getCsrName() {
		return csrName;
	}

	public void setCsrName(String csrName) {
		this.csrName = csrName;
	}
	
/*	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
*/
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
	
/*	private void insert() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("INSERT INTO tborders (csr_id, co_id, date_id, orders_booked, orders_loss, orders_estimate, orders_cancel, orders_complete, orders_date_created, orders_date_modified) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCompanyID());
			ps.setDate(3, Date.valueOf(this.getDate()));
			ps.setInt(4, this.getBooked());
			ps.setInt(5, this.getLoss());
			ps.setInt(6, this.getEstimate());
			ps.setInt(7, this.getCancel());
			ps.setInt(8, this.getComplete());
			ps.setTimestamp(9, Timestamp.valueOf(this.getDateCreated()));
			ps.setTimestamp(10, Timestamp.valueOf(this.getDateModified()));
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
			ps.setString(3, this.getCompanyID());
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
	
	private void update() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("UPDATE tborders SET orders_status = ?, csr_id = ?, orders_amount_scheduled = ?, orders_amount_total = ?, orders_date_modified = ? " +
					"WHERE orders_id = ? AND co_id = ? AND date_id = ?");
			ps.setString(1, this.getStatus());
			ps.setString(2, this.getCsrID());
			ps.setDouble(3, this.getAmountScheduled());
			ps.setDouble(4, this.getAmountTotal());
			ps.setTimestamp(5, Timestamp.valueOf(this.getDateModified()));
			ps.setString(6, this.getOrderID());
			ps.setString(7, this.getCompanyID());
			ps.setDate(8, Date.valueOf(this.getDate()));
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
					"orders_custtype = ? AND orders_status = ? AND orders_amount_scheduled = ? AND orders_amount_total = ?");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCompanyID());
			ps.setDate(4, Date.valueOf(this.getDate()));
			ps.setString(5, this.getJobType());
			ps.setString(6, this.getCustType());
			ps.setString(7, this.getStatus());
			ps.setDouble(8, this.getAmountScheduled());
			ps.setDouble(9, this.getAmountTotal());
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
			ps = con.prepareStatement("SELECT * FROM tborders WHERE orders_id = ? AND csr_id = ? AND co_id = ? AND date_id = ? AND " +
					"(orders_jobtype != ? OR orders_custtype != ? OR orders_status != ? OR orders_amount_scheduled != ? OR " +
					"orders_amount_total != ?)");
			ps.setString(1, this.getOrderID());
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCompanyID());
			ps.setDate(4, Date.valueOf(this.getDate()));
			ps.setString(5, this.getJobType());
			ps.setString(6, this.getCustType());
			ps.setString(7, this.getStatus());
			ps.setDouble(8, this.getAmountScheduled());
			ps.setDouble(9, this.getAmountTotal());
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
			if(!isUpdate)
				this.insert();
			else
				this.update();
	}
	
	
}
