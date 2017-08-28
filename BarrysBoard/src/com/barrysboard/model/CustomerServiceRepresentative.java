package com.barrysboard.model;

public class CustomerServiceRepresentative {

	private String csrID;
	private String csrName;
	private String companyID;
	
	public CustomerServiceRepresentative(String id, String name, String coId) {
		csrID = id;
		csrName = name;
		companyID = coId;
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

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
}
