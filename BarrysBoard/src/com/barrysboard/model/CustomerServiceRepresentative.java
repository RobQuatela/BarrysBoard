package com.barrysboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceRepresentative {

	private String csrID;
	private String csrName;
	private String csrActive;
	
	public CustomerServiceRepresentative(String id, String name, String active) {
		csrID = id;
		csrName = name;
		csrActive = active;
	}
	
	public CustomerServiceRepresentative(String id) {
		this.csrID = id;
	}

	public String getCsrID() {
		return csrID;
	}

	public void setCsrID(String csrID) {
		this.csrID = csrID;
	}

	public String getCsrName() {
		return csrName;
	}

	public void setCsrName(String csrName) {
		this.csrName = csrName;
	}
	
	public String getCsrActive() {
		return csrActive;
	}

	public void setCsrActive(String csrActive) {
		this.csrActive = csrActive;
	}

	private void insert() {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("INSERT INTO tbcsr (csr_id, csr_name, csr_active) VALUES (?, ?, ?)");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCsrName());
			ps.setString(3, this.getCsrActive());
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
			ps = con.prepareStatement("UPDATE tbcsr SET csr_name = ?, csr_active = ? WHERE csr_id = ?");
			ps.setString(1, this.getCsrName());
			ps.setString(2, this.getCsrActive());
			ps.setString(3, this.getCsrID());
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
			ps = con.prepareStatement("SELECT * FROM tbcsr WHERE csr_id = ? AND csr_name = ? AND csr_active = ?");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCsrName());
			ps.setString(3, this.getCsrActive());
			rs = ps.executeQuery();
			if(rs.next()) {
				check = true;
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
		
		return check;
	}
	
	private boolean checkForUpdate() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isUpdate = false;
		
		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("SELECT * FROM tbcsr WHERE csr_id = ? AND (csr_name != ? OR csr_active != ?)");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCsrName());
			ps.setString(3, this.getCsrActive());
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
		
		if(!isDup) {
			if(isUpdate && !this.getCsrName().equalsIgnoreCase("Empty"))
				this.update();
			else
				this.insert();
		}
	}
}
