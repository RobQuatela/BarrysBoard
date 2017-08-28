package com.barrysboard.model;

import java.time.LocalDate;

public class Sales {

	private int orderID;
	private String csrID;
	private String companyID;
	private LocalDate date;
	private String jobType;
	private String custType;
	private double scheduledAmount;
	private double totalAmount;
	
	public Sales(int orderID, String csrID, String companyID, LocalDate date, String jobType, String custType,
			double scheduledAmount, double totalAmount) {
		this.orderID = orderID;
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.jobType = jobType;
		this.custType = custType;
		this.scheduledAmount = scheduledAmount;
		this.totalAmount = totalAmount;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
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

	public double getScheduledAmount() {
		return scheduledAmount;
	}

	public void setScheduledAmount(double scheduledAmount) {
		this.scheduledAmount = scheduledAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
