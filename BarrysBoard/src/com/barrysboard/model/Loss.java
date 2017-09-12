package com.barrysboard.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Loss {

	private String lossID;
	private String csrID;
	private String coID;
	private LocalDate dateID;
	private double lossAmount;
	private String reason;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	
	public Loss(String lossID, String csrID, String coID, LocalDate dateID, double lossAmount, String reason,
			LocalDateTime dateCreated, LocalDateTime dateModified) {
		this.lossID = lossID;
		this.csrID = csrID;
		this.coID = coID;
		this.dateID = dateID;
		this.lossAmount = lossAmount;
		this.reason = reason;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}

	public String getLossID() {
		return lossID;
	}

	public void setLossID(String lossID) {
		this.lossID = lossID;
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

	public LocalDate getDateID() {
		return dateID;
	}

	public void setDateID(LocalDate dateID) {
		this.dateID = dateID;
	}

	public double getLossAmount() {
		return lossAmount;
	}

	public void setLossAmount(double lossAmount) {
		this.lossAmount = lossAmount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
			ps = con.prepareStatement("INSERT INTO tbloss (loss_id, csr_id, co_id, date_id, loss_amount, loss_reason, " +
					"loss_date_created, loss_date_modified) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, this.getLossID());
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCoID());
			ps.setDate(4, Date.valueOf(this.getDateID()));
			ps.setDouble(5, this.getLossAmount());
			ps.setString(6, this.getReason());
			ps.setTimestamp(7, Timestamp.valueOf(this.getDateCreated()));
			ps.setTimestamp(8, Timestamp.valueOf(this.getDateModified()));
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
			ps = con.prepareStatement("UPDATE tbloss SET loss_amount = ?, loss_reason = ? loss_date_modified = ? " +
					"WHERE loss_id = ? AND csr_id = ? AND co_id = ? AND date_id = ?");
			ps.setDouble(1, this.getLossAmount());
			ps.setString(2, this.getReason());
			ps.setTimestamp(3, Timestamp.valueOf(this.getDateModified()));
			ps.setString(4, this.getLossID());
			ps.setString(5, this.getCsrID());
			ps.setString(6, this.getCoID());
			ps.setDate(7, Date.valueOf(this.getDateID()));
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
			ps = con.prepareStatement("SELECT * FROM tbloss WHERE loss_id = ? AND csr_id = ? AND co_id = ? AND date_id = ? AND loss_amount = ? " +
					"AND loss_reason = ?");
			ps.setString(1, this.getLossID());
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCoID());
			ps.setDate(4, Date.valueOf(this.getDateID()));
			ps.setDouble(5, this.getLossAmount());
			ps.setString(6, this.getReason());
			rs = ps.executeQuery();
			if(rs.next()) {
				isDup = true;
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
		
		return isDup;
	}
	
	private boolean checkForUpdate() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isUpdate = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbloss WHERE loss_id = ? AND csr_id = ? AND co_id = ? AND date_id = ? AND (loss_amount != ? " +
					"OR loss_reason != ?)");
			ps.setString(1, this.getLossID());
			ps.setString(2, this.getCsrID());
			ps.setString(3, this.getCoID());
			ps.setDate(4, Date.valueOf(this.getDateID()));
			ps.setDouble(5, this.getLossAmount());
			ps.setString(6, this.getReason());
			rs = ps.executeQuery();
			if(rs.next()) {
				isUpdate = true;
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
