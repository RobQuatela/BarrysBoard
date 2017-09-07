package com.barrysboard.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Orders {

	private int ID;
	private String csrID;
	private String csrName;
	private String companyID;
	private LocalDate date;
	private int booked;
	private int loss;
	private int estimate;
	private int cancel;
	private int complete;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	
	public Orders(int orderID, String csrID, String companyID, LocalDate date, int booked, int loss, int estimate,
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
	}

	public int getOrderID() {
		return ID;
	}

	public void setOrderID(int orderID) {
		this.ID = orderID;
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

	public int getBooked() {
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
	}

	public String getCsrName() {
		return csrName;
	}

	public void setCsrName(String csrName) {
		this.csrName = csrName;
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
	}
	
	private void update() {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("UPDATE tborders SET orders_booked = ?, orders_loss = ?, orders_estimate = ?, orders_cancel = ?, orders_complete = ?, orders_date_modified = ? " +
					"WHERE csr_id = ? AND co_id = ? AND date_id = ?");
			ps.setInt(1, this.getBooked());
			ps.setInt(2, this.getLoss());
			ps.setInt(3, this.getEstimate());
			ps.setInt(4, this.getCancel());
			ps.setInt(5, this.getComplete());
			ps.setTimestamp(6, Timestamp.valueOf(this.getDateModified()));
			ps.setString(7, this.getCsrID());
			ps.setString(8, this.getCompanyID());
			ps.setDate(9, Date.valueOf(this.getDate()));
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
			ps = con.prepareStatement("SELECT * FROM tborders WHERE csr_id = ? AND co_id = ? AND date_id = ? AND orders_booked = ? AND orders_loss = ? " +
					"AND orders_estimate = ? AND orders_cancel = ? AND orders_complete = ?");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCompanyID());
			ps.setDate(3, Date.valueOf(this.getDate()));
			ps.setInt(4, this.getBooked());
			ps.setInt(5, this.getLoss());
			ps.setInt(6, this.getEstimate());
			ps.setInt(7, this.getCancel());
			ps.setInt(8, this.getComplete());
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
			ps = con.prepareStatement("SELECT * FROM tborders WHERE csr_id = ? AND co_id = ? AND date_id = ? AND (orders_booked != ? OR orders_loss != ? " +
					"OR orders_estimate != ? OR orders_cancel != ? OR orders_complete != ?)");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCompanyID());
			ps.setDate(3, Date.valueOf(this.getDate()));
			ps.setInt(4, this.getBooked());
			ps.setInt(5, this.getLoss());
			ps.setInt(6, this.getEstimate());
			ps.setInt(7, this.getCancel());
			ps.setInt(8, this.getComplete());
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
