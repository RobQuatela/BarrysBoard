package com.steemer.bi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Company {

	private int companyID;
	private String companyName;
	
	public Company(int companyID, String companyName) {
		this.companyID = companyID;
		this.companyName = companyName;
	}
	
	public Company() {
		
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCompanyID() {
		return companyID;
	}
	
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	
	public static ArrayList<Company> getCompany() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Company> companies = new ArrayList<>();
		
		try {
			con = ConnectDB.connect();
			ps = con.prepareStatement("SELECT * FROM tbcompany");
			rs = ps.executeQuery();
			while(rs.next()) {
				companies.add(new Company(
						rs.getInt(1), 
						rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return companies;
		
	}
}
