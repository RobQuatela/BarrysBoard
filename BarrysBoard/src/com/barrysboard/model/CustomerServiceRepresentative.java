package com.barrysboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceRepresentative {

	private String csrID;
	private String csrName;
	
	public CustomerServiceRepresentative(String id, String name) {
		csrID = id;
		csrName = name;
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
	
	private void insert() {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBConnect.connect();
			ps = con.prepareStatement("INSERT INTO tbcsr (csr_id, csr_name) VALUES (?, ?)");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCsrName());
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
			ps = con.prepareStatement("UPDATE tbcsr SET csr_name = ? WHERE csr_id = ?");
			ps.setString(1, this.getCsrName());
			ps.setString(2, this.getCsrID());
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
			ps = con.prepareStatement("SELECT * FROM tbcsr WHERE csr_id = ?");
			ps.setString(1, this.getCsrID());
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
			ps = con.prepareStatement("SELECT * FROM tbcsr WHERE csr_id = ? AND csr_name != ?");
			ps.setString(1, this.getCsrID());
			ps.setString(2, this.getCsrName());
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
		boolean dup = this.checkForDup();
		
		if(dup) {
			if(!this.getCsrName().equalsIgnoreCase("Empty"))
				this.update();
		} else {
			this.insert();
		}
	}
}
