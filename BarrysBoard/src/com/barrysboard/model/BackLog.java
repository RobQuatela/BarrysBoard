package com.barrysboard.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BackLog {

	private int logId;
	private String csrID;
	private String companyID;
	private LocalDate date;
	private int backlogAmount;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;

	public BackLog(int logId, String csrID, String companyID, LocalDate date, int backlogAmount) {
		this.logId = logId;
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.backlogAmount = backlogAmount;
	}
	
	public BackLog(String csrID, String companyID, LocalDate date, int backlogAmount) {
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.backlogAmount = backlogAmount;
	}
	
	public BackLog(String csrID, String companyID, LocalDate date, int backlogAmount, LocalDateTime dateCreated, LocalDateTime dateModified) {
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.backlogAmount = backlogAmount;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}
	
	
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
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

	public int getBackLogAmount() {
		return backlogAmount;
	}

	public void setBackLogAmount(int backlogAmount) {
		this.backlogAmount = backlogAmount;
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
		if(!this.getCompanyID().equalsIgnoreCase("SAV")) {
			try {
				con = DBConnect.connect();
				ps = con.prepareStatement(
						"INSERT INTO tbbacklog (csr_id, co_id, date_id, backlog_amount, backlog_date_created, backlog_date_modified) VALUES (?, ?, ?, ?, ?, ?)");
				ps.setString(1, this.getCsrID());
				ps.setString(2, this.getCompanyID());
				ps.setDate(3, Date.valueOf(this.getDate()));
				ps.setInt(4, this.getBackLogAmount());
				ps.setTimestamp(5, Timestamp.valueOf(this.getDateCreated()));
				ps.setTimestamp(6, Timestamp.valueOf(this.getDateModified()));
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
			ps = con.prepareStatement("UPDATE tbbacklog SET backlog_amount = ?, backlog_date_modified = ? WHERE date_id = ? AND csr_id = ? AND co_id = ?");
			ps.setInt(1, this.getBackLogAmount());
			ps.setTimestamp(2, Timestamp.valueOf(this.getDateModified()));
			ps.setDate(3, Date.valueOf(this.getDate()));
			ps.setString(4, this.getCsrID());
			ps.setString(5, this.getCompanyID());
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
			ps = con.prepareStatement("SELECT * FROM tbbacklog WHERE date_id = ? AND csr_id = ? AND co_id = ? AND backlog_amount = ?");
			ps.setDate(1, Date.valueOf(this.getDate()));
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCompanyID());
			ps.setInt(4, this.getBackLogAmount());
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
			ps = con.prepareStatement("SELECT * FROM tbbacklog WHERE date_id = ? AND csr_id = ? AND co_id = ? AND backlog_amount != ?");
			ps.setDate(1, Date.valueOf(this.getDate()));
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCompanyID());
			ps.setInt(4, this.getBackLogAmount());
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
			if(!isUpdate)
				this.insert();
			else
				this.update();
		}
	}
}
