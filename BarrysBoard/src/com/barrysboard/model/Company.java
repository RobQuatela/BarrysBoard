package com.barrysboard.model;

public class Company {

	private String companyID;
	private String companyName;
	
	public Company(String id, String name) {
		companyID = id;
		companyName = name;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
