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
	
	public void insert() {
		Connection con = null;
		PreparedStatement ps = null;
		boolean check = this.checkForDup();
		
		if(check == false) {
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
}
