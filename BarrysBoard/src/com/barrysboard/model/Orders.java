package com.barrysboard.model;

import java.time.LocalDate;

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
	
	
}
